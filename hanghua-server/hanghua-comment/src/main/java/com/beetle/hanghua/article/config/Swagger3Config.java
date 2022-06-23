package com.beetle.hanghua.article.config;


import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;


/**
 * swagger只要求在开发环境下才会开启
 * @auther zhaojie
 * @date 2022/06/14 15:32
 **/
@Configuration
public class Swagger3Config implements EnvironmentAware {


    private String applicationName;

    private String applicationDescription;


    /**
     * 返回文档摘要信息
     * @return
     */
    @Bean
    public Docket creatRestApi() {
        // OAS_30指定版本
        return new Docket(DocumentationType.OAS_30)
                // 开关
                .enable(true)
                // 生成页面信息
                .apiInfo(createApiInfo())
                // 过滤规则：必须借助select()和apis()
                .select()
                //为当前包下controller生成API文档
                .apis(RequestHandlerSelectors.basePackage("com.beetle.hanghua.comment.controller"))
                //为有@Api注解的Controller生成API文档
//                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                //为有@ApiOperation注解的方法生成API文档
//                .apis(RequestHandlerSelectors.withMethodAnnotation(Operation.class))
                // 为所有Api注解生成文档,any()是全部生效，none()是全部无效
                .paths(PathSelectors.any())
                .build();
    }


    /**
     *  生成接口信息，包括标题、联系人等
     * @return
     */
    private ApiInfo createApiInfo() {
        return new ApiInfoBuilder()
                .title(applicationName + "接口文档")
                .description(applicationDescription)
                .termsOfServiceUrl("")
                .contact(new Contact("admin","http://www.zjmaster.top","zhaojie777j@gmail.com"))
                .version("1.0")
                .build();
    }


    /**
     * 获取配置文件内数据
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        this.applicationName = environment.getProperty("swagger.name");
        this.applicationDescription = environment.getProperty("swagger.description");

    }


    /**
     *  封装通用信息
     * @return
     */
    private List<Response> getGlobalResponseMessages() {
        List<Response> responseList = new ArrayList<>();
        responseList.add(new ResponseBuilder().code("404").description("未找到资源").build());
        return responseList;
    }


}
