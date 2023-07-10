package com.lt.controller;

import com.lt.common.BaseResponse;
import com.lt.common.ErrorCode;
import com.lt.common.ResultUtils;
import com.lt.dto.review.ReviewProductDTO;
import com.lt.exception.BusinessException;
import com.lt.service.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
