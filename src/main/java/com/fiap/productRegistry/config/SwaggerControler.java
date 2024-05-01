package com.fiap.productRegistry.config;

import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerControler {

    @Autowired
    public SwaggerControler(SwaggerUiConfigParameters swaggerUiConfigParameters) {
        swaggerUiConfigParameters.setDefaultModelsExpandDepth(-1);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Product API")
                        .description("Microserviço para gerenciamento de produtos")
                        .version("1.0.0")
                );
    }
}