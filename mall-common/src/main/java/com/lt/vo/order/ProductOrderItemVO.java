package com.lt.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/12 18:26
 */
@Data
@ApiModel("商品订单项VO")
public class ProductOrderItemVO implements Serializable {
    @ApiModelProperty("商品订单项编号")
    private Integer productOrderItemId;
    @ApiModelProperty("商品的数量")
    private Integer productOrderItemNumber;
    @ApiModelProperty("订单价格")
    private BigDecimal productOrderItemPrice;
    @ApiModelProperty("订单项对应的商品")
    private ProductOrderItemProductVO productOrderItemProductVO;
}
