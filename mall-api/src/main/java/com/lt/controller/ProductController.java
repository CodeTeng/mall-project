package com.lt.controller;

import com.lt.common.BaseResponse;
import com.lt.common.ErrorCode;
import com.lt.common.ResultUtils;
import com.lt.entity.Category;
import com.lt.exception.BusinessException;
import com.lt.service.CategoryService;
import com.lt.service.ProductService;
import com.lt.vo.HomeProductVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
}
