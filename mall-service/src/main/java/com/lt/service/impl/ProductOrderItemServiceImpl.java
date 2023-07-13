package com.lt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.common.ErrorCode;
import com.lt.entity.ProductOrderItem;
import com.lt.exception.BusinessException;
import com.lt.mapper.ProductOrderItemMapper;
import com.lt.service.ProductOrderItemService;
import com.lt.vo.cart.CartVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @author teng
 * @description 针对表【productorderitem】的数据库操作Service实现
 * @createDate 2023-07-09 11:29:57
 */
@Service
@Slf4j
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

    @Override
    public List<CartVO> getMyCart(Integer userId) {
        return productOrderItemMapper.getMyCart(userId);
    }

    @Override
    public void updateCartItm(Integer orderItemId, Integer productOrderItemNumber) {
        ProductOrderItem productOrderItem = productOrderItemMapper.selectById(orderItemId);
        if (productOrderItem == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "该订单不存在");
        }
        // 首先获取每件商品的单价
        BigDecimal productOrderItemPrice = productOrderItem.getProductOrderItemPrice();
        Integer count = productOrderItem.getProductOrderItemNumber();
        BigDecimal commodityPrice = productOrderItemPrice.divide(new BigDecimal(count), RoundingMode.HALF_UP);
        log.info("商品的单价为：{}", commodityPrice.doubleValue());
        productOrderItem.setProductOrderItemNumber(productOrderItemNumber);
        BigDecimal multiCount = new BigDecimal(productOrderItemNumber);
        // 单价乘总数量
        BigDecimal finalOrderItemPrice = commodityPrice.multiply(multiCount);
        productOrderItem.setProductOrderItemPrice(finalOrderItemPrice);
        productOrderItemMapper.updateById(productOrderItem);
    }
}




