package com.lt.property;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/14 9:28
 */
@Data
@Component
@ConfigurationProperties(prefix = "alipay")
public class PayProperty {
    @ApiModelProperty("APPID")
    private String appId;
    @ApiModelProperty("应用私钥 ")
    private String appPrivateKey;
    @ApiModelProperty("支付宝公钥")
    private String alipayPublicKey;
    @ApiModelProperty("支付宝异步通知路径")
    private String notifyUrl;
    @ApiModelProperty("支付宝同步通知路径")
    private String returnUrl;
    @ApiModelProperty("网关地址")
    private String gatewayUrl;
    @ApiModelProperty("格式")
    private String format;
    @ApiModelProperty("编码格式")
    private String encoding;
    @ApiModelProperty("签名方式")
    private String signType;
}
