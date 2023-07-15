package com.lt;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/15 16:17
 */
public class DateTest {
    public static void main(String[] args) {
        DateTime dateTime = DateUtil.offsetHour(new Date(), 1);
        System.out.println(dateTime);
    }
}
