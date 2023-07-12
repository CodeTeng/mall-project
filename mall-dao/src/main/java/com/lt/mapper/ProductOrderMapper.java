package com.lt.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lt.entity.ProductOrder;
import com.lt.vo.order.ProductOrderVO;
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
     * 获取当前用户订单数据
     *
     * @param status 状态
     * @param userId 当前用户id
     * @return 订单数据
     */
    List<ProductOrderVO> getMyAllOrder(@Param("status") Integer status, @Param("userId") Integer userId);
}




