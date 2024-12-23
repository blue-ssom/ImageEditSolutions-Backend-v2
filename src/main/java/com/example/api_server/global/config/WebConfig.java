package com.example.api_server.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**") // 모든 경로에 대해 CORS 설정
                .allowedOrigins("http://localhost:8080", "http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "PATCH","DELETE") // 허용할 HTTP 메서드
                .allowedHeaders("Content-Type", "Authorization") // 허용할 헤더
                .allowCredentials(true);
    }
}
