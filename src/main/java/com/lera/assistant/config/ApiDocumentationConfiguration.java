package com.lera.assistant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ApiDocumentationConfiguration {
    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("Personal Finance Assistant")
                .description("App that helps freelancers manage their finances")
                .version("1.0")
                .contact(new Contact("Valeria Ivashchenko", "", "ivashchenko.valeria@gmail.com"))
                .build();
    }

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/**"))
                .apis(RequestHandlerSelectors.basePackage("com.lera.assistant"))
                .build()
                .apiInfo(metadata());
    }
}
