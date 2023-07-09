package com.lt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @TableName product
 */
@TableName(value = "product")
@Data
@ApiModel("商品实体")
public class Product implements Serializable {
    @ApiModelProperty("商品编号")
    @TableId(type = IdType.AUTO)
    private Integer productId;
    @ApiModelProperty("商品名称")
    private String productName;
    @ApiModelProperty("商品概述")
    private String productTitle;
    @ApiModelProperty("商品价格")
    private BigDecimal productPrice;
    @ApiModelProperty("商品卖价")
    private BigDecimal productSalePrice;
    @ApiModelProperty("商品上架时间")
    private Date productCreateDate;
    @ApiModelProperty("商品所属分类ID")
    private Integer productCategoryId;
    @ApiModelProperty("商品的状态（0：销售中，1：停售中，2：促销中）")
    private Integer productIsEnabled;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}