package org.macross.shopping_mall.config;

import org.macross.shopping_mall.interceptor.CorsInterceptor;
import org.macross.shopping_mall.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Bean
    public CorsInterceptor corsInterceptor(){return new CorsInterceptor();}

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(corsInterceptor()).addPathPatterns("/**");

        registry.addInterceptor(loginInterceptor())
                    .addPathPatterns("/api/v1/pri/**")
                    .excludePathPatterns("/api/v1/pri/user/login","/api/v1/pri/user/register");

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
