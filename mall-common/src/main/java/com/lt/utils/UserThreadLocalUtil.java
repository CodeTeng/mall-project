package com.lt.utils;


/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/9 19:43
 */
public class UserThreadLocalUtil {
    private final static ThreadLocal<Integer> userThreadLocal = new ThreadLocal<>();

    /**
     * 设置当前线程中的用户
     */
    public static void setUserId(Integer userId) {
        userThreadLocal.set(userId);
    }

    /**
     * 获取线程中的用户
     */
    public static Integer getUserId() {
        return userThreadLocal.get();
    }

    /**
     * 清空线程中的用户信息
     */
    public static void clear() {
        userThreadLocal.remove();
    }
}
