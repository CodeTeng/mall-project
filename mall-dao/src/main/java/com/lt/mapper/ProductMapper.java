package com.lt.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lt.entity.Product;
import com.lt.vo.product.ProductSearchVO;
import com.lt.vo.product.DetailedProductVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * 根据分类id获取商品信息
     *
     * @param categoryId 分类id
     * @param sortField  排序字段
     * @param sortOrder  排序方式
     * @return 商品信息
     */
    List<ProductSearchVO> getProductByCategoryId(@Param("categoryId") Integer categoryId, @Param("sortField") String sortField, @Param("sortOrder") String sortOrder);
}




