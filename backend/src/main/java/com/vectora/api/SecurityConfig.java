package com.vectora.api;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {
  @Bean
  InMemoryUserDetailsManager users() {
    return new InMemoryUserDetailsManager(
        User.withUsername("commander").password("{noop}commander123").roles("COMMANDER").build(),
        User.withUsername("operator").password("{noop}operator123").roles("OPERATOR").build(),
        User.withUsername("agri_manager").password("{noop}agri123").roles("AGRI_MANAGER").build(),
        User.withUsername("technician").password("{noop}tech123").roles("TECHNICIAN").build(),
        User.withUsername("auditor").password("{noop}audit123").roles("AUDITOR").build(),
        User.withUsername("client_viewer").password("{noop}client123").roles("CLIENT_VIEWER").build());
  }

  @Bean
  SecurityFilterChain chain(HttpSecurity http) throws Exception {
    http.csrf(c -> c.disable())
        .cors(Customizer.withDefaults())
        .httpBasic(Customizer.withDefaults())
        .authorizeHttpRequests(a -> a
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .requestMatchers(
                "/", "/api", "/error", "/favicon.ico",
                "/swagger-ui.html", "/swagger-ui/**", "/webjars/**",
                "/v3/api-docs", "/v3/api-docs/**").permitAll()
            .anyRequest().authenticated());
    return http.build();
  }

  @Bean
  org.springframework.web.cors.CorsConfigurationSource cors() {
    CorsConfiguration c = new CorsConfiguration();
    c.setAllowedOriginPatterns(List.of("*"));
    c.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    c.setAllowedHeaders(List.of("*"));
    c.setAllowCredentials(true);
    UrlBasedCorsConfigurationSource s = new UrlBasedCorsConfigurationSource();
    s.registerCorsConfiguration("/**", c);
    return s;
  }
}
