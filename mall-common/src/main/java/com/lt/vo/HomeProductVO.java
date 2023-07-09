package com.lt.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/9 20:54
 */
@Data
@ApiModel("主页商品VO")
public class HomeProductVO implements Serializable {
    @ApiModelProperty("分类的图片地址")
    private String categoryImageSrc;
    @ApiModelProperty("分类下的商品信息")
    private List<ProductCategoryVO> productCategoryVOList;
}
