package com.lt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @TableName productOrderItem
 */
@TableName(value = "productOrderItem")
@Data
@ApiModel("商品订单项实体")
public class ProductOrderItem implements Serializable {
    @ApiModelProperty("订单项编号")
    @TableId(type = IdType.AUTO)
    private Integer productOrderItemId;
    @ApiModelProperty("商品的数量")
    private Integer productOrderItemNumber;
    @ApiModelProperty("订单价格")
    private BigDecimal productOrderItemPrice;
    @ApiModelProperty("订单中的商品编号")
    private Integer productOrderItemProductId;
    @ApiModelProperty("商品所属订单的编号")
    private Integer productOrderItemOrderId;
    @ApiModelProperty("商品订单所属用户编号")
    private Integer productOrderItemUserId;
    @ApiModelProperty("订单用户留言")
    private String productOrderItemUserMessage;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}