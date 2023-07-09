package com.lt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.entity.Product;
import com.lt.entity.ProductImage;
import com.lt.mapper.ProductImageMapper;
import com.lt.mapper.ProductMapper;
import com.lt.service.ProductService;
import com.lt.vo.HomeProductVO;
import com.lt.vo.ProductCategoryVO;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * @author teng
 * @description 针对表【product】的数据库操作Service实现
 * @createDate 2023-07-09 11:29:57
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
        implements ProductService {
    @Resource
    private ProductMapper productMapper;
    @Resource
    private ProductImageMapper productImageMapper;

    @Override
    public HomeProductVO getHomeProductList(Integer categoryId) {
        HomeProductVO homeProductVO = new HomeProductVO();
        // 获取分类下的商品信息
        List<ProductCategoryVO> productCategoryVOList = productMapper.getHomeProductList(categoryId);
        if (CollectionUtils.isEmpty(productCategoryVOList)) {
            // 该分类下无商品信息 直接返回
            return homeProductVO;
        }
        Random random = new Random();
        // 为每一个商品设置图片
        for (ProductCategoryVO productCategoryVO : productCategoryVOList) {
            Integer productId = productCategoryVO.getProductId();
            QueryWrapper<ProductImage> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("productImageProductId", productId);
            // 查询该商品的所有图片
            List<ProductImage> productImageList = productImageMapper.selectList(queryWrapper);
            if (CollectionUtils.isEmpty(productImageList)) {
                // 该商品没有图片 直接不显示
                productCategoryVO.setProductImageSrc("#");
                continue;
            }
            // 有图片 随机获取一张
            int size = productImageList.size();
            int index = random.nextInt(size);
            productCategoryVO.setProductImageSrc(productImageList.get(index).getProductImageSrc());
        }
        homeProductVO.setProductCategoryVOList(productCategoryVOList);
        return homeProductVO;
    }
}




