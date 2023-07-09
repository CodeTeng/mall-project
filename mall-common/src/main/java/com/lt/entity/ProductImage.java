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
 * @TableName productImage
 */
@TableName(value = "productImage")
@Data
@ApiModel("商品图片")
public class ProductImage implements Serializable {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("图片编号")
    private Integer productImageId;
    @ApiModelProperty("图片类型")
    private Integer productImageType;
    @ApiModelProperty("图片地址")
    private String productImageSrc;
    @ApiModelProperty("图片所属的商品id")
    private Integer productImageProductId;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}