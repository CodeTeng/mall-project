package com.lt.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/9 19:52
 */
@Data
@ApiModel("修改用户信息请求体")
public class UpdateUserDTO implements Serializable {
    @ApiModelProperty("用户昵称")
    private String userNickName;
    @ApiModelProperty("用户密码")
    private String userPassword;
    @ApiModelProperty("确认密码")
    private String confirmPassword;
    @ApiModelProperty("真实姓名")
    private String userRealname;
    @ApiModelProperty("性别")
    private Integer userGender;
    @ApiModelProperty("出生日期")
    private Date userBirthday;
    @ApiModelProperty("所在城市")
    private String userAddress;
    @ApiModelProperty("所在城区")
    private String userHomePlace;
    @ApiModelProperty("头像地址")
    private String userProfilePictureSrc;
}
