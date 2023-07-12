package com.lt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.entity.ProductOrderItem;
import com.lt.mapper.ProductOrderItemMapper;
import com.lt.service.ProductOrderItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author teng
 * @description 针对表【productorderitem】的数据库操作Service实现
 * @createDate 2023-07-09 11:29:57
 */
@Service
public class ProductOrderItemServiceImpl extends ServiceImpl<ProductOrderItemMapper, ProductOrderItem>
        implements ProductOrderItemService {
    @Resource
    private ProductOrderItemMapper productOrderItemMapper;

    @Override
    public Integer getTotalSales(Integer productId) {
        QueryWrapper<ProductOrderItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(productId != null && productId > 0, "productOrderItemProductId", productId);
        queryWrapper.select("productOrderItemNumber");
        List<ProductOrderItem> productOrderItemList = productOrderItemMapper.selectList(queryWrapper);
        return productOrderItemList.stream()
                .map(ProductOrderItem::getProductOrderItemNumber)
                .reduce(Integer::sum).orElse(0);
    }
}




