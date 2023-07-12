package com.lt.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lt.common.BaseResponse;
import com.lt.common.ErrorCode;
import com.lt.common.PageRequest;
import com.lt.common.ResultUtils;
import com.lt.exception.BusinessException;
import com.lt.service.ProductOrderService;
import com.lt.vo.order.ProductOrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/10 14:32
 */
@RestController
@RequestMapping("/order")
@Api(tags = "商品订单控制器")
public class ProductOrderController {
    @Resource
    private ProductOrderService productOrderService;

    @GetMapping("/getMyAllOrder")
    @ApiOperation(value = "分页获取我的所有订单", notes = "status代表获取类型，null获取所有,0待付款,1待发货,2待收货,3已完成,4取消交易")
    public BaseResponse<Page<ProductOrderVO>> getMyAllOrder(@RequestParam(value = "status", required = false) Integer status, PageRequest pageRequest) {
        if (pageRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Page<ProductOrderVO> productOrderVOPage = productOrderService.getMyAllOrder(pageRequest, status);
        return ResultUtils.success(productOrderVOPage);
    }
}
