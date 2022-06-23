package com.beetle.hanghua.accountcenter;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@RefreshScope
@EnableFeignClients
@EnableDiscoveryClient
@EnableScheduling
@SpringBootApplication
public class HanghuaAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(HanghuaAccountApplication.class, args);
    }

}
