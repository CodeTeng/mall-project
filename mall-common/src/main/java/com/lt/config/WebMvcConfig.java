package com.lt.config;

import com.lt.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * @description: MVC 配置
 * @author: ~Teng~
 * @date: 2023/7/9 15:32
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private List<String> urls = Arrays.asList(
            "/**/login", "/**/register", "/**/findPassword", "/druid/index.html",
            "/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/doc.html/**",
            "/v2/**", "/product/**", "/address/**", "/category/**"
    );

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 覆盖所有请求
        registry.addMapping("/**")
                // 是否发送Cookie
                .allowCredentials(true)
                // 放行哪些域名
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("*");
    }

    /**
     * 登录拦截器
     */
    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor())//添加拦截器
                .excludePathPatterns(urls)//对应的不拦截的请求
                .addPathPatterns("/**"); //拦截所有请求
    }
}
