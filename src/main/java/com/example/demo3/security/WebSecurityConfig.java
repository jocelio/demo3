package com.example.demo3.security;

import com.example.demo3.security.jwt.JwtSecurityConfigurer;
import com.example.demo3.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .httpBasic().disable()
        .csrf().disable()
//        .cors().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .authorizeRequests()
            .antMatchers("/auth/signin").permitAll()
            .antMatchers("/me").permitAll()
            .antMatchers("/api/v1/users/**").hasRole("ADMIN")
            .antMatchers("/api/v1/groups/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        .and()
        .apply(new JwtSecurityConfigurer(jwtTokenProvider));

    }
}