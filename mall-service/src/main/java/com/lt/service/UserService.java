package com.lt.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lt.dto.user.UserLoginDTO;
import com.lt.dto.user.UserRegisterDTO;
import com.lt.entity.User;

/**
 * @author teng
 * @description 针对表【user】的数据库操作Service
 * @createDate 2023-07-09 11:29:57
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userRegisterDTO 用户注册请求体
     * @return 新用户id
     */
    Integer register(UserRegisterDTO userRegisterDTO);

    /**
     * 用户登录
     *
     * @param userLoginDTO 用户登录请求体
     * @return 用户token
     */
    String login(UserLoginDTO userLoginDTO);

    /**
     * 找回密码
     *
     * @param userName 用户名
     * @param mobile   手机号
     */
    void findPassword(String userName, String mobile);
}
