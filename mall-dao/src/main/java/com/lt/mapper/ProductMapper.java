package com.lt.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lt.entity.Product;
import com.lt.vo.product.DetailedProductVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author teng
 * @description 针对表【product】的数据库操作Mapper
 * @createDate 2023-07-09 11:29:57
 * @Entity generator.domain.Product
 */
@Repository
public interface ProductMapper extends BaseMapper<Product> {
    /**
     * 获取商品详细信息
     *
     * @param productId 商品id
     * @return 商品详细信息
     */
    DetailedProductVO getDetailedProduct(@Param("productId") Integer productId);
}




