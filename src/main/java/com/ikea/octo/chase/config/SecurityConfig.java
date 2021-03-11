package com.ikea.octo.chase.config;

import com.ikea.octo.chase.entity.Roles;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled=true, securedEnabled=true, prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${app.admin.name}")
    private String adminUserName;

    @Value("${app.admin.password}")
    private String adminUserPassword;

    @Value("${app.user.name}")
    private String userName;

    @Value("${app.user.password}")
    private String userPassword;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/static/**").permitAll()
                //.antMatchers("/elements").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/index")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                    .withUser(userName)
                    .password(passwordEncoder().encode(userPassword))
                    .authorities(Roles.USER.name())
                    .roles(Roles.USER.name())
                .and()
                    .withUser(adminUserName)
                    .password(passwordEncoder().encode(adminUserPassword))
                    .authorities(Roles.ADMIN.name())
                    .roles(Roles.ADMIN.name());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
