package com.example.patients_mvc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
// cette classe va etre instancier en premier qu'on spring demare
@EnableWebSecurity
// acceder a la securite web
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder=passwordEncoder();
        String encodedPassword = passwordEncoder.encode("1234");
        // Strategie utiliser pour chercher les utilisateur
        // preciser comment spring va chercher les utilisateur et les roles utilise la base de donne ou utilisateur en memoire , annuaire de l'entreprise
        // on va utiliser memory authentification
        auth.inMemoryAuthentication().withUser("user1").password(encodedPassword).roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password(encodedPassword).roles("USER","ADMIN");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // pour specifier les droit d'acces ,
       // http.formLogin().loginPage("/login");
        http.formLogin();
        //
        http.authorizeRequests().anyRequest().authenticated();

    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
