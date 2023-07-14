package com.lt.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/14 15:17
 */
@Data
@Component
@ConfigurationProperties(prefix = "sms")
public class SmsProperty {
    private String signName;
    private String templateCode;
    private String accessKey;
    private String secret;
}
