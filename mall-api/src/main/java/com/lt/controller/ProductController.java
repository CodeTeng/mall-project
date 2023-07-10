package com.lt.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lt.common.BaseResponse;
import com.lt.common.ErrorCode;
import com.lt.common.ResultUtils;
import com.lt.dto.product.ProductSearchDTO;
import com.lt.entity.Category;
import com.lt.entity.Product;
import com.lt.exception.BusinessException;
import com.lt.mapper.PropertyValueMapper;
import com.lt.service.CategoryService;
import com.lt.service.ProductService;
import com.lt.vo.HomeProductVO;
import com.lt.vo.ProductParameterVO;
import com.lt.vo.ProductSearchVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/9 20:53
 */
@RestController
@RequestMapping("/product")
@Api(tags = "商品控制器")
public class ProductController {
    @Resource
    private ProductService productService;
    @Resource
    private CategoryService categoryService;

    @GetMapping("/getHomeProduct")
    @ApiOperation("获取主页分类下的商品")
    public BaseResponse<HomeProductVO> getHomeProductList(Integer categoryId) {
        if (categoryId == null || categoryId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Category category = categoryService.getById(categoryId);
        if (category == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "该分类不存在");
        }
        // 获取分类下的图片
        String categoryImageSrc = category.getCategoryImageSrc();
        // 查询分类下的一些商品
        HomeProductVO homeProductVO = productService.getHomeProductList(categoryId);
        homeProductVO.setCategoryImageSrc(categoryImageSrc);
        return ResultUtils.success(homeProductVO);
    }

    @GetMapping("/search")
    @ApiOperation(value = "主页商品分页搜索", notes = "根据商品名称模糊搜索，可根据价格和时间排序")
    public BaseResponse<Page<ProductSearchVO>> search(ProductSearchDTO productSearchDTO) {
        if (productSearchDTO == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Page<ProductSearchVO> searchVOPage = productService.search(productSearchDTO);
        return ResultUtils.success(searchVOPage);
    }

    @GetMapping("/getProductParameter")
    @ApiOperation("根据商品id获取商品参数")
    public BaseResponse<List<ProductParameterVO>> getProductParameter(@RequestParam("productId") Integer productId) {
        if (productId == null || productId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        List<ProductParameterVO> productParameterVOList = productService.getProductParameter(productId);
        return ResultUtils.success(productParameterVOList);
    }

    @GetMapping("/getProductById")
    @ApiOperation("根据商品id获取商品信息")
    public BaseResponse<Product> getProductById(@RequestParam("productId") Integer productId) {
        if (productId == null || productId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Product product = productService.getById(productId);
        if (product == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "该商品不存在");
        }
        return ResultUtils.success(product);
    }
}
