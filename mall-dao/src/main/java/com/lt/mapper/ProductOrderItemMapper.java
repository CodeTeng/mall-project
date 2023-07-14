package com.lt.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lt.entity.ProductOrderItem;
import com.lt.vo.cart.CartVO;
import com.lt.vo.cart.ConfirmCartVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author teng
 * @description 针对表【productOrderItem】的数据库操作Mapper
 * @createDate 2023-07-09 11:29:57
 * @Entity generator.domain.ProductOrderItem
 */
@Repository
public interface ProductOrderItemMapper extends BaseMapper<ProductOrderItem> {
    /**
     * 根据用户id获取购物车信息
     *
     * @param userId 用户id
     * @return 购物车信息
     */
    List<CartVO> getMyCart(@Param("userId") Integer userId);

    /**
     * 给卖家留言
     *
     * @param productOrderItemId          订单项id
     * @param productOrderId              生成的订单id
     * @param productOrderItemUserMessage 留言信息
     */
    void updateOrderItem(@Param("productOrderItemId") Integer productOrderItemId, @Param("productOrderId") Integer productOrderId, @Param("productOrderItemUserMessage") String productOrderItemUserMessage);

    /**
     * 根据订单项id获取订单信息
     *
     * @param orderItemIdList 订单项id List
     * @return 数据信息
     */
    List<CartVO> getCartListByItemId(@Param("orderItemIdList") List<Integer> orderItemIdList);

    /**
     * 根据订单id获取订单项信息
     *
     * @param productOrderId 订单id
     * @return 订单项信息
     */
    List<ConfirmCartVO> getCartListByOrderId(@Param("productOrderId") Integer productOrderId);
}




