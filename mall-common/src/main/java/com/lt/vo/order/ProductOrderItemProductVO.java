package com.lt.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/12 18:28
 */
@Data
@ApiModel("订单项对应的商品VO")
public class ProductOrderItemProductVO implements Serializable {
    @ApiModelProperty("商品编号")
    private Integer productId;
    @ApiModelProperty("商品名称")
    private String productName;
    @ApiModelProperty("商品卖价")
    private BigDecimal productSalePrice;
    @ApiModelProperty("商品可放大图片")
    private String productImageSrc;
}
