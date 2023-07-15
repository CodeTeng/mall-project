package com.lt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/15 20:32
 */
@TableName(value = "sys_log")
@Data
@ApiModel("系统日志表")
public class SysLog implements Serializable {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("主键id")
    private Long id;
    @ApiModelProperty("请求路径")
    private String requestUrl;
    @ApiModelProperty("请求参数")
    private String requestParam;
    @ApiModelProperty("请求ip-IPV4")
    private String requestIp;
    @ApiModelProperty("耗时，单位毫秒")
    private Long cost;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
