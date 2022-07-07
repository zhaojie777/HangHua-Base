package com.beetle.hanghua.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import springfox.documentation.oas.annotations.EnableOpenApi;

//@EnableOpenApi
@RefreshScope
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class HanghuaAuthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(HanghuaAuthorizationApplication.class, args);
    }

}
