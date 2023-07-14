package com.lt.service;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/14 15:20
 */
public interface SmsService {
    /**
     * 发送手机号验证码
     *
     * @param mobile 手机号
     * @param code   验证码
     */
    void sendSms(String mobile, String code);
}
