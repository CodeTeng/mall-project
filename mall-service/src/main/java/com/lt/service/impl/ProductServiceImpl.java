package com.lt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.constant.CommonConstant;
import com.lt.dto.product.ProductSearchDTO;
import com.lt.entity.Product;
import com.lt.entity.ProductImage;
import com.lt.entity.Review;
import com.lt.mapper.ProductImageMapper;
import com.lt.mapper.ProductMapper;
import com.lt.mapper.ProductOrderMapper;
import com.lt.mapper.ReviewMapper;
import com.lt.service.ProductService;
import com.lt.vo.HomeProductVO;
import com.lt.vo.ProductCategoryVO;
import com.lt.vo.ProductSearchVO;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
    @Resource
    private ReviewMapper reviewMapper;
    @Resource
    private ProductOrderMapper productOrderMapper;

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
            queryWrapper.eq("productImageType", 0);
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

    @Override
    public Page<ProductSearchVO> search(ProductSearchDTO productSearchDTO) {
        String productName = productSearchDTO.getProductName();
        long current = productSearchDTO.getCurrent();
        long pageSize = productSearchDTO.getPageSize();
        String sortField = productSearchDTO.getSortField();
        String sortOrder = productSearchDTO.getSortOrder();
        // 1. 模糊查询商品的基本信息
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.ne("productIsEnabled", 1);
        productQueryWrapper.like(StringUtils.isNotBlank(productName), "productName", productName);
        productQueryWrapper.select("productId", "productName", "productSalePrice");
        productQueryWrapper.orderBy(StringUtils.isNotBlank(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_DESC), sortField);
        // 2. 分页查询
        Page<Product> productPage = productMapper.selectPage(new Page<>(current, pageSize), productQueryWrapper);
        List<Product> productList = productPage.getRecords();
        if (CollectionUtils.isEmpty(productList)) {
            // 没有商品直接返回
            return new Page<>(current, pageSize);
        }
        // 3. 根据模糊查询出来的商品并进行封装返回
        Page<ProductSearchVO> productSearchVOPage =
                new Page<>(productPage.getCurrent(), productPage.getSize(), productPage.getTotal());
        List<ProductSearchVO> searchVOList = productList.stream().map(product -> {
            ProductSearchVO productSearchVO = new ProductSearchVO();
            BeanUtils.copyProperties(product, productSearchVO);
            Integer productId = product.getProductId();
            QueryWrapper<ProductImage> productImageQueryWrapper = new QueryWrapper<>();
            productImageQueryWrapper.eq("productImageType", 0);
            productImageQueryWrapper.eq("productImageProductId", productId);
            productImageQueryWrapper.last("limit 5");
            // 3.1 查询每个商品的多张商品图片
            List<ProductImage> productImageList = productImageMapper.selectList(productImageQueryWrapper);
            productSearchVO.setProductPlanSrc(productImageList.get(0).getProductImageSrc());
            List<String> productImageSrcList = productImageList.stream()
                    .map(ProductImage::getProductImageSrc).collect(Collectors.toList());
            productSearchVO.setProductImageSrcList(productImageSrcList);
            // 3.2 根据商品id查询评论个数
            Long reviewCount = reviewMapper.selectCount(new QueryWrapper<Review>().eq("reviewProductId", productId));
            productSearchVO.setReviewCount(Math.toIntExact(reviewCount));
            // 3.3 根据商品id查询该商品总成交个数
            Integer totalTransactionCount = productOrderMapper.getTotalTransactionCountByProductId(productId);
            productSearchVO.setTotalTransactionCount(totalTransactionCount);
            return productSearchVO;
        }).collect(Collectors.toList());
        productSearchVOPage.setRecords(searchVOList);
        return productSearchVOPage;
    }
}




