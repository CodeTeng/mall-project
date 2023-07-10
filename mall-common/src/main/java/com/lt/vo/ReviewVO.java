package com.lt.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/10 20:15
 */
@Data
@ApiModel("评价数据VO")
public class ReviewVO implements Serializable {
    @ApiModelProperty("评论内容")
    private String reviewContent;
    @ApiModelProperty("评论时间")
    private Date reviewCreatedate;
    @ApiModelProperty("评论人昵称")
    private String userNickName;
}
