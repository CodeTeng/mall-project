package com.lt.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lt.entity.Product;
import com.lt.vo.ProductCategoryVO;
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
    List<ProductCategoryVO> getHomeProductList(@Param("categoryId") Integer categoryId);
}




