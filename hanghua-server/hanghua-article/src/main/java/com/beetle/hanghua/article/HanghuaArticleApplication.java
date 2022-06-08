package com.beetle.hanghua.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class HanghuaArticleApplication {

    public static void main(String[] args) {
        SpringApplication.run(HanghuaArticleApplication.class, args);
    }

}
