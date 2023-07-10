package com.lt.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lt.common.BaseResponse;
import com.lt.common.ErrorCode;
import com.lt.common.ResultUtils;
import com.lt.dto.review.GetProductReviewDTO;
import com.lt.dto.review.ReviewProductDTO;
import com.lt.exception.BusinessException;
import com.lt.service.ReviewService;
import com.lt.vo.ReviewVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/10 18:59
 */
@RestController
@RequestMapping("/review")
@Api(tags = "评论控制器")
public class ReviewController {
    @Resource
    private ReviewService reviewService;

    @PostMapping("/reviewProduct")
    @ApiOperation("评价商品")
    public BaseResponse<Boolean> reviewProduct(@RequestBody ReviewProductDTO reviewProductDTO) {
        if (reviewProductDTO == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String reviewContent = reviewProductDTO.getReviewContent();
        Integer reviewProductId = reviewProductDTO.getReviewProductId();
        Integer reviewOrderItemId = reviewProductDTO.getReviewOrderItemId();
        if (StringUtils.isBlank(reviewContent)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "评价内容不能为空");
        }
        if (reviewProductId == null || reviewOrderItemId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        reviewService.reviewProduct(reviewProductDTO);
        return ResultUtils.success(Boolean.TRUE);
    }

    @GetMapping("/getProductReviewCount")
    @ApiOperation("获取商品评论总数")
    public BaseResponse<Long> getProductReviewCount(@RequestParam(value = "productId") Integer productId) {
        if (productId == null || productId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "商品Id参数错误");
        }
        Long count = reviewService.getProductReviewCount(productId);
        return ResultUtils.success(count);
    }

    @GetMapping("/getPageReviewByProductId")
    @ApiOperation("根据商品id分页获取评论数据")
    public BaseResponse<Page<ReviewVO>> getPageReviewByProductId(GetProductReviewDTO getProductReviewDTO) {
        if (getProductReviewDTO == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Integer productId = getProductReviewDTO.getProductId();
        if (productId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Page<ReviewVO> reviewVOPage = reviewService.getPageReviewByProductId(getProductReviewDTO);
        return ResultUtils.success(reviewVOPage);
    }
}
