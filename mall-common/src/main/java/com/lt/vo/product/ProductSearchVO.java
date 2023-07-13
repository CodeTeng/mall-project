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
 * @date: 2023/7/10 9:15
 */
@Data
@ApiModel("搜索商品VO")
public class ProductSearchVO implements Serializable {
    @ApiModelProperty("商品编号")
    private Integer productId;
    @ApiModelProperty("商品名称")
    private String productName;
    @ApiModelProperty("分类名称")
    private String categoryName;
    @ApiModelProperty("商品卖价")
    private BigDecimal productSalePrice;
    @ApiModelProperty("多个商品图片")
    private List<String> productImageSrcList;
    @ApiModelProperty("评论个数")
    private Integer reviewCount;
    @ApiModelProperty("总成交个数")
    private Integer totalTransactionCount;
}
