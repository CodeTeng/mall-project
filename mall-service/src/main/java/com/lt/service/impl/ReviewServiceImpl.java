package com.lt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.entity.Review;
import com.lt.mapper.ReviewMapper;

import com.lt.service.ReviewService;

import org.springframework.stereotype.Service;

/**
 * @author teng
 * @description 针对表【review】的数据库操作Service实现
 * @createDate 2023-07-09 11:29:57
 */
@Service
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review>
        implements ReviewService {

}




