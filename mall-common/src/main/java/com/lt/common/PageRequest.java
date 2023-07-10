package com.lt.common;


import com.lt.constant.CommonConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页请求
 *
 * @author teng
 */
@Data
@ApiModel("分页请求")
public class PageRequest {
    @ApiModelProperty(name = "current", value = "当前页号", required = true)
    private long current = 1;
    @ApiModelProperty(name = "pageSize", value = "页面大小 默认10页", required = true)
    private long pageSize = 10;
    @ApiModelProperty(name = "sortField", value = "排序字段")
    private String sortField;
    @ApiModelProperty(name = "sortOrder", value = "排序规则 默认降序")
    private String sortOrder = CommonConstant.SORT_ORDER_DESC;
}
