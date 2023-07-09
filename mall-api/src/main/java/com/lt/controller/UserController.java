package com.lt.controller;

import com.lt.common.BaseResponse;
import com.lt.common.ErrorCode;
import com.lt.common.ResultUtils;
import com.lt.dto.user.UserLoginDTO;
import com.lt.dto.user.UserRegisterDTO;
import com.lt.exception.BusinessException;
import com.lt.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/9 15:46
 */
@RestController
@RequestMapping("/user")
@Api(tags = "前台用户控制器")
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public BaseResponse<Integer> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        if (userRegisterDTO == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userName = userRegisterDTO.getUserName();
        String userPassword = userRegisterDTO.getUserPassword();
        String confirmPassword = userRegisterDTO.getConfirmPassword();
        String userNickName = userRegisterDTO.getUserNickName();
        Integer userGender = userRegisterDTO.getUserGender();
        Date userBirthday = userRegisterDTO.getUserBirthday();
        String userAddress = userRegisterDTO.getUserAddress();
        String userHomePlace = userRegisterDTO.getUserHomePlace();
        if (StringUtils.isAnyBlank(userName, userPassword, confirmPassword, userNickName, userAddress, userHomePlace)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请填写完整的信息");
        }
        if (userGender == null || userBirthday == null || (userGender != 0 && userGender != 1)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请填写完整的信息");
        }
        if (!userPassword.equals(confirmPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "登录密码与确认密码不相同");
        }
        Integer userId = userService.register(userRegisterDTO);
        return ResultUtils.success(userId);
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public BaseResponse<String> login(@RequestBody UserLoginDTO userLoginDTO) {
        if (userLoginDTO == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userName = userLoginDTO.getUserName();
        String userPassword = userLoginDTO.getUserPassword();
        if (StringUtils.isAnyBlank(userName, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请填写用户名或密码");
        }
        String token = userService.login(userLoginDTO);
        return ResultUtils.success(token);
    }

}
