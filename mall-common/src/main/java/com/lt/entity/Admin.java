package com.lt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @TableName admin
 */
@TableName(value = "admin")
@Data
@ApiModel("后台管理员实体")
public class Admin implements Serializable {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("管理员编号")
    private Integer adminId;
    @ApiModelProperty("管理员名称")
    private String adminName;
    @ApiModelProperty("管理员昵称")
    private String adminNickname;
    @ApiModelProperty("管理员密码")
    private String adminPassword;
    @ApiModelProperty("管理员头像地址")
    private String adminProfilePictureSrc;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}