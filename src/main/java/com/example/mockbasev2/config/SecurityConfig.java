package com.example.mockbasev2.config;

import com.example.mockbasev2.security.CustomAuthenticationEntryPoint;
import com.example.mockbasev2.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private  CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private  UserDetailsService userDetailsService;
    private  JwtAuthenticationFilter authenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter authenticationFilter, UserDetailsService userDetailsService, CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.authenticationFilter = authenticationFilter;
        this.userDetailsService = userDetailsService;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        //permitAll()
        final String[] generalEndpoints = {
                "/api/auth/**"
        };
        final String[] adminEndpoints = {
                "/api/posts/**"
        };
        final String[] userEndpoints = {
                "/api/user/**"
        };
        http.csrf().disable() .authorizeHttpRequests((authorize) ->
                authorize
                        .antMatchers(generalEndpoints).permitAll()
                        .anyRequest().authenticated()

        ).exceptionHandling( exception -> exception
                .authenticationEntryPoint(customAuthenticationEntryPoint)
        ).sessionManagement( session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
