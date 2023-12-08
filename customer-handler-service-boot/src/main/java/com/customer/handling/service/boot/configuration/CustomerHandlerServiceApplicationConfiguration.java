package com.customer.handling.service.boot.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.customer.handling")
@EnableJpaRepositories(
        basePackages = "com.customer.handling.service.database.repository"
)
@EntityScan(
        basePackages = "com.customer.handling.service.database"
)
@EnableAutoConfiguration
public class CustomerHandlerServiceApplicationConfiguration {
}
