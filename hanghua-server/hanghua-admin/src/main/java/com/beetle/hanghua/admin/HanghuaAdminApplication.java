package com.beetle.hanghua.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @auther zhaojie
 * @date 2022/06/21 14:59
 **/
@EnableOpenApi
@RefreshScope
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class HanghuaAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(HanghuaAdminApplication.class, args);
    }
}
