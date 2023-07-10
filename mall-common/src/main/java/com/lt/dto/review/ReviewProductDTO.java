package com.lt.dto.review;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/10 19:01
 */
@Data
@ApiModel("评价商品DTO")
public class ReviewProductDTO implements Serializable {
    @ApiModelProperty(value = "评论内容", required = true)
    private String reviewContent;
    @ApiModelProperty(value = "评论的商品编号", required = true)
    private Integer reviewProductId;
    @ApiModelProperty(value = "评论的订单编号", required = true)
    private Integer reviewOrderItemId;
}
