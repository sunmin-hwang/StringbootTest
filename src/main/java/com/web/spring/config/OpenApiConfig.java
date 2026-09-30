package com.web.spring.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Board API",
        description = "Spring Boot 스터디용 게시판 REST API",
        version = "v1"
    )
)
public class OpenApiConfig {
}
