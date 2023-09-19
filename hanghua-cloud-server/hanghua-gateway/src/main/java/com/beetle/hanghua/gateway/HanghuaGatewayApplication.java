package com.beetle.hanghua.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @auther zhaojie
 * @date 2022/06/08 17:09
 **/
@EnableOpenApi
@RefreshScope
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class HanghuaGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(HanghuaGatewayApplication.class, args);
    }

}
