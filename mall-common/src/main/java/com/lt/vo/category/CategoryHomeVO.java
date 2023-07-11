package com.lt.vo.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/11 16:41
 */
@Data
@ApiModel("主页分类商品VO")
public class CategoryHomeVO implements Serializable {
    @ApiModelProperty("分类的编号")
    private Integer categoryId;
    @ApiModelProperty("分类名称")
    private String categoryName;
    @ApiModelProperty("分类的图片地址")
    private String categoryImageSrc;
    @ApiModelProperty("分类下的商品信息")
    private List<CategoryHomeProductVO> categoryHomeProductVOList;
}
