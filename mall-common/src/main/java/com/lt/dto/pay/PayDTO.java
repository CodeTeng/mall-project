package com.lt.dto.pay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/14 10:43
 */
@Data
@ApiModel("支付宝支付DTO")
public class PayDTO implements Serializable {
    @ApiModelProperty("订单编号")
    private Integer productOrderId;
    @ApiModelProperty("支付总金额")
    private BigDecimal money;
    @ApiModelProperty("订单标题")
    private String subject;
}
