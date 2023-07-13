package com.lt.vo.cart;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/13 15:00
 */
@Data
@ApiModel("购物车VO")
public class CartVO implements Serializable {
    @ApiModelProperty("订单项编号")
    private Integer productOrderItemId;
    @ApiModelProperty("商品的数量")
    private Integer productOrderItemNumber;
    @ApiModelProperty("订单价格")
    private BigDecimal productOrderItemPrice;
    @ApiModelProperty("商品编号")
    private Integer productId;
    @ApiModelProperty("商品名称")
    private String productName;
    @ApiModelProperty("商品卖价")
    private BigDecimal productSalePrice;
    @ApiModelProperty("分类的编号")
    private Integer categoryId;
    @ApiModelProperty("分类名称")
    private String categoryName;
    @ApiModelProperty("商品可放大图片")
    private String productImageSrc;
}
