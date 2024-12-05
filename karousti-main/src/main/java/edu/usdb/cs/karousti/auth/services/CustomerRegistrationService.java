package edu.usdb.cs.karousti.auth.services;

import edu.usdb.cs.karousti.auth.AuthenticationResponse;
import edu.usdb.cs.karousti.auth.requests.CustomerRegistrationRequest;
import edu.usdb.cs.karousti.config.JwtService;
import edu.usdb.cs.karousti.user.customer.Customer;
import edu.usdb.cs.karousti.user.customer.CustomerRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerRegistrationService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public CustomerRegistrationService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder,
            JwtService jwtService) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthenticationResponse register(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // Encode the password
                .build();

        customerRepository.save(customer);

        String jwtToken = jwtService.generateToken(customer);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
