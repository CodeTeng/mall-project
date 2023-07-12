package com.lt.vo.product;

import com.lt.entity.ProductImage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/12 10:48
 */
@Data
@ApiModel("详细商品VO")
public class DetailedProductVO implements Serializable {
    @ApiModelProperty("商品编号")
    private Integer productId;
    @ApiModelProperty("商品名称")
    private String productName;
    @ApiModelProperty("商品价格")
    private BigDecimal productPrice;
    @ApiModelProperty("商品卖价")
    private BigDecimal productSalePrice;
    @ApiModelProperty("总销量")
    private Integer totalSales;
    @ApiModelProperty("评论个数")
    private Integer reviewCount;
    @ApiModelProperty("商品放大图片地址")
    private List<ProductImage> singleProductImageList;
    @ApiModelProperty("商品不可放大图片地址")
    private List<ProductImage> detailProductImageList;
    @ApiModelProperty("商品参数")
    private List<ProductParameter> productParameterList;
}
