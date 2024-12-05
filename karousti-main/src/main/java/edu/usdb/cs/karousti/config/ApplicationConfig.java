package edu.usdb.cs.karousti.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.usdb.cs.karousti.user.User;
import edu.usdb.cs.karousti.user.admin.AdminRepository;
import edu.usdb.cs.karousti.user.agent.AgentRepository;
import edu.usdb.cs.karousti.user.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final AgentRepository agentRepository;

    @Bean
    UserDetailsService userDetailsService() {
        return username -> {
            Optional<User> user = adminRepository.findByEmail(username)
                    .map(admin -> {
                        System.out.println("Found in AdminRepository");
                        return (User) admin;
                    });

            if (user.isEmpty()) {
                user = customerRepository.findByEmail(username)
                        .map(customer -> {
                            System.out.println("Found in CustomerRepository");
                            return (User) customer;
                        });
            }

            if (user.isEmpty()) {
                user = agentRepository.findByEmail(username)
                        .map(agent -> {
                            System.out.println("Found in AgentRepository");
                            return (User) agent;
                        });
            }

            return user.orElseThrow(() -> {
                System.out.println("User not found in any repository");
                return new UsernameNotFoundException("User not found.");
            });
        };

    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        System.out.println("Hi1");
        authProvider.setUserDetailsService(userDetailsService());
        System.out.println("Hi2");
        authProvider.setPasswordEncoder(passwordEncoder());
        System.out.println("Hi3");
        return authProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
