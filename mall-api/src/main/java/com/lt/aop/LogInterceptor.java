package com.lt.aop;

import com.lt.entity.SysLog;
import com.lt.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @description:
 * @author: ~Teng~
 * @date: 2023/7/15 20:40
 */
@Component
@Slf4j
@Aspect
public class LogInterceptor {
    @Resource
    private SysLogService sysLogService;

    /**
     * 执行拦截
     */
    @Around("execution(* com.lt.controller.*.*(..))")
    public Object doInterceptor(ProceedingJoinPoint point) throws Throwable {
        // 计时
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // 获取请求路径
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
        // 生成请求唯一 id
        String requestId = UUID.randomUUID().toString();
        String url = httpServletRequest.getRequestURI();
        // 获取请求参数
        Object[] args = point.getArgs();
        String reqParam = "[" + StringUtils.join(args, ", ") + "]";
        // 输出请求日志
        String ip = httpServletRequest.getRemoteHost();
        log.info("request start，id: {}, path: {}, ip: {}, params: {}", requestId, url, ip, reqParam);
        // 执行原方法
        Object result = point.proceed();
        // 输出响应日志
        stopWatch.stop();
        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        log.info("request end, id: {}, cost: {}ms", requestId, totalTimeMillis);
        // 异步保存日志
        CompletableFuture.runAsync(() -> {
            SysLog sysLog = new SysLog();
            sysLog.setRequestUrl(url);
            sysLog.setRequestParam(reqParam);
            sysLog.setRequestIp(ip);
            sysLog.setCost(totalTimeMillis);
            sysLogService.save(sysLog);
        });
        return result;
    }
}
