package com.beetle.hanghua.account.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * webMVC配置
 * @author zhaojie
 * @date 2021-05-02
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {



    /**
     * 自定义拦截器注册管理
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求
//        registry.addInterceptor().addPathPatterns("/**");
    }
}
