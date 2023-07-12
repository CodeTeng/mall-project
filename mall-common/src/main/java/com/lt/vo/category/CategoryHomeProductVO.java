package com.lt.vo.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/11 16:43
 */
@ApiModel("主页分类下的商品VO")
@Data
public class CategoryHomeProductVO implements Serializable {
    @ApiModelProperty("商品编号")
    private Integer productId;
    @ApiModelProperty("商品名称")
    private String productName;
    @ApiModelProperty("商品卖价")
    private BigDecimal productSalePrice;
    @ApiModelProperty("可放大的图片地址")
    private List<String> singleProductImageList;
}

