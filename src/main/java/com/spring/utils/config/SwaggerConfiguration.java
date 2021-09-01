package com.spring.utils.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

    @Value("${release.version}")
    private String releaseVersion;

    @Value("${api.version}")
    private String apiVersion;

    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.spring.controller.v1"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    public ApiInfo apiInfo(){
            return new ApiInfoBuilder().title("Java Api")
                    .description("Java API - Endpoint's documentation")
                    .version(releaseVersion.concat("_").concat(apiVersion))
                    .build();
    }
}
