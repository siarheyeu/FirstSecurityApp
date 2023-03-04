package ru.siarheyeu.springcourse.FirstSecurityApp.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import ru.siarheyeu.springcourse.FirstSecurityApp.security.AuthProviderImpl;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final AuthProviderImpl authProvider;


    @Autowired
    public SecurityConfig(AuthProviderImpl authProvider){
        this.authProvider = authProvider;
    }

    //Настраивает аутентификацию
protected void configure(AuthenticationManagerBuilder auth){
    auth.authenticationProvider(authProvider);
}
}
