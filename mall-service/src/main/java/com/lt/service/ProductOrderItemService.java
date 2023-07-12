package com.lt.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lt.entity.ProductOrderItem;

/**
 * @author teng
 * @description 针对表【productOrderItem】的数据库操作Service
 * @createDate 2023-07-09 11:29:57
 */
public interface ProductOrderItemService extends IService<ProductOrderItem> {
    /**
     * 获取商品总销量数
     *
     * @param productId 商品id
     * @return 总销量数
     */
    Integer getTotalSales(Integer productId);
}
