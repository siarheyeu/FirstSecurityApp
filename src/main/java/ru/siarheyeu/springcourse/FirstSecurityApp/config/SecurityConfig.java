package ru.siarheyeu.springcourse.FirstSecurityApp.config;


import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //Настраивает аутентификацию
protected void configure(AuthenticationManagerBuilder auth){
    auth.authenticationProvider();
}
}
