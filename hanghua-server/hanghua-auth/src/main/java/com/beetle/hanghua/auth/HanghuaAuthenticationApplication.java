package com.beetle.hanghua.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;


@RefreshScope
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class HanghuaAuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(HanghuaAuthenticationApplication.class, args);
    }

}
