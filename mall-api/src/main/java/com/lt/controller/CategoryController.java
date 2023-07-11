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
import com.lt.vo.category.CategoryVO;
import io.swagger.annotations.Api;
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
    @ApiOperation(value = "获取商品的分类信息", notes = "type:1 查询上方分类 type:2查询侧边分类")
    public BaseResponse<List<CategoryVO>> list(@RequestParam("type") Integer type) {
        if (type == null || (type != 1 && type != 2)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        List<Category> categoryList = categoryService.list();
        List<CategoryVO> categoryVOList = categoryList.stream().map(category -> {
            CategoryVO categoryVO = new CategoryVO();
            BeanUtils.copyProperties(category, categoryVO);
            return categoryVO;
        }).collect(Collectors.toList());
        if (type == 1) {
            categoryVOList = categoryVOList.stream().limit(8).collect(Collectors.toList());
            categoryVOList.forEach(categoryVO -> {
                String categoryName = categoryVO.getCategoryName();
                String prefixCategoryName = categoryName.split("/")[0];
                categoryVO.setCategoryName(prefixCategoryName);
            });
            return ResultUtils.success(categoryVOList);
        }
        return ResultUtils.success(categoryVOList);
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
