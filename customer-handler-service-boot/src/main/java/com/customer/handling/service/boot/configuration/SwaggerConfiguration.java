package com.customer.handling.service.boot.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info =
        @Info(description = "A sample API to illustrate OpenAPI concepts",
                title = "This is SPARTA!!!!",
                version = "1.0.0"))
public class SwaggerConfiguration {
}