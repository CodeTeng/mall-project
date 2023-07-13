package com.lt.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/13 18:11
 */
@Data
@ApiModel("添加订单DTO")
public class AddOrderDTO implements Serializable {
    @ApiModelProperty(value = "订单所在区地址", required = true)
    private String productOrderAddress;
    @ApiModelProperty(value = "订单详细地址", required = true)
    private String productOrderDetailAddress;
    @ApiModelProperty("商品的邮政编号")
    private String productOrderPost;
    @ApiModelProperty(value = "订单接收人", required = true)
    private String productOrderReceiver;
    @ApiModelProperty(value = "订单的联系电话", required = true)
    private String productOrderMobile;
    @ApiModelProperty(value = "添加订单中购物车中的信息", required = true)
    private List<AddOrderItemDTO> addOrderItemDTOList;
}
