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
 * @TableName propertyValue
 */
@TableName(value = "propertyValue")
@Data
@ApiModel("商品属性值实体")
public class PropertyValue implements Serializable {
    @ApiModelProperty("商品属性值编号")
    @TableId(type = IdType.AUTO)
    private Integer propertyValueId;
    @ApiModelProperty("商品属性值")
    private String propertyValueValue;
    @ApiModelProperty("商品属性值编号")
    private Integer propertyValuePropertyId;
    @ApiModelProperty("属性所属商品编号")
    private Integer propertyValueProductId;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}