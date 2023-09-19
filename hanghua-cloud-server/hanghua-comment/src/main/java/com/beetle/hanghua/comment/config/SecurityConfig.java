package com.beetle.hanghua.comment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @auther zhaojie
 * @date 2022/06/23 14:29
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 配置Web静态资源拦截规则
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui/index.html", "/doc.html", "/webjars/**", "/img.icons/**");
    }
}
