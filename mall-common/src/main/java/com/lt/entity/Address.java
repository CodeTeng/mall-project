package com.lt.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @TableName address
 */
@TableName(value = "address")
@Data
@ApiModel("地址实体")
public class Address implements Serializable {
    @TableId
    @ApiModelProperty("地址编号")
    private String addressAreaId;
    @ApiModelProperty("地址名称")
    private String addressName;
    @ApiModelProperty("所属地的地址编号")
    private String addressRegionId;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}