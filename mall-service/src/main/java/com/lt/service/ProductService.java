package com.lt.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lt.entity.Product;
import com.lt.vo.HomeProductVO;

/**
 * @author teng
 * @description 针对表【product】的数据库操作Service
 * @createDate 2023-07-09 11:29:57
 */
public interface ProductService extends IService<Product> {

    /**
     * 获取主页分类下的商品
     *
     * @param categoryId 商品分类id
     * @return 主页商品VO
     */
    HomeProductVO getHomeProductList(Integer categoryId);
}
