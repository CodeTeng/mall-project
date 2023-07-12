package com.lt.dto.product;

import com.lt.common.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/12 14:32
 */
@Data
@ApiModel("商品分类DTO")
public class ProductCategoryDTO extends PageRequest implements Serializable {
    @ApiModelProperty(value = "商品分类id", required = true)
    private Integer categoryId;
}
