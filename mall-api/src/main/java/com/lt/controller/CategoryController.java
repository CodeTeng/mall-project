package com.lt.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lt.common.BaseResponse;
import com.lt.common.ErrorCode;
import com.lt.common.ResultUtils;
import com.lt.entity.Category;
import com.lt.entity.Product;
import com.lt.exception.BusinessException;
import com.lt.service.CategoryService;
import com.lt.service.ProductService;
import com.lt.vo.CategoryProductVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/11 14:25
 */
@RestController
@RequestMapping("/category")
@Api(tags = "分类控制器")
public class CategoryController {
    @Resource
    private CategoryService categoryService;
    @Resource
    private ProductService productService;

    @GetMapping("/list")
    @ApiOperation("获取所有的分类信息")
    public BaseResponse<List<Category>> list() {
        return ResultUtils.success(categoryService.list());
    }

    @GetMapping("/productListById")
    @ApiOperation(value = "根据分类id获取商品", notes = "分类id不能为空")
    public BaseResponse<List<CategoryProductVO>> ProductListById(@RequestParam("categoryId") String categoryId) {
        if (StringUtils.isBlank(categoryId)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("productCategoryId", categoryId);
        queryWrapper.ne("productIsEnabled", 1);
        List<Product> productList = productService.list(queryWrapper);
        List<CategoryProductVO> categoryProductVOList = productList.stream().map(product -> {
            CategoryProductVO categoryProductVO = new CategoryProductVO();
            BeanUtils.copyProperties(product, categoryProductVO);
            return categoryProductVO;
        }).collect(Collectors.toList());
        return ResultUtils.success(categoryProductVOList);
    }
}
