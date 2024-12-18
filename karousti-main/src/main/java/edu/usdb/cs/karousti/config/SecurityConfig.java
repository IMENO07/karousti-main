package edu.usdb.cs.karousti.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        private final JwtAuthenticationFilter jwtAuthFilter;
        private final AuthenticationProvider authenticationProvider;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
                httpSecurity
                        .csrf(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests(requests -> requests
                                .requestMatchers("/api/v1/auth/**").permitAll() // Public access for authentication
                                .requestMatchers("/api/v1/admin/**").hasRole("ADMIN") // Only accessible by ADMIN
                                .requestMatchers("/api/v1/customer/**").hasRole("CUSTOMER") // Only accessible by CUSTOMER
                                .requestMatchers("/api/v1/agent/**").hasRole("AGENT") // Only accessible by AGENT
                                .anyRequest().authenticated())
                        .sessionManagement(session -> session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authenticationProvider(authenticationProvider)
                        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                        .exceptionHandling(exception -> exception
                                .authenticationEntryPoint(authenticationEntryPoint()));

                return httpSecurity.build();
        }

        @Bean
        public AuthenticationEntryPoint authenticationEntryPoint() {
                return (_, response, authException) -> {
                        response.setContentType("application/json");
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.getWriter().write("{\"error\": \"Unauthorized: " + authException.getMessage() + "\"}");
                };
        }
}
