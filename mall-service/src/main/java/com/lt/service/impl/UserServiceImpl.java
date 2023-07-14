package com.lt.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.common.ErrorCode;
import com.lt.dto.user.UserLoginDTO;
import com.lt.dto.user.UserRegisterDTO;
import com.lt.entity.User;
import com.lt.exception.BusinessException;
import com.lt.mapper.UserMapper;
import com.lt.service.SmsService;
import com.lt.service.UserService;
import com.lt.utils.JwtUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * @author teng
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-07-09 11:29:57
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private SmsService smsService;

    @Override
    public Integer register(UserRegisterDTO userRegisterDTO) {
        User user = new User();
        BeanUtils.copyProperties(userRegisterDTO, user);
        String userPassword = userRegisterDTO.getUserPassword();
        userPassword = DigestUtils.md5DigestAsHex(userPassword.getBytes(StandardCharsets.UTF_8));
        user.setUserPassword(userPassword);
        // 插入数据
        userMapper.insert(user);
        return user.getUserId();
    }

    @Override
    public String login(UserLoginDTO userLoginDTO) {
        String userName = userLoginDTO.getUserName();
        String userPassword = userLoginDTO.getUserPassword();
        userPassword = DigestUtils.md5DigestAsHex(userPassword.getBytes(StandardCharsets.UTF_8));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName", userName);
        queryWrapper.eq("userPassword", userPassword);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "用户名或密码错误");
        }
        // 生成token
        String token = JwtUtil.getToken(user.getUserId());
        if (StringUtils.isBlank(token)) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "登录失败");
        }
        return token;
    }

    @Override
    public void findPassword(String userName, String mobile) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName", userName);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "该用户不存在，请先注册");
        }
        // 发送手机验证码
        // 1. 随机生成6位号码
        String code = RandomStringUtils.randomNumeric(6);
        // 2. 发送短信
        smsService.sendSms(mobile, code);
        // 3. 更改用户密码
        user.setUserPassword(DigestUtils.md5DigestAsHex(code.getBytes(StandardCharsets.UTF_8)));
        userMapper.updateById(user);
    }
}




