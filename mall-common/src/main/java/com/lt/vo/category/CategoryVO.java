package com.lt.vo.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
* @description:
* @author: ~Teng~
* @date: 2023/7/11 15:55
*/
@ApiModel("分类商品VO")
@Data
public class CategoryVO implements Serializable {
    @ApiModelProperty("分类id")
    private Integer categoryId;
    @ApiModelProperty("分类名称")
    private String categoryName;
}
