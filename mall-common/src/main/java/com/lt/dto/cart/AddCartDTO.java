package com.lt.dto.cart;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/13 16:35
 */
@Data
@ApiModel("添加购物车DTO")
public class AddCartDTO implements Serializable {
    @ApiModelProperty(value = "商品编号", required = true)
    private Integer productId;
    @ApiModelProperty(value = "商品单价", required = true)
    private BigDecimal productSalePrice;
    @ApiModelProperty(value = "商品的数量", required = true)
    private Integer productOrderItemNumber;
}
