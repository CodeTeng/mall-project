package com.lt.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/14 22:36
 */
@Data
@ApiModel("订单时间信息VO")
public class ProductOrderTimeVO implements Serializable {
    @ApiModelProperty("订单的支付时间")
    private Date productOrderPayDate;
    @ApiModelProperty("订单的投递时间")
    private Date productOrderDeliveryDate;
    @ApiModelProperty("订单的确认时间")
    private Date productOrderConfirmDate;
}
