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
    @ApiModelProperty("当前页号")
    private long current = 1;
    @ApiModelProperty("页面大小")
    private long pageSize = 10;
    @ApiModelProperty("排序字段")
    private String sortField;
    @ApiModelProperty("排序规则 默认升序")
    private String sortOrder = CommonConstant.SORT_ORDER_ASC;
}
