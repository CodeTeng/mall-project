package com.lt.service.impl;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.lt.common.ErrorCode;
import com.lt.exception.BusinessException;
import com.lt.property.SmsProperty;
import com.lt.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 短信Service
 * @author: ~Teng~
 * @date: 2023/7/14 15:20
 */
@Service
@Slf4j
public class SmsServiceImpl implements SmsService {
    @Resource
    private SmsProperty smsProperty;

    @Override
    public void sendSms(String mobile, String code) {
        // 配置阿里云
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(smsProperty.getAccessKey())
                // 您的AccessKey Secret
                .setAccessKeySecret(smsProperty.getSecret())
                .setEndpoint("dysmsapi.aliyuncs.com");
        try {
            Client client = new Client(config);
            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    .setPhoneNumbers(mobile)
                    .setSignName(smsProperty.getSignName())
                    .setTemplateCode(smsProperty.getTemplateCode())
                    .setTemplateParam("{\"code\":\"" + code + "\"}");
            SendSmsResponse response = client.sendSms(sendSmsRequest);
            log.info("响应体：{}", response.getBody().toString());
            log.info("响应内容：{}", response.getBody().message);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "发送失败");
        }
    }
}
