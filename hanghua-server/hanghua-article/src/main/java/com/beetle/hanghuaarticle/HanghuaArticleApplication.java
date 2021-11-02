package com.beetle.hanghuaarticle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;


@EnableFeignClients
@SpringBootApplication
public class HanghuaArticleApplication {

    public static void main(String[] args) {
        SpringApplication.run(HanghuaArticleApplication.class, args);
    }

}
