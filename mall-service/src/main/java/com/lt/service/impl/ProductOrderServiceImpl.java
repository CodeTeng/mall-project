package com.lt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.constant.CommonConstant;
import com.lt.dto.product.ProductSearchDTO;
import com.lt.entity.Product;
import com.lt.entity.ProductImage;
import com.lt.entity.ProductOrder;
import com.lt.entity.ProductOrderItem;
import com.lt.mapper.ProductImageMapper;
import com.lt.mapper.ProductMapper;
import com.lt.mapper.ProductOrderItemMapper;
import com.lt.mapper.ProductOrderMapper;
import com.lt.service.ProductOrderService;
import com.lt.utils.UserThreadLocalUtil;
import com.lt.vo.ProductOrderVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * @author teng
 * @description 针对表【productOrder】的数据库操作Service实现
 * @createDate 2023-07-09 11:29:57
 */
@Service
public class ProductOrderServiceImpl extends ServiceImpl<ProductOrderMapper, ProductOrder>
        implements ProductOrderService {
    @Resource
    private ProductOrderMapper productOrderMapper;
    @Resource
    private ProductImageMapper productImageMapper;
    @Resource
    private ProductOrderItemMapper productOrderItemMapper;

    @Override
    public Page<ProductOrderVO> getMyAllOrder(ProductSearchDTO productSearchDTO, Integer type) {
        // 获取当前用户id
        Integer userId = UserThreadLocalUtil.getUserId();
        String productName = productSearchDTO.getProductName();
        long current = productSearchDTO.getCurrent();
        long pageSize = productSearchDTO.getPageSize();
        QueryWrapper<ProductOrderItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("productOrderItemUserId", userId);
        Page<ProductOrderItem> orderItemPage = productOrderItemMapper.selectPage(new Page<>(current, pageSize), queryWrapper);
        Page<ProductOrderVO> page = new Page<>(orderItemPage.getCurrent(), orderItemPage.getSize(), orderItemPage.getTotal());
        List<ProductOrderItem> orderItemList = orderItemPage.getRecords();
        if (CollectionUtils.isEmpty(orderItemList)) {
            // 该用户没有订单 直接返回
            return new Page<>(current, pageSize);
        }
        if (type == -1) {
            // 搜索所有订单
            List<ProductOrderVO> productOrderVOList = productOrderMapper.getMyAllOrder(userId, productName);
            // 根据商品id获取图片
            getProductImage(productOrderVOList);
            page.setRecords(productOrderVOList);
            page.setTotal(productOrderVOList.size());
            return page;
        }
        // 指定的订单
        List<ProductOrderVO> productOrderVOList = productOrderMapper.getMyAllOrderByType(userId, productName, type);
        // 根据商品id获取图片
        getProductImage(productOrderVOList);
        page.setRecords(productOrderVOList);
        page.setTotal(productOrderVOList.size());
        return page;
    }

    private void getProductImage(List<ProductOrderVO> productOrderVOList) {
        productOrderVOList.forEach(productOrderVO -> {
            Integer productId = productOrderVO.getProductId();
            QueryWrapper<ProductImage> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("productImageProductId", productId);
            queryWrapper.select("productImageSrc");
            List<ProductImage> productImageList = productImageMapper.selectList(queryWrapper);
            if (CollectionUtils.isEmpty(productImageList)) {
                // 该商品没有图片
                productOrderVO.setProductPlanSrc("#");
            } else {
                // 获取第一张图片
                productOrderVO.setProductPlanSrc(productImageList.get(0).getProductImageSrc());
            }
        });
    }
}




