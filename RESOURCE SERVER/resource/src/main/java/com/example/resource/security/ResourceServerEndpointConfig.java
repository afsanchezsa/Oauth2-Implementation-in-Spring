package com.example.resource.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
@EnableResourceServer
@Configuration
public class ResourceServerEndpointConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws  Exception{
    http.authorizeRequests().antMatchers("/message").hasAuthority("ROLE_ADMIN").anyRequest().permitAll();
        //http.authorizeRequests().anyRequest().permitAll();

    }

    /*@Bean
    public ResourceServerTokenServices tokenServices(){
        RemoteTokenServices tokenServices=new RemoteTokenServices();
        tokenServices.setClientId("cliente");
        tokenServices.setClientSecret("password");
        tokenServices.setCheckTokenEndpointUrl("http://localhost:8101/oauth/check_token");
        return tokenServices;
    }*/
    @Primary
    @Bean
    public RemoteTokenServices tokenService() {
        RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl("http://localhost:8101/oauth/check_token");
        tokenService.setClientId("cliente");
        tokenService.setClientSecret("password");
        return tokenService;
    }
}
