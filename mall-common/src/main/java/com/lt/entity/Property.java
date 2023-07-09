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
 * @TableName property
 */
@TableName(value = "property")
@Data
@ApiModel("商品属性实体")
public class Property implements Serializable {
    @ApiModelProperty("商品属性编号")
    @TableId(type = IdType.AUTO)
    private Integer propertyId;
    @ApiModelProperty("属性名称")
    private String propertyName;
    @ApiModelProperty("属性分类编号")
    private Integer propertyCategoryId;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}