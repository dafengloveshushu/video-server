package com.jushu.video.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 大奉
 * @date 2020/1/8 11:44
 * @blame 大奉
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/","/login.html","/403.html","/404.html","/favicon.ico")
                .excludePathPatterns("/assets/**","/css/**","/images/**","/js/**")
                .excludePathPatterns("/video/gm-admin/login");
    }
}
