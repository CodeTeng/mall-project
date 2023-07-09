package com.lt.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/9 21:04
 */
@Data
@ApiModel("主页分类下的商品")
public class ProductCategoryVO implements Serializable {
    @ApiModelProperty("商品编号")
    private Integer productId;
    @ApiModelProperty("商品名称")
    private String productName;
    @ApiModelProperty("商品卖价")
    private BigDecimal productSalePrice;
    @ApiModelProperty("图片地址")
    private String productImageSrc;
}
