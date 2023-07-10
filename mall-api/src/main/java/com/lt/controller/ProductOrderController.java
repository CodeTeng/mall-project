package com.lt.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lt.common.BaseResponse;
import com.lt.common.ErrorCode;
import com.lt.common.ResultUtils;
import com.lt.dto.product.ProductSearchDTO;
import com.lt.exception.BusinessException;
import com.lt.service.ProductOrderService;
import com.lt.vo.ProductOrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/getMyAllOrder/{type}")
    @ApiOperation(value = "分页获取我的所有订单", notes = "type代表获取类型，-1获取所有,0待付款,1待发货,2待收货,3已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "订单的状态", dataType = "Integer", required = true, paramType = "path")
    })
    public BaseResponse<Page<ProductOrderVO>> getMyAllOrder(@PathVariable("type") Integer type, ProductSearchDTO productSearchDTO) {
        // TODO 有BUG
        if (productSearchDTO == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (type == null || (type != -1 && type != 0 && type != 1 && type != 2 && type != 3)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Page<ProductOrderVO> productOrderVOPage = productOrderService.getMyAllOrder(productSearchDTO, type);
        return ResultUtils.success(productOrderVOPage);
    }
}
