package com.lt.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lt.dto.product.ProductSearchDTO;
import com.lt.entity.ProductOrder;
import com.lt.vo.ProductOrderVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author teng
 * @description 针对表【productOrder】的数据库操作Mapper
 * @createDate 2023-07-09 11:29:57
 * @Entity generator.domain.ProductOrder
 */
@Repository
public interface ProductOrderMapper extends BaseMapper<ProductOrder> {

    /**
     * 根据商品id查询该商品总成交个数
     *
     * @param productId 商品id
     * @return 总成交个数
     */
    Integer getTotalTransactionCountByProductId(@Param("productId") Integer productId);

    /**
     * 搜索所有订单
     *
     * @param userId      用户id
     * @param productName 商品名称
     * @return 订单数据VO
     */
    List<ProductOrderVO> getMyAllOrder(@Param("userId") Integer userId, @Param("productName") String productName);

    /**
     * 指定订单
     *
     * @param userId      用户id
     * @param type        订单类型
     * @param productName 商品名称
     * @return 订单数据VO
     */
    List<ProductOrderVO> getMyAllOrderByType(@Param("userId") Integer userId, @Param("productName") String productName, @Param("type") Integer type);
}




