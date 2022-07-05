package com.beetle.hanghua.gateway.authentication;

import cn.hutool.core.util.StrUtil;
import com.beetle.hanghua.common.jwt.JwtTokenUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 自定义JWT认证过滤器
 * @auther zhaojie
 * @date 2022/06/29 14:42
 **/
@Component
public class JwtAuthenticationTokenFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {


        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        // 判断token格式是否符合JWT标准或是否携带token
        if (StrUtil.isBlank(token) || !StrUtil.startWithIgnoreCase(token, "Bearer")) {
            return chain.filter(exchange);
        }


        try {
            //从token中解析用户信息并设置到Header中去
            String realToken = StrUtil.replaceIgnoreCase(token, "Bearer", Strings.EMPTY);
            Map<String, Object> userInfo = JwtTokenUtil.getUserInfo(token);

            ServerHttpRequest request = exchange.getRequest().mutate().header("user", userInfo.get("name").toString()).build();
            exchange = exchange.mutate().request(request).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chain.filter(exchange);
    }




    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 1、从请求头中获取 token
     * 2、对 token 进行解析、验签、校验过期时间
     * 3、校验成功，将验证结果放到 ThreadLocal 中，供下次请求使用
     */



}
