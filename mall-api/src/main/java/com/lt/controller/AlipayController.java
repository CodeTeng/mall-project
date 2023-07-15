package com.lt.controller;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lt.common.ErrorCode;
import com.lt.constant.CommonConstant;
import com.lt.dto.pay.PayDTO;
import com.lt.entity.ProductOrder;
import com.lt.exception.BusinessException;
import com.lt.property.PayProperty;
import com.lt.service.ProductOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/14 10:12
 */
@RestController
@RequestMapping("/pay")
@Api(tags = "支付宝支付控制器")
@Slf4j
public class AlipayController {
    @Resource
    private PayProperty payProperty;
    @Resource
    private ProductOrderService productOrderService;

    @GetMapping("/alipay")
    @ApiOperation("进行支付宝支付")
    public void aliPay(PayDTO payDTO, HttpServletResponse resp) {
        if (payDTO == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        BigDecimal money = payDTO.getMoney();
        String subject = payDTO.getSubject();
        if (money == null || StringUtils.isBlank(subject)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 订单号
        String productOrderCode = DateUtil.format(new Date(), "yyyyMMddHHmmss");
        Integer productOrderId = payDTO.getProductOrderId();
        ProductOrder productOrder = productOrderService.getById(productOrderId);
        productOrder.setProductOrderCode(productOrderCode);
        // 先更新订单号
        productOrderService.updateById(productOrder);
        log.info("订单号：{}", productOrderCode);
        // 调用封装好的方法（给支付宝接口发送请求）
        sendRequestToAlipay(payDTO, productOrderCode, resp);
    }

    private void sendRequestToAlipay(PayDTO payDTO, String productOrderCode, HttpServletResponse resp) {
        BigDecimal money = payDTO.getMoney();
        String subject = payDTO.getSubject();
        // 调用支付宝API
        AlipayClient alipayClient = new DefaultAlipayClient(
                payProperty.getGatewayUrl(),
                payProperty.getAppId(),
                payProperty.getAppPrivateKey(),
                payProperty.getFormat(),
                payProperty.getEncoding(),
                payProperty.getAlipayPublicKey(),
                payProperty.getSignType());
        // 构造一个支付请求
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        // 异步回调,用于接收支付宝服务器的回调请求
        request.setNotifyUrl(payProperty.getNotifyUrl());
        // 同步回调,重定向url
        request.setReturnUrl(payProperty.getReturnUrl());

        // 封装请求体
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", productOrderCode);
        bizContent.put("total_amount", money.doubleValue());
        bizContent.put("subject", subject);
        bizContent.put("trade_time", System.currentTimeMillis());
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
        // 1个小时后支付失效
        bizContent.put("time_expire", DateUtil.offsetHour(new Date(), 1));
        request.setBizContent(bizContent.toString());
        AlipayTradePagePayResponse response;
        try {
            response = alipayClient.pageExecute(request);
            if (response.isSuccess()) {
                log.info("调用成功！！！");
                // 拿到支付宝服务器响应的响应体
                String body = response.getBody();
                //将响应体,写入浏览器
                resp.setContentType("text/html;charset=utf-8");
                resp.getWriter().print(body);
                resp.getWriter().close();
            } else {
                log.error("支付宝调用失败！！！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "支付宝调用失败");
        }
    }

    //    @PostMapping("/notifyUrl")
//    @ApiOperation("异步回调")
//    public void notifyUrlMethod(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        log.info("------------------------异步回调----------------------------------");
//        Map<String, String> params = new HashMap<>();
//        Map<String, String[]> requestParams = request.getParameterMap();
//        for (String name : requestParams.keySet()) {
//            String[] values = requestParams.get(name);
//            String valueStr = "";
//            for (int i = 0; i < values.length; i++) {
//                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
//            }
//            params.put(name, valueStr);
//        }
//        log.info("参数：{}", params);
//        // 验证签名（支付宝公钥）
//        // 调用SDK验证签名
//        boolean signVerified = AlipaySignature.rsaCheckV1(params, payProperty.getAlipayPublicKey(), payProperty.getEncoding(), payProperty.getSignType());
//        // 验证签名通过
//        if (!signVerified) {
//            throw new BusinessException(ErrorCode.OPERATION_ERROR, "支付失败");
//        }
//        String outTradeNo = new String(request.getParameter("out_trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
//        String tradeNo = new String(request.getParameter("trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
//        BigDecimal totalAmount = new BigDecimal(new String(request.getParameter("total_amount").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
//        double money = totalAmount.doubleValue();
//        log.info("商户订单号=" + outTradeNo);
//        log.info("支付宝交易号=" + tradeNo);
//        log.info("付款金额=" + money);
//        updateProductOrder(outTradeNo);
//        response.sendRedirect("http://localhost:5173/order");
//    }
//
    private void updateProductOrder(String outTradeNo) {
        // 修改订单状态
        QueryWrapper<ProductOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("productOrderCode", outTradeNo);
        ProductOrder productOrder = productOrderService.getOne(queryWrapper);
        if (productOrder == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "该订单不存在！");
        }
        productOrder.setProductOrderStatus(1);
        productOrder.setProductOrderCode(outTradeNo);
        productOrder.setProductOrderPayDate(new Date());
        productOrderService.updateById(productOrder);
    }

    @GetMapping("/returnUrl")
    @ApiOperation("同步回调接口")
    public void returnUrlMethod(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("------------------------同步回调----------------------------------");
        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
            params.put(name, valueStr);
        }
        log.info("参数：{}", params);
        // 验证签名（支付宝公钥）
        // 调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, payProperty.getAlipayPublicKey(), payProperty.getEncoding(), payProperty.getSignType());
        // 验证签名通过
        if (!signVerified) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "支付失败");
        }
        String outTradeNo = new String(request.getParameter("out_trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String tradeNo = new String(request.getParameter("trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        BigDecimal totalAmount = new BigDecimal(new String(request.getParameter("total_amount").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        double money = totalAmount.doubleValue();
        log.info("商户订单号=" + outTradeNo);
        log.info("支付宝交易号=" + tradeNo);
        log.info("付款金额=" + money);
        updateProductOrder(outTradeNo);
        response.sendRedirect(CommonConstant.DIRECT_URL);
    }
}
