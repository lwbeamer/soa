package org.itmo.spacemarine.config;

import lombok.AllArgsConstructor;
import org.itmo.spacemarine.security.filter.JwtAuthenticationFilterBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@AllArgsConstructor
@Configuration
public class SecurityConfig {
    private final JwtAuthenticationFilterBefore jwtAuthenticationFilterBefore;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .disable()
                .csrf().disable()
                .cors().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests((auth) -> auth
                        .anyRequest().permitAll()
                )
                .addFilterBefore(jwtAuthenticationFilterBefore, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
