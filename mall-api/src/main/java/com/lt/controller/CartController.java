package com.lt.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.lt.common.BaseResponse;
import com.lt.common.ErrorCode;
import com.lt.common.ResultUtils;
import com.lt.exception.BusinessException;
import com.lt.service.ProductOrderItemService;
import com.lt.utils.UserThreadLocalUtil;
import com.lt.vo.cart.CartVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/13 11:21
 */
@RestController
@RequestMapping("/cart")
@Api(tags = "购物车控制器")
public class CartController {
    @Resource
    private ProductOrderItemService productOrderItemService;

    @GetMapping("/getMyCart")
    @ApiOperation(value = "获取我的购物车信息", notes = "前端需要处理数据店铺的全称 贤趣 + categoryName + 旗舰店")
    public BaseResponse<List<CartVO>> getMyCart() {
        Integer userId = UserThreadLocalUtil.getUserId();
        List<CartVO> cartVOList = productOrderItemService.getMyCart(userId);
        return ResultUtils.success(cartVOList);
    }

    @DeleteMapping("/deleteCartItem")
    @ApiOperation(value = "根据订单id删除购物车订单信息", notes = "删除一个或多个")
    public BaseResponse<String> deleteCartItem(@RequestParam("productOrderItemIdList") List<Integer> productOrderItemIdList) {
        if (CollectionUtil.isEmpty(productOrderItemIdList)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请选取购物车中的商品信息");
        }
        boolean flag = productOrderItemService.removeBatchByIds(productOrderItemIdList);
        if (!flag) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "删除失败");
        }
        return ResultUtils.success("删除成功");
    }

    @PostMapping("/updateCartItm/{orderItemId}")
    @ApiOperation("更新购物车中的订单信息")
    public BaseResponse<Boolean> updateCartItm(@PathVariable("orderItemId") Integer orderItemId,
                                               @RequestParam("productOrderItemNumber") Integer productOrderItemNumber) {
        if (orderItemId == null || orderItemId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (productOrderItemNumber == null || productOrderItemNumber <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "商品数量至少为1");
        }
        productOrderItemService.updateCartItm(orderItemId, productOrderItemNumber);
        return ResultUtils.success(true);
    }
}
