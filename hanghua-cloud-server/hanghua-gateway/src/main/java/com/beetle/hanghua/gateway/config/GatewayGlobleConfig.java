package com.beetle.hanghua.gateway.config;

import com.beetle.hanghua.common.jwt.JwtTokenUtil;
import com.beetle.hanghua.gateway.authentication.JwtAuthenticationTokenFilter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * 统一的接口请求拦截
 * @auther zhaojie
 * @date 2022/06/22 12:52
 **/
@Configuration
public class GatewayGlobleConfig{

    @Autowired
    private JwtAuthenticationTokenFilter tokenFilter;


    @Bean
    @Order
    public RouteLocator customizedRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/java/**")
                        .filters(f -> f.stripPrefix(1)
                                .filter(tokenFilter)
                        )
                        .uri("lb://FEIGN-CLIENT")
                )
                .build();
    }


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeRequests().anyRequest().authenticated().and().build();
    }



}
