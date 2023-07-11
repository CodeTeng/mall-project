package com.lt.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/11 14:38
 */
@Data
@ApiModel("分类商品VO")
public class CategoryProductVO implements Serializable {
    @ApiModelProperty("商品概述")
    private String productTitle;
    @ApiModelProperty("商品的状态（0：销售中，1：停售中，2：促销中）")
    private Integer productIsEnabled;
}
