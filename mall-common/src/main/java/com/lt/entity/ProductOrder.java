package com.lt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @TableName productOrder
 */
@TableName(value = "productOrder")
@Data
@ApiModel("商品订单实体")
public class ProductOrder implements Serializable {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("商品订单序列号")
    private Integer productOrderId;
    @ApiModelProperty("商品订单号")
    private String productOrderCode;
    @ApiModelProperty("订单地址")
    private String productOrderAddress;
    @ApiModelProperty("订单详细地址")
    private String productOrderDetailAddress;
    @ApiModelProperty("商品的邮政编号")
    private String productOrderPost;
    @ApiModelProperty("订单接收人")
    private String productOrderReceiver;
    @ApiModelProperty("订单的联系电话")
    private String productOrderMobile;
    @ApiModelProperty("订单的支付时间")
    private Date productOrderPayDate;
    @ApiModelProperty("订单的投递时间")
    private Date productOrderDeliveryDate;
    @ApiModelProperty("订单的确认时间")
    private Date productOrderConfirmDate;
    @ApiModelProperty("订单状态（0：待买家付款；1：待卖家发货；2：买家确认；3：交易成功")
    private Integer productOrderStatus;
    @ApiModelProperty("下订单人的编号")
    private Integer productOrderUserId;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}