package com.lt.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lt.entity.Category;
import com.lt.vo.category.CategoryHomeVO;

import java.util.List;

/**
 * @author teng
 * @description 针对表【category】的数据库操作Service
 * @createDate 2023-07-09 11:29:57
 */
public interface CategoryService extends IService<Category> {

    /**
     * 获取主页分类下的所有商品
     */
    List<CategoryHomeVO> getHomeProductList();
}
