package com.lt.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lt.dto.product.ProductSearchDTO;
import com.lt.entity.Product;
import com.lt.vo.HomeProductVO;
import com.lt.vo.ProductSearchVO;

import java.util.List;

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

    /**
     * 主页商品搜索
     *
     * @param productSearchDTO 搜索DTO
     * @return 分页搜索商品VO
     */
    Page<ProductSearchVO> search(ProductSearchDTO productSearchDTO);
}
