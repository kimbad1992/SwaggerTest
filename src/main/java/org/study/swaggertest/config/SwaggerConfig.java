package org.study.swaggertest.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        // Access Token을 위한 Security Scheme
        SecurityScheme accessTokenScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER).name("Authorization");

        // Refresh Token을 위한 Security Scheme (Authorization 필드가 겹치게 되므로 사용하지 않음)
        SecurityScheme refreshTokenScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER).name("RefreshToken");


        // SecurityScheme을 Security Requirement에 추가
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("accessTokenScheme").addList("refreshTokenScheme");

        // 각 Server 환경 정보를 추가
        Server localServer = new Server();
        localServer.setUrl("http://localhost:3000");
        localServer.setDescription("Local Server");

        Server qaServer = new Server();
        qaServer.setUrl("https://my.qa.server");
        qaServer.setDescription("QA Server");

        Server prodServer = new Server();
        prodServer.setUrl("https://my.production.server");
        prodServer.setDescription("Production Server");

        return new OpenAPI()
                .info(new Info()
                        .title("Swagger 설명용 REST API Server")
                        .version("1.0")
                        .description("Swagger 설명용/간단한 CRUD를 위한 REST API 서버입니다.")
//                        .termsOfService("http://swagger.io/terms/")
//                        .contact(new Contact()
//                                .name("API Support")
//                                .url("http://www.example.com/support")
//                                .email("support@example.com"))
//                        .license(new License()
//                                .name("Apache 2.0")
//                                .url("http://springdoc.org"))
                )
                .components(
                        new Components()
                                .addSecuritySchemes("accessTokenScheme", accessTokenScheme)
                                .addSecuritySchemes("refreshTokenScheme", refreshTokenScheme)
                )
                .security(Arrays.asList(
                        new SecurityRequirement().addList("accessTokenScheme"),
                        new SecurityRequirement().addList("refreshTokenScheme")
                ))
                .servers(List.of(localServer, qaServer, prodServer));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public GroupedOpenApi privateApi() {
        return GroupedOpenApi.builder()
                .group("private")
                .pathsToMatch("/private/**")
                .build();
    }
}
