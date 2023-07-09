package com.lt.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/9 19:40
 */
@Data
@ApiModel("用户返回VO")
public class UserVO implements Serializable {
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("用户昵称")
    private String userNickName;
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
