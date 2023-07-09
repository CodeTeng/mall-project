package com.lt.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.entity.Admin;
import com.lt.mapper.AdminMapper;

import com.lt.service.AdminService;

import org.springframework.stereotype.Service;

/**
 * @author teng
 * @description 针对表【admin】的数据库操作Service实现
 * @createDate 2023-07-09 11:29:57
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
        implements AdminService {

}




