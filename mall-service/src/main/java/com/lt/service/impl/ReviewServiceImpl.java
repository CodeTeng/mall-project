package com.lt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.dto.review.ReviewProductDTO;
import com.lt.entity.Review;
import com.lt.mapper.ReviewMapper;

import com.lt.service.ReviewService;

import com.lt.utils.UserThreadLocalUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author teng
 * @description 针对表【review】的数据库操作Service实现
 * @createDate 2023-07-09 11:29:57
 */
@Service
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review>
        implements ReviewService {

    @Override
    public void reviewProduct(ReviewProductDTO reviewProductDTO) {
        Review review = new Review();
        BeanUtils.copyProperties(reviewProductDTO, review);
        Integer userId = UserThreadLocalUtil.getUserId();
        review.setReviewUserId(userId);
        review.setReviewCreatedate(new Date());
        this.save(review);
    }
}




