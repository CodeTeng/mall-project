package com.lt.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lt.dto.cart.AddCartDTO;
import com.lt.entity.ProductOrderItem;
import com.lt.vo.cart.CartVO;
import com.lt.vo.cart.ConfirmCartVO;

import java.util.List;

/**
 * @author teng
 * @description 针对表【productOrderItem】的数据库操作Service
 * @createDate 2023-07-09 11:29:57
 */
public interface ProductOrderItemService extends IService<ProductOrderItem> {
    /**
     * 获取商品总销量数
     *
     * @param productId 商品id
     * @return 总销量数
     */
    Integer getTotalSales(Integer productId);

    /**
     * 获取我的购物车信息
     *
     * @param userId 用户id
     * @return 购物车信息
     */
    List<CartVO> getMyCart(Integer userId);

    /**
     * 更新购物车中的订单信息
     *
     * @param orderItemId            订单id
     * @param productOrderItemNumber 商品数量
     */
    void updateCartItm(Integer orderItemId, Integer productOrderItemNumber);

    /**
     * 添加商品到购物车中
     *
     * @param addCartDTO 添加购物车DTO
     */
    Integer addCartItem(AddCartDTO addCartDTO);

    /**
     * 根据订单项id获取订单信息
     *
     * @param orderItemIdList 订单项id List
     * @return 数据信息
     */
    List<CartVO> getCartListByItemId(List<Integer> orderItemIdList);

    /**
     * 根据订单id获取订单项信息
     *
     * @param productOrderId 订单id
     * @return 订单项信息
     */
    List<ConfirmCartVO> getCartListByOrderId(Integer productOrderId);
}
