package com.beetle.hanghuaeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class HanghuaEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HanghuaEurekaServerApplication.class, args);
    }

}
