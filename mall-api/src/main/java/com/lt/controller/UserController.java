package com.lt.controller;

import cn.hutool.core.util.PhoneUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lt.common.BaseResponse;
import com.lt.common.ErrorCode;
import com.lt.common.ResultUtils;
import com.lt.dto.user.FindPwdDTO;
import com.lt.dto.user.UpdateUserDTO;
import com.lt.dto.user.UserLoginDTO;
import com.lt.dto.user.UserRegisterDTO;
import com.lt.entity.Address;
import com.lt.entity.User;
import com.lt.exception.BusinessException;
import com.lt.service.AddressService;
import com.lt.service.UserService;
import com.lt.utils.UserThreadLocalUtil;
import com.lt.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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
    @Resource
    private AddressService addressService;

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
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName", userName);
        User user = userService.getOne(queryWrapper);
        if (user != null) {
            // 已存在该用户名，不允许重复添加
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "已存在该用户名，请换一个名称");
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

    @PostMapping("/logout")
    @ApiOperation("用户退出")
    public BaseResponse<Boolean> logout(HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        String token = request.getHeader("Authorization");
        servletContext.removeAttribute(token);
        return ResultUtils.success(Boolean.TRUE);
    }

    @GetMapping("/getUserVO")
    @ApiOperation("获取当前登录用户信息")
    public BaseResponse<UserVO> getUserVO() {
        Integer userId = UserThreadLocalUtil.getUserId();
        User user = userService.getById(userId);
        if (user == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "当前用户不存在");
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        String userAddress = user.getUserAddress();
        Address address = addressService.getById(userAddress);
        String userProvince = address.getAddressRegionId();
        userVO.setUserProvince(userProvince);
        return ResultUtils.success(userVO);
    }

    @PostMapping("/updateUser")
    @ApiOperation("修改个人信息")
    public BaseResponse<Boolean> updateUser(@RequestBody UpdateUserDTO updateUserDTO) {
        if (updateUserDTO == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userNickName = updateUserDTO.getUserNickName();
        String userRealname = updateUserDTO.getUserRealname();
        String userPassword = updateUserDTO.getUserPassword();
        String confirmPassword = updateUserDTO.getConfirmPassword();
        Integer userGender = updateUserDTO.getUserGender();
        Date userBirthday = updateUserDTO.getUserBirthday();
        String userAddress = updateUserDTO.getUserAddress();
        String userHomePlace = updateUserDTO.getUserHomePlace();
        if (StringUtils.isAnyBlank(userNickName, userRealname, userPassword, confirmPassword, userAddress, userHomePlace)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请填写完整的用户信息");
        }
        if (userGender == null || userBirthday == null || (userGender != 0 && userGender != 1)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请填写完整的用户信息");
        }
        if (!userPassword.equals(confirmPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "登录密码与确认密码不相同");
        }
        Integer userId = UserThreadLocalUtil.getUserId();
        User user = userService.getById(userId);
        if (user == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "当前用户不存在");
        }
        BeanUtils.copyProperties(updateUserDTO, user);
        userPassword = DigestUtils.md5DigestAsHex(userPassword.getBytes(StandardCharsets.UTF_8));
        user.setUserPassword(userPassword);
        userService.updateById(user);
        return ResultUtils.success(Boolean.TRUE);
    }

    @PostMapping("/findPassword")
    @ApiOperation("找回密码")
    public BaseResponse<String> findPassword(@RequestBody FindPwdDTO findPwdDTO) {
        if (findPwdDTO == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String mobile = findPwdDTO.getMobile();
        String userName = findPwdDTO.getUserName();
        if (StringUtils.isAnyBlank(userName, mobile)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请填写完整的用户信息");
        }
        if (!PhoneUtil.isMobile(mobile)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请填写正确的手机号");
        }
        userService.findPassword(userName, mobile);
        return ResultUtils.success("你的新密码已发送至手机号，请注意查收");
    }
}
