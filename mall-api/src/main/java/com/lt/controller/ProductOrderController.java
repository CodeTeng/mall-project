package com.lt.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lt.common.BaseResponse;
import com.lt.common.ErrorCode;
import com.lt.common.PageRequest;
import com.lt.common.ResultUtils;
import com.lt.dto.order.AddOrderDTO;
import com.lt.dto.order.AddOrderItemDTO;
import com.lt.dto.order.UpdateOrderDTO;
import com.lt.entity.ProductOrder;
import com.lt.exception.BusinessException;
import com.lt.service.ProductOrderService;
import com.lt.vo.order.ProductOrderTimeVO;
import com.lt.vo.order.ProductOrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/10 14:32
 */
@RestController
@RequestMapping("/order")
@Api(tags = "商品订单控制器")
@Slf4j
public class ProductOrderController {
    @Resource
    private ProductOrderService productOrderService;

    @GetMapping("/getMyAllOrder")
    @ApiOperation(value = "分页获取我的所有订单", notes = "status代表获取类型，-1获取所有,0待付款,1待发货,2待收货,3已完成,4取消交易")
    public BaseResponse<PageInfo<ProductOrderVO>> getMyAllOrder(@RequestParam(value = "status", required = false) Integer status, PageRequest pageRequest) {
        if (pageRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (status == null || (status != -1 && status != 0 && status != 1 && status != 2 && status != 3 && status != 4)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 分页
        PageHelper.startPage(pageRequest.getCurrent(), pageRequest.getPageSize());
        List<ProductOrderVO> productOrderVOList = productOrderService.getMyAllOrder(pageRequest, status);
        PageInfo<ProductOrderVO> pageInfo = new PageInfo<>(productOrderVOList);
        return ResultUtils.success(pageInfo);
    }

    @PostMapping("/updateOrderStatus")
    @ApiOperation("修改订单状态")
    public BaseResponse<Boolean> updateOrderStatus(@RequestBody UpdateOrderDTO updateOrderDTO) {
        if (updateOrderDTO == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Integer productOrderId = updateOrderDTO.getProductOrderId();
        Integer productOrderStatus = updateOrderDTO.getProductOrderStatus();
        if (productOrderId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (productOrderStatus != 0 && productOrderStatus != 1 && productOrderStatus != 2 && productOrderStatus != 3 && productOrderStatus != 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        productOrderService.updateOrderStatus(productOrderId, productOrderStatus);
        return ResultUtils.success(Boolean.TRUE);
    }

    @PostMapping("/addOrder")
    @ApiOperation("添加订单")
    public BaseResponse<Integer> addOrder(@RequestBody AddOrderDTO addOrderDTO) {
        if (addOrderDTO == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String productOrderAddress = addOrderDTO.getProductOrderAddress();
        String productOrderDetailAddress = addOrderDTO.getProductOrderDetailAddress();
        String productOrderReceiver = addOrderDTO.getProductOrderReceiver();
        String productOrderMobile = addOrderDTO.getProductOrderMobile();
        if (StringUtils.isAnyBlank(productOrderAddress, productOrderDetailAddress, productOrderReceiver, productOrderMobile)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请填写完整的收获人信息");
        }
        List<AddOrderItemDTO> addOrderItemDTOList = addOrderDTO.getAddOrderItemDTOList();
        if (CollectionUtil.isEmpty(addOrderItemDTOList)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "暂无订单数据，无法提交订单");
        }
        Integer productOrderId = productOrderService.addOrder(addOrderDTO);
        return ResultUtils.success(productOrderId);
    }

    @GetMapping("/getOrderTime")
    @ApiOperation("根据订单id获取订单的时间信息")
    public BaseResponse<ProductOrderTimeVO> getOrderTime(@RequestParam("productOrderId") Integer productOrderId) {
        if (productOrderId == null || productOrderId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ProductOrder productOrder = productOrderService.getById(productOrderId);
        if (productOrder == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "该订单不存在");
        }
        ProductOrderTimeVO productOrderTimeVO = new ProductOrderTimeVO();
        BeanUtils.copyProperties(productOrder, productOrderTimeVO);
        return ResultUtils.success(productOrderTimeVO);
    }
}
