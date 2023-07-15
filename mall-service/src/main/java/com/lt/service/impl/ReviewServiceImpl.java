package com.lt.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.constant.CommonConstant;
import com.lt.dto.review.GetProductReviewDTO;
import com.lt.dto.review.ReviewProductDTO;
import com.lt.entity.Review;
import com.lt.entity.User;
import com.lt.mapper.ReviewMapper;
import com.lt.mapper.UserMapper;
import com.lt.service.ReviewService;
import com.lt.utils.UserThreadLocalUtil;
import com.lt.vo.ReviewVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author teng
 * @description 针对表【review】的数据库操作Service实现
 * @createDate 2023-07-09 11:29:57
 */
@Service
@Slf4j
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review>
        implements ReviewService {
    @Resource
    private ReviewMapper reviewMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public void reviewProduct(ReviewProductDTO reviewProductDTO) {
        Review review = new Review();
        BeanUtils.copyProperties(reviewProductDTO, review);
        Integer userId = UserThreadLocalUtil.getUserId();
        review.setReviewUserId(userId);
        review.setReviewCreatedate(new Date());
        this.save(review);
    }

    @Override
    public Integer getProductReviewCount(Integer productId) {
        QueryWrapper<Review> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("reviewProductId", productId);
        return Math.toIntExact(reviewMapper.selectCount(queryWrapper));
    }

    @Override
    public Page<ReviewVO> getPageReviewByProductId(GetProductReviewDTO getProductReviewDTO) {
        Integer productId = getProductReviewDTO.getProductId();
        long current = getProductReviewDTO.getCurrent();
        long pageSize = getProductReviewDTO.getPageSize();
        String sortField = getProductReviewDTO.getSortField();
        String sortOrder = getProductReviewDTO.getSortOrder();
        QueryWrapper<Review> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("reviewProductId", productId);
        queryWrapper.orderBy(true, sortOrder.equals(CommonConstant.SORT_ORDER_ASC), "reviewCreatedate");
        Page<Review> page = new Page<>(current, pageSize);
        Page<Review> reviewPage = reviewMapper.selectPage(page, queryWrapper);
        List<Review> reviewList = reviewPage.getRecords();
        if (CollectionUtil.isEmpty(reviewList)) {
            new Page<>(current, pageSize);
        }
        Page<ReviewVO> reviewVOPage = new Page<>(current, pageSize, page.getTotal());
        List<ReviewVO> reviewVOList = reviewList.stream().map(review -> {
            ReviewVO reviewVO = new ReviewVO();
            BeanUtils.copyProperties(review, reviewVO);
            Integer reviewUserId = review.getReviewUserId();
            User user = userMapper.selectById(reviewUserId);
            reviewVO.setUserNickName(user.getUserNickName());
            return reviewVO;
        }).collect(Collectors.toList());
        reviewVOPage.setRecords(reviewVOList);
        return reviewVOPage;
    }
}




