package com.lt.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.entity.User;
import com.lt.mapper.UserMapper;

import com.lt.service.UserService;

import org.springframework.stereotype.Service;

/**
 * @author teng
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-07-09 11:29:57
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

}




