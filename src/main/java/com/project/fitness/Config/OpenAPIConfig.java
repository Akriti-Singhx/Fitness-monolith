package com.project.fitness.Config;


import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.lang.Runtime.version;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("fitness tracking api")
                        .version("1.0")
                        .description("production grade APIs ")
                        .contact(new Contact()
                                .name("embark")
                                .url("https://www.embark.com")
                                .email("abc@gmail.com"))
                        .license(new License()
                                .name("apache 2.0")
                                .url("https://google.com")));
    }
}
