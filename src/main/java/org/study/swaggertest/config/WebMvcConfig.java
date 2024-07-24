package org.study.swaggertest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 모든 경로에 대해
                        // TODO : 모든 Origin에 대해 허용(테스트)
                        .allowedOrigins("*") // 허용할 출처
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드
                        .allowedHeaders("*") // 허용할 헤더
                        .allowCredentials(false); // 자격 증명(쿠키, 인증 정보 등) 허용 여부
            }
        };
    }
}
