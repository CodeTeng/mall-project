package com.lt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.constant.CommonConstant;
import com.lt.dto.product.ProductCategoryDTO;
import com.lt.dto.product.ProductSearchDTO;
import com.lt.entity.Category;
import com.lt.entity.Product;
import com.lt.entity.ProductImage;
import com.lt.entity.Review;
import com.lt.mapper.*;
import com.lt.service.ProductService;
import com.lt.vo.product.ProductSearchVO;
import com.lt.vo.product.DetailedProductVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
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
    @Resource
    private CategoryMapper categoryMapper;

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
        productQueryWrapper.select("productId", "productName", "productSalePrice", "productTitle");
        productQueryWrapper.orderBy(StringUtils.isNotBlank(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
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
            List<String> productImageSrcList = productImageList.stream()
                    .map(ProductImage::getProductImageSrc).collect(Collectors.toList());
            productSearchVO.setProductImageSrcList(productImageSrcList);
            // 3.2 根据商品id查询评论个数
            Long reviewCount = reviewMapper.selectCount(new QueryWrapper<Review>().eq("reviewProductId", productId));
            productSearchVO.setReviewCount(Math.toIntExact(reviewCount));
            // 3.3 根据商品id查询该商品总成交个数
            Integer totalTransactionCount = productOrderMapper.getTotalTransactionCountByProductId(productId);
            productSearchVO.setTotalTransactionCount(Optional.ofNullable(totalTransactionCount).orElse(0));
            // 3.4 根据商品id获取商品分类名称
            Integer categoryId = productMapper.selectById(productId).getProductCategoryId();
            Category category = categoryMapper.selectById(categoryId);
            String categoryName = category.getCategoryName();
            productSearchVO.setCategoryName(categoryName);
            return productSearchVO;
        }).collect(Collectors.toList());
        productSearchVOPage.setRecords(searchVOList);
        return productSearchVOPage;
    }

    @Override
    public DetailedProductVO getDetailedProduct(Integer productId) {
        return productMapper.getDetailedProduct(productId);
    }

    @Override
    public Page<ProductSearchVO> getProductByCategoryId(ProductCategoryDTO productCategoryDTO) {
        Integer categoryId = productCategoryDTO.getCategoryId();
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("productCategoryId", categoryId);
        queryWrapper.ne("productIsEnabled", 1);
        Page<Product> productPage = productMapper.selectPage(new Page<>(productCategoryDTO.getCurrent(), productCategoryDTO.getPageSize()), queryWrapper);
        Page<ProductSearchVO> productSearchVOPage = new Page<>(productPage.getCurrent(), productPage.getSize(), productPage.getTotal());
        List<ProductSearchVO> productSearchVOList = productMapper.getProductByCategoryId(categoryId);
        productSearchVOPage.setRecords(productSearchVOList);
        return productSearchVOPage;
    }
}




