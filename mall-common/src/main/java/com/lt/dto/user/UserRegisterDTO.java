package com.lt.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/9 17:34
 */
@Data
@ApiModel("用户注册请求体")
public class UserRegisterDTO implements Serializable {
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("用户密码")
    private String userPassword;
    @ApiModelProperty("确认密码")
    private String confirmPassword;
    @ApiModelProperty("用户昵称")
    private String userNickName;
    @ApiModelProperty("性别")
    private Integer userGender;
    @ApiModelProperty("出生日期")
    private Date userBirthday;
    @ApiModelProperty("所在城市")
    private String userAddress;
    @ApiModelProperty("所在城区")
    private String userHomePlace;
}
