package com.beetle.hanghua.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @auther zhaojie
 * @date 2022/06/21 15:02
 **/
@RefreshScope
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class HanghuaCommentApplication {

    public static void main(String[] args) {
        SpringApplication.run(HanghuaCommentApplication.class, args);
    }

}
