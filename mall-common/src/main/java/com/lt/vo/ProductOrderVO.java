package com.lt.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/10 14:38
 */
@Data
@ApiModel("商品订单数据VO")
public class ProductOrderVO implements Serializable {
    @ApiModelProperty("商品编号")
    private Integer productId;
    @ApiModelProperty("商品订单序列号")
    private Integer productOrderId;
    @ApiModelProperty("商品订单号")
    private String productOrderCode;
    @ApiModelProperty("订单的支付时间")
    private Date productOrderPayDate;
    @ApiModelProperty("商品主图")
    private String productPlanSrc;
    @ApiModelProperty("商品名称")
    private String productName;
    @ApiModelProperty("商品卖价")
    private BigDecimal productSalePrice;
    @ApiModelProperty("商品的数量")
    private Integer productOrderItemNumber;
    @ApiModelProperty("订单价格")
    private BigDecimal productOrderItemPrice;
    @ApiModelProperty("订单状态（0：待买家付款；1：待卖家发货；2：买家确认；3：交易成功")
    private Integer productOrderStatus;
}
