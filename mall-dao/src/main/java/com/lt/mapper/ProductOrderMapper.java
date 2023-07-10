package com.lt.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lt.entity.ProductOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
}




