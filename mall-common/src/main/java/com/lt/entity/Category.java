package com.lt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @TableName category
 */
@TableName(value = "category")
@Data
@ApiModel("分类实体")
public class Category implements Serializable {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("分类的编号")
    private Integer categoryId;
    @ApiModelProperty("分类名称")
    private String categoryName;
    @ApiModelProperty("分类的图片地址")
    private String categoryImageSrc;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}