package com.lt.constant;

/**
 * 用户常量
 *
 * @author teng
 */
public interface UserConstant {
    /**
     * 用户登录态键
     */
    String USER_LOGIN_STATE = "userLoginState";

    /**
     * 盐值，混淆密码
     */
    String SALT = "muziteng";

    /**
     * 系统用户 id（虚拟用户）
     */
    long SYSTEM_USER_ID = 0;

    /**
     * 默认权限
     */
    String DEFAULT_ROLE = "user";

    /**
     * 管理员权限
     */
    String ADMIN_ROLE = "admin";

    /**
     * 被封号
     */
    String BAN_ROLE = "ban";
}
