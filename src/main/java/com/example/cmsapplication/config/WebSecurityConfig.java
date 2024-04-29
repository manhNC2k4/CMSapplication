package com.example.cmsapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests( (request) -> request
                        .requestMatchers(HttpMethod.GET, "/comments/list", "/comments/{id}", "/posts/list", "/posts/{id}", "/posts/title/{title}", "/likes/post/{id}","/home/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/comments/create", "/likes/create", "/posts/create").hasAuthority("ROLE_USER")
                        .requestMatchers(HttpMethod.PUT, "/posts/update").hasAuthority("ROLE_USER")
                        .requestMatchers(HttpMethod.DELETE, "/comments/delete/{id}", "/posts/delete/{id}","/api/likes/delete/{id}").hasAuthority("ROLE_USER")
                        .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
        )
                .formLogin((form) -> form.loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .failureForwardUrl("/login?error=true")
                        .permitAll())
                .logout((logout) -> logout.logoutUrl("/logout").logoutSuccessUrl("/logout?logout=true").permitAll());
                return http.build();
    }
}
