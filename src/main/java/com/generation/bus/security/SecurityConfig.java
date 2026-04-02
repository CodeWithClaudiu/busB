package com.generation.bus.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
     private final CorsConfigurationSource corsConfigurationSource;

    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;

    public SecurityConfig(CorsConfigurationSource corsConfigurationSource) {
        this.corsConfigurationSource = corsConfigurationSource;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource))
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                .requestMatchers("/api/users/login").permitAll()

                .requestMatchers(HttpMethod.GET, "/api/lines/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/trips/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/stops/**").permitAll()

                .requestMatchers("/api/users/**").hasAuthority("ADMIN")

                .requestMatchers(HttpMethod.POST, "/api/lines/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/lines/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/lines/**").hasAuthority("ADMIN")

                .requestMatchers(HttpMethod.POST, "/api/trips/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/trips/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/trips/**").hasAuthority("ADMIN")

                .requestMatchers(HttpMethod.POST, "/api/stops/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/stops/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/stops/**").hasAuthority("ADMIN")

                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

