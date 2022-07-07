package com.beetle.hanghua.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;




/**
 * @auther zhaojie
 * @date 2022/06/27 09:43
 **/
@EnableWebSecurity
public class SecurityConfig{

    /**
     * 自定义过滤规则
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                        authorizeRequests.anyRequest().authenticated())
                .formLogin();
        return http.build();
    }



    /**
     * 密码加密模式
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 用户资源
     * @return
     */
    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("zhangsan")
                .password(passwordEncoder().encode("zhangsan"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }


}