package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.servers.Server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // Server configuration (as per preferred project)
                .servers(List.of(
                        new Server().url("https://9004.pro604cr.amypo.ai/")
                ))

                // API info (optional but good)
                .info(new Info()
                        .title("E-Commerce Bundle & Save API")
                        .version("1.0")
                        .description("REST API for E-Commerce Bundle Discount Management System"))

                // Security requirement (IMPORTANT for testcases)
                .addSecurityItem(
                        new SecurityRequirement().addList("Bearer Authentication")
                )

                // Security scheme definition
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Enter JWT token")));
    }
}
