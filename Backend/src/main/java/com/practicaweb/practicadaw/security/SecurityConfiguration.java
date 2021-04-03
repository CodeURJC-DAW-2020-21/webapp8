package com.practicaweb.practicadaw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    RepositoryUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10, new SecureRandom());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/create_user").permitAll();
        http.authorizeRequests().antMatchers("/criptomonedas").permitAll();

        // Error pages
        http.authorizeRequests().antMatchers("/404").permitAll();
        http.authorizeRequests().antMatchers("/error").permitAll();

        // Private pages (all other pages)
        http.authorizeRequests().antMatchers("/admin").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/settings").hasAnyRole("USER", "ADMIN");
        http.authorizeRequests().antMatchers("/newEntry").hasAnyRole("USER", "ADMIN");
        http.authorizeRequests().antMatchers("/newComment").hasAnyRole("USER", "ADMIN");
        http.authorizeRequests().antMatchers("/update_user").hasAnyRole("USER", "ADMIN");
        http.authorizeRequests().antMatchers("/favorite_cryptocurrencies").hasAnyRole("USER", "ADMIN");
        http.authorizeRequests().antMatchers("/users").hasAnyRole("ADMIN");

        // Login
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/");
        http.formLogin().failureUrl("/loginError");

        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/login");

//        http.csrf().disable();
    }
}
