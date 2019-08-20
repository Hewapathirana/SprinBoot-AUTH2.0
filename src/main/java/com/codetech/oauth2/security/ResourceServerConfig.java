package com.codetech.oauth2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Created by A Majutharan.
 * Date: 6/16/2019
 * Time: 12:07 PM
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    //aded by dasitha on 18/8/2109
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http    //.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .authorizeRequests()
                .antMatchers("/user-service/register").permitAll()
              //  .antMatchers("/test/test/welcome").permitAll()
                .antMatchers("/**").authenticated().and().logout().logoutUrl("/oauth/logout").invalidateHttpSession(true)
                .clearAuthentication(true).logoutSuccessUrl("/").and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
              //  .headers().frameOptions().sameOrigin().and()
                .csrf().disable(); //.and().cors()
    }
}
