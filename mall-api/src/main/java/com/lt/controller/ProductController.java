package com.lt.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lt.common.BaseResponse;
import com.lt.common.ErrorCode;
import com.lt.common.ResultUtils;
import com.lt.dto.product.ProductCategoryDTO;
import com.lt.dto.product.ProductSearchDTO;
import com.lt.exception.BusinessException;
import com.lt.service.ProductOrderItemService;
import com.lt.service.ProductService;
import com.lt.service.ReviewService;
import com.lt.vo.ProductSearchVO;
import com.lt.vo.product.DetailedProductVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
    private ReviewService reviewService;
    @Resource
    private ProductOrderItemService productOrderItemService;

    @GetMapping("/search")
    @ApiOperation(value = "商品分页搜索", notes = "根据商品名称模糊搜索，可根据价格和时间排序")
    public BaseResponse<Page<ProductSearchVO>> search(ProductSearchDTO productSearchDTO) {
        if (productSearchDTO == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Page<ProductSearchVO> searchVOPage = productService.search(productSearchDTO);
        return ResultUtils.success(searchVOPage);
    }

    @GetMapping("/getDetailedProduct/{productId}")
    @ApiOperation("根据商品id获取详细商品信息")
    public BaseResponse<DetailedProductVO> getDetailedProduct(@PathVariable("productId") Integer productId) {
        if (productId == null || productId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        DetailedProductVO detailedProductVO = productService.getDetailedProduct(productId);
        Integer reviewCount = reviewService.getProductReviewCount(productId);
        Integer totalSales = productOrderItemService.getTotalSales(productId);
        detailedProductVO.setTotalSales(totalSales);
        detailedProductVO.setReviewCount(reviewCount);
        return ResultUtils.success(detailedProductVO);
    }

    @GetMapping("/getProductByCategoryId")
    @ApiOperation(value = "根据分类id分页查询商品信息", notes = "只能分页查询,不能进行排序")
    public BaseResponse<Page<ProductSearchVO>> getProductByCategoryId(ProductCategoryDTO productCategoryDTO) {
        if (productCategoryDTO == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Page<ProductSearchVO> productSearchVOPage = productService.getProductByCategoryId(productCategoryDTO);
        return ResultUtils.success(productSearchVOPage);
    }
}
