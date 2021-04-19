package com.practicaweb.practicadaw.security;

import com.practicaweb.practicadaw.security.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Order(1)
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.antMatcher("/api/**");

        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/users/friends/**").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PATCH, "/api/users/**").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/entries/**").hasAnyRole("USER, ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/comments/**").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/cryptocurrencies/**").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/auth/logout").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/users/**").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/users/friends/**").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/cryptocurrencies/**").hasAnyRole("USER");


        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/users/").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/entries/**").permitAll();
        http.authorizeRequests().anyRequest().permitAll();

        http.csrf().disable();
        http.httpBasic().disable();
        http.formLogin().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
