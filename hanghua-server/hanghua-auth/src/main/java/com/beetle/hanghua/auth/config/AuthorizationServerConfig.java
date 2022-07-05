package com.beetle.hanghua.auth.config;


import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.util.UUID;




/**
 * 授权服务器
 * @auther zhaojie
 * @date 2022/06/27 09:42
 **/
@Configuration(proxyBeanMethods = false)
public class  AuthorizationServerConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;



    /**
     * Spring Security核心过滤器链,spring security 5.4.x版本及以上写法
     * @return
     * @throws Exception
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        // 授权服务器配置
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        // 未通过身份验证时重定向到登录页面授权端点
        http.exceptionHandling(exceptions ->
                exceptions.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")));
        return http.build();
    }




    /**
     * 客户端注册资源，注意这里采用的是内存模式写死数据，后续可以配合后台管理页面改成jdbc模式。
     * RegisteredClientRepository用于管理客户端的实例。
     * @return
     */
    @Bean
    public RegisteredClientRepository registeredClientRepository(JdbcTemplate template) {
        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                // 客户端唯一ID
                .clientId("test-client")
                // 客户端密码，不可泄漏给客户端，必须保存在服务器端
                .clientSecret(passwordEncoder.encode("test123"))
                // 可以基于 basic 的方式和授权服务器进行认证
                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                // 授权码模式
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                // 刷新token模式
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                // 客户端模式
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                // 重定向地址
//                .redirectUri("http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc")
//                .redirectUri("http://127.0.0.1:8080/authorized")
                .redirectUri("https://www.zjmaster.top/")
                // 客户端申请的作用域，即客户端申请访问用户的哪些信息，比如：获取用户信息，获取用户照片等
                .scope(OidcScopes.OPENID)
                .scope("message.read")
                .scope("message.write")
                // 是否需要用户确认一下客户端需要获取用户的哪些权限
                // 比如：客户端需要获取用户的 用户信息、用户照片 但是此处用户可以控制只给客户端授权获取 用户信息。
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();

        // Save registered client in db as if in-memory
        JdbcRegisteredClientRepository registeredClientRepository = new JdbcRegisteredClientRepository(template);
        registeredClientRepository.save(registeredClient);

        return registeredClientRepository;
    }


    /**
     * 保存授权信息，授权服务器给我们颁发来token，那我们肯定需要保存吧，由这个服务来保存
     */
    @Bean
    public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
//        return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
        JdbcOAuth2AuthorizationService authorizationService = new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);

        class CustomOAuth2AuthorizationRowMapper extends JdbcOAuth2AuthorizationService.OAuth2AuthorizationRowMapper {
            public CustomOAuth2AuthorizationRowMapper(RegisteredClientRepository registeredClientRepository) {
                super(registeredClientRepository);
                getObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
                this.setLobHandler(new DefaultLobHandler());
            }
        }

        CustomOAuth2AuthorizationRowMapper oAuth2AuthorizationRowMapper =
                new CustomOAuth2AuthorizationRowMapper(registeredClientRepository);

        authorizationService.setAuthorizationRowMapper(oAuth2AuthorizationRowMapper);
        return authorizationService;
    }


    /**
     * 如果是授权码的流程，可能客户端申请了多个权限，比如：获取用户信息，修改用户信息，此Service处理的是用户给这个客户端哪些权限，比如只给获取用户信息的权限
     */
    @Bean
    public OAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
    }




    /**
     * ProviderSettings配置 Spring Authorization Server的实例
     * 模拟本地为授权服务器
     * @return
     */
    @Bean
    public ProviderSettings providerSettings() {
        return ProviderSettings.builder()
                .tokenEndpoint("/oauth2/token")
                .issuer("http://localhost:9000")
                .build();
    }


//    /**
//     * 嵌入式数据库,使用方便灵活
//     * @return
//     */
//    @Bean
//    public EmbeddedDatabase embeddedDatabase() {
//        return new EmbeddedDatabaseBuilder()
//                .generateUniqueName(true)
//                .setType(EmbeddedDatabaseType.H2)
//                .setScriptEncoding("UTF-8")
//                .addScript("org/springframework/security/oauth2/server/authorization/oauth2-authorization-schema.sql")
//                .addScript("org/springframework/security/oauth2/server/authorization/oauth2-authorization-consent-schema.sql")
//                .addScript("org/springframework/security/oauth2/server/authorization/client/oauth2-registered-client-schema.sql")
//                .build();
//    }



}
