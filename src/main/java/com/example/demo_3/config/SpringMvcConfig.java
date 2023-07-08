package com.example.demo_3.config;


import com.example.demo_3.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Resource
    LoginInterceptor loginterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/test/**",
                        "/user/login",
                        "/category/all",
                        "/ebook/list",
                        "/doc/**",
                        "/doc/find-content/**",
                        "/ebook-snapshot/**",
                        "/ebook-snapshot/**"
                );


    }
//        "/test/**",

//        "/user/login",
//        "/category/all",
//        "/ebook/list",
//        "/doc/all/**",
//        "/doc/vote/**",
//        "/doc/find-content/**",
//        "/ebook-snapshot/**"
}
