package com.nvt.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

   @Bean
    public Docket postsApi() {
       ParameterBuilder aParameterBuilder = new ParameterBuilder();
       aParameterBuilder.name("Authorization")
               .modelRef(new ModelRef("string"))
               .parameterType("header")
               .defaultValue("Example:d7c46f4ab4b54f2b08bd9fbb46df03c04")
               .required(true)
               .build();
       List<Parameter> aParameters = new ArrayList<>();
       aParameters.add(aParameterBuilder.build());

       return new Docket(SWAGGER_2)
               .groupName("public-api")
               .apiInfo(apiInfo())
               .select()
               .paths(postPaths())
               .build()
               .globalOperationParameters(aParameters);
   }

    private Predicate<String> postPaths() {
        return or(
                regex("/api/*.*")
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("IELTS API")
                .description("IELTS API reference for developers")
                .termsOfServiceUrl("http://hantsy.blogspot.com")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .version("2.0")
                .build();
    }
}
