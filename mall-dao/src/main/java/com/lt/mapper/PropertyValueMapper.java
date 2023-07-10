package com.lt.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lt.entity.PropertyValue;
import com.lt.vo.ProductParameterVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author teng
 * @description 针对表【propertyValue】的数据库操作Mapper
 * @createDate 2023-07-09 11:29:57
 * @Entity generator.domain.PropertyValue
 */
@Repository
public interface PropertyValueMapper extends BaseMapper<PropertyValue> {
    /**
     * 根据商品id获取产品参数
     *
     * @param productId 商品id
     * @return 产品参数
     */
    List<ProductParameterVO> getProductParameter(@Param("productId") Integer productId);
}




