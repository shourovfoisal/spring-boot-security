package com.shourov.springbootsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // to enable post methods through csrf security
                // first send a get request and collect the csrf token (XSRF-TOKEN) from the received cookie
                // then use that token inside the header of the post request
                // with the header name "X-XSRF-TOKEN", found in the CookieCsrfTokenRepository
                // csrf maybe disabled with .csrf.disable() when developing an app, and then re-enabled in production
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .authorizeRequests()
                .antMatchers("/signin").permitAll()
//                .antMatchers("/home", "/login", "/register").permitAll()
//                .antMatchers("/public/**").permitAll()
                .antMatchers("/public/**").hasAnyRole("NORMAL", "ADMIN")
//                .antMatchers("/users/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
//                .httpBasic();
                .formLogin()
                .loginPage("/signin")
                .loginProcessingUrl("/dologin")
                .defaultSuccessUrl("/users");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("shourov")
                .password(passwordEncoder().encode("123"))
                .roles("NORMAL");

        auth
                .inMemoryAuthentication()
                .withUser("foisal")
                .password(passwordEncoder().encode("456"))
                .roles("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}
