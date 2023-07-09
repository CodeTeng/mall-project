package com.lt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @TableName user
 */
@TableName(value = "user")
@Data
@ApiModel("用户实体")
public class User implements Serializable {
    @ApiModelProperty("用户编号")
    @TableId(type = IdType.AUTO)
    private Integer userId;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("用户昵称")
    private String userNickName;
    @ApiModelProperty("用户密码")
    private String userPassword;
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
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}