package com.lt.vo.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/10 21:03
 */
@Data
@ApiModel("商品参数VO")
public class ProductParameter implements Serializable {
    @ApiModelProperty("属性名称")
    private String propertyName;
    @ApiModelProperty("商品属性值")
    private String propertyValueValue;
}
