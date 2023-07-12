package com.lt.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/12 19:20
 */
@Data
@ApiModel("更改订单状态DTO")
public class UpdateOrderDTO implements Serializable {
    @ApiModelProperty(value = "商品订单编号", required = true)
    private Integer productOrderId;
    @ApiModelProperty(value = "订单状态（0：待买家付款；1：待卖家发货；2：买家确认；3：交易成功； 4：取消交易", required = true)
    private Integer productOrderStatus;
}
