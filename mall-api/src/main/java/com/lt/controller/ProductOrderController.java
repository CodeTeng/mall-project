package com.lt.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lt.common.BaseResponse;
import com.lt.common.ErrorCode;
import com.lt.common.PageRequest;
import com.lt.common.ResultUtils;
import com.lt.dto.order.UpdateOrderDTO;
import com.lt.exception.BusinessException;
import com.lt.service.ProductOrderService;
import com.lt.vo.order.ProductOrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
}
