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
 * @TableName review
 */
@TableName(value = "review")
@Data
@ApiModel("评论实体")
public class Review implements Serializable {
    @ApiModelProperty("评论编号")
    @TableId(type = IdType.AUTO)
    private Integer reviewId;
    @ApiModelProperty("评论内容")
    private String reviewContent;
    @ApiModelProperty("评论时间")
    private Date reviewCreatedate;
    @ApiModelProperty("评论人编号")
    private Integer reviewUserId;
    @ApiModelProperty("评论的商品编号")
    private Integer reviewProductId;
    @ApiModelProperty("评论的订单编号")
    private Integer reviewOrderItemId;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}