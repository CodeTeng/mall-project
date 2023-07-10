package com.lt.dto.product;

import com.lt.common.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/10 10:44
 */
@Data
@ApiModel("商品搜索DTO")
public class ProductSearchDTO extends PageRequest implements Serializable {
    @ApiModelProperty("商品名称")
    private String productName;
}
