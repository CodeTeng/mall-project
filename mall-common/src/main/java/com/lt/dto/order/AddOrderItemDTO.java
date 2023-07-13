package com.lt.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/13 18:24
 */
@Data
@ApiModel("添加订单留言DTO")
public class AddOrderItemDTO implements Serializable {
    @ApiModelProperty(value = "订单项编号", required = true)
    private Integer productOrderItemId;
    @ApiModelProperty("订单用户留言")
    private String productOrderItemUserMessage;
}
