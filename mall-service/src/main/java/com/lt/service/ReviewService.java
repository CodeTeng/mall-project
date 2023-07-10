package com.lt.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lt.dto.review.GetProductReviewDTO;
import com.lt.dto.review.ReviewProductDTO;
import com.lt.entity.Review;
import com.lt.vo.ReviewVO;

/**
 * @author teng
 * @description 针对表【review】的数据库操作Service
 * @createDate 2023-07-09 11:29:57
 */
public interface ReviewService extends IService<Review> {

    /**
     * 评价商品
     *
     * @param reviewProductDTO 评价商品DTO
     */
    void reviewProduct(ReviewProductDTO reviewProductDTO);

    /**
     * 获取商品评论总数
     *
     * @param productId 商品id
     * @return 商品评论总数
     */
    Long getProductReviewCount(Integer productId);

    /**
     * 根据商品id分页获取评论数据
     *
     * @param getProductReviewDTO 分页DTO
     * @return 评论数据
     */
    Page<ReviewVO> getPageReviewByProductId(GetProductReviewDTO getProductReviewDTO);
}
