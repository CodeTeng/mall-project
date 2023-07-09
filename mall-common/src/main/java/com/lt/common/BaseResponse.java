package com.lt.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回类
 *
 * @param <T>
 * @author teng
 */
@Data
@ApiModel("通用返回类")
public class BaseResponse<T> implements Serializable {
    @ApiModelProperty("状态码")
    private int code;
    @ApiModelProperty("数据")
    private T data;
    @ApiModelProperty("信息")
    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }
}
