package com.beetle.hanghua.accountcenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swigger3.0接口文档配置
 * @auther zhaojie
 * @date 2022/04/20 16:38
 **/
@EnableOpenApi
@Configuration
public class SwiggerConfig implements WebMvcConfigurer {

    /**
     * 返回文档摘要信息
     * @return
     */
    @Bean
    public Docket creatRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                //为当前包下controller生成API文档
                .apis(RequestHandlerSelectors.basePackage("com.beetle.hanghua.accountcenter.controller"))
                //为有@Api注解的Controller生成API文档
//                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                //为有@ApiOperation注解的方法生成API文档
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 为所有Api注解生成文档
                .paths(PathSelectors.any())
                .build();
    }


    /**
     *  生成接口信息，包括标题、联系人等
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swigger3接口文档")
                .description("")
                .contact(new Contact("","",""))
                .version("1.0")
                .build();
    }


}
