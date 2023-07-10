package com.lt.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lt.dto.review.ReviewProductDTO;
import com.lt.entity.Review;

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
}
