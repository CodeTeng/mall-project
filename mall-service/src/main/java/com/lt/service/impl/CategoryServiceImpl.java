package com.lt.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.entity.Category;
import com.lt.mapper.CategoryMapper;

import com.lt.service.CategoryService;

import org.springframework.stereotype.Service;

/**
 * @author teng
 * @description 针对表【category】的数据库操作Service实现
 * @createDate 2023-07-09 11:29:57
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
        implements CategoryService {

}




