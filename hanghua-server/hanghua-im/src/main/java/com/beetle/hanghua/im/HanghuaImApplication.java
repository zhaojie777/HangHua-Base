package com.beetle.hanghua.im;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

@RefreshScope
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class HanghuaImApplication {

    public static void main(String[] args) {
        SpringApplication.run(HanghuaImApplication.class, args);
    }

}
