package com.beetle.hanghua.gateway.config;

import com.beetle.hanghua.common.jwt.JwtTokenUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * 统一的接口请求拦截
 * @auther zhaojie
 * @date 2022/06/22 12:52
 **/
@Configuration
public class GatewayGlobleConfig implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
       return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
