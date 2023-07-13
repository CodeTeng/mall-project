package com.lt.vo.order;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/10 14:38
 */
@Data
@ApiModel("商品订单数据VO")
public class ProductOrderVO implements Serializable {
    @ApiModelProperty("商品订单编号")
    private Integer productOrderId;
    @ApiModelProperty("商品订单号")
    private String productOrderCode;
    @ApiModelProperty("订单的支付时间")
    private Date productOrderPayDate;
    @ApiModelProperty("订单状态（0：待买家付款；1：待卖家发货；2：买家确认；3：交易成功； 4：取消交易")
    private Integer productOrderStatus;
    @ApiModelProperty("商品订单项")
    private List<ProductOrderItemVO> productOrderIItemList;
}
