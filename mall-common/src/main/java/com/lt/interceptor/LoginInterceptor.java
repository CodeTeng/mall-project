package com.lt.interceptor;

import com.lt.common.ErrorCode;
import com.lt.exception.BusinessException;
import com.lt.utils.JwtUtil;
import com.lt.utils.UserThreadLocalUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 登录拦截器
 * @author: ~Teng~
 * @date: 2023/7/9 18:50
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        log.info("token 获取为：{}", token);
        if (StringUtils.isBlank(token)) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR, "请先登录");
        }
        // 获取 payload 信息
        Claims claimsBody = JwtUtil.getClaimsBody(token);
        if (claimsBody == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR, "请先登录");
        }
        // 判断是否过期
        int res = JwtUtil.verifyToken(claimsBody);
        if (res > 1) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "token已过期，请重新登录");
        }
        Integer userId = (Integer) claimsBody.get("userId");
        log.info("token 解析成功，userId = {}", userId);
        UserThreadLocalUtil.setUserId(userId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocalUtil.clear();
    }
}
