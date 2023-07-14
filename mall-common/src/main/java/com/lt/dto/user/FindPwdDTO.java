package com.lt.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/14 15:32
 */
@Data
@ApiModel("找回密码DTO")
public class FindPwdDTO implements Serializable {
    @ApiModelProperty(value = "手机号", required = true)
    private String mobile;
    @ApiModelProperty(value = "用户名", required = true)
    private String userName;
}
