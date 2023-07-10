package com.lt.dto.review;

import com.lt.common.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/10 20:19
 */
@Data
@ApiModel("获取商品评价DTO")
public class GetProductReviewDTO extends PageRequest implements Serializable {
    @ApiModelProperty(value = "商品Id", required = true)
    private Integer productId;
}
