package com.mmmgdzl.test.springcloudsecurityauth.config;

import com.mmmgdzl.test.springcloudsecurityauth.extra.RedisTokenStore;
import com.mmmgdzl.test.springcloudsecurityauth.service.ClientDetailServiceImpl;
import com.mmmgdzl.test.springcloudsecurityauth.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private ClientDetailServiceImpl clientDetailService;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                //允许表单提交获取令牌
                .allowFormAuthenticationForClients()
                //以下两项默认值为"denyAll()", 推测配置意义为允许所有地址获取令牌
                .tokenKeyAccess("permitAll()")
                //推测意义为已授权用户才可检查令牌
                .checkTokenAccess("isAuthenticated()");
    }



    /**
     * 注入authenticationManager
     * 来支持 password grant type
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        //设置将TOKEN缓存至REDIS
        endpoints.tokenStore(tokenStore())
                //配置用户详情bean
                .userDetailsService(userDetailService)
                .authenticationManager(authenticationManager);
        endpoints.tokenServices(defaultTokenServices());
        //认证异常翻译
        // endpoints.exceptionTranslator(webResponseExceptionTranslator());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("mmmgdzl")
//                .secret(passwordEncoder.encode("987987"))
//                .authorizedGrantTypes("client_credentials", "password", "refresh_token")
//                .scopes("all")
//                .resourceIds("oauth2-resource")
//                .accessTokenValiditySeconds(1200)
//                .refreshTokenValiditySeconds(50000);
        clients.withClientDetails(clientDetailService);
    }

    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * <p>注意，自定义TokenServices的时候，需要设置@Primary，否则报错，</p>
     * 自定义的token
     * 认证的token是存到redis里的
     * @return
     */
    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        //设置将TOKEN缓存至REDIS
        tokenServices.setTokenStore(tokenStore());
        //设置支持TOKEN刷新
        tokenServices.setSupportRefreshToken(true);
        //tokenServices.setClientDetailsService(clientDetails());
        // token有效期自定义设置，默认12小时
        tokenServices.setAccessTokenValiditySeconds(60*60*12);
        // refresh_token默认30天
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);
        return tokenServices;
    }


}