package com.lt.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lt.entity.Category;
import com.lt.vo.category.CategoryHomeVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author teng
 * @description 针对表【category】的数据库操作Mapper
 * @createDate 2023-07-09 11:29:57
 * @Entity generator.domain.Category
 */
@Repository
public interface CategoryMapper extends BaseMapper<Category> {
    /**
     * 获取主页分类下的所有商品
     */
    List<CategoryHomeVO> getHomeProductList();
}




