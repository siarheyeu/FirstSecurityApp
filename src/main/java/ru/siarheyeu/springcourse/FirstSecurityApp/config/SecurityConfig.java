package ru.siarheyeu.springcourse.FirstSecurityApp.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.siarheyeu.springcourse.FirstSecurityApp.dto.JWTFilter;
import ru.siarheyeu.springcourse.FirstSecurityApp.services.PersonDetailsService;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final PersonDetailsService personDetailsService;
    private final JWTFilter jwtfilter;



    private final PersonDetailsService personDetailsService;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService){
        this.personDetailsService = personDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //конфигурируем сам Spring Security
        //конфигурируем авторизацию
                http.csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/auth/login", "/auth/registration", "/error").permitAll()
                .anyRequest().hasAnyRole("USER", "ADMIN")
                .and()
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("process_login")
                .defaultSuccessUrl("/hello", true)
                .failureUrl("/auth/login?error")
                .and()
                .logout().
                logoutUrl("/logout").
                logoutSuccessUrl("/auth/login")
                        .and()
                        .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);;\

        http.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
    }

    //Настраивает аутентификацию
    @Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception{
    auth.userDetailsService(personDetailsService)
            .passwordEncoder(getPasswordEncoder());

}

@Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
}

@Bean
@Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
