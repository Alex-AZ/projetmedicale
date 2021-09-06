package com.m2i.demomedical.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure( AuthenticationManagerBuilder auth  ) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder( passwordEncoder() );
    }

    @Override
    protected void configure( HttpSecurity http  ) throws Exception {
        //formLogin = utiliser un formulaire d'authetification - loginPage : chemin de l'authentification
        http.formLogin().loginPage("/login").defaultSuccessUrl("/dashboard");
        // Autoriser un accès anonyme sur les routes /login et /css/**
        http.authorizeRequests().antMatchers("/login" , "/css/**" ).permitAll();
        // Autoriser les actions post pour les admins : ROLE_ADMIN
        http.authorizeRequests().antMatchers("**/add" , "**/edit/**" , "**/delete/**").hasRole("ADMIN");

        // Tous les utilisateurs qui ne sont pas mentionnées en haut devrait s'authentifier
        http.authorizeRequests().anyRequest().authenticated();

        // désactiver la protection csrf
        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
