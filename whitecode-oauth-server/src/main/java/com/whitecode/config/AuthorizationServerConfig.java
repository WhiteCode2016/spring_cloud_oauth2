package com.whitecode.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * 授权服务器配置
 * Created by White on 2018/1/24.
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 配置客户端,既可以用于password认证，又可用于client认证
        // Password模式：http://localhost:8080/oauth/token?username=root&password=123&grant_type=password&scope=all&client_id=client&client_secret=123
        // Client模式：http://localhost:8080/oauth/token?grant_type=client_credentials&scope=all&client_id=client&client_secret=123
        clients.inMemory().withClient("client")
                .authorizedGrantTypes("client_credentials", "password", "refresh_token")
                .scopes("all") // 此处scopes是无用，可以任意配
                .authorities("client")
                .secret("123")
                .accessTokenValiditySeconds(12000) //2小时
                .refreshTokenValiditySeconds(6000*24*30) // 30天
                .and()
                    .withClient("android")
                    .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                    .scopes("xx") //此处的scopes是无用的，可以随意设置
                    .secret("android")
                .and()
                    .withClient("webapp")
                    .authorizedGrantTypes("implicit")
                    .scopes("xx");

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore()) // 暂时存储在内存中
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll")
                .checkTokenAccess("isAuthenticated")
                .allowFormAuthenticationForClients();  //允许表单认证
    }
}

