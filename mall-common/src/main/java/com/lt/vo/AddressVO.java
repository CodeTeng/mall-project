package com.lt.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/9 18:12
 */
@Data
@ApiModel("地址返回VO")
public class AddressVO implements Serializable {
    @ApiModelProperty("地址编号")
    private String addressAreaId;
    @ApiModelProperty("地址名称")
    private String addressName;
}
