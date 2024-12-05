package edu.usdb.cs.karousti.auth.services;

import edu.usdb.cs.karousti.auth.AuthenticationResponse;
import edu.usdb.cs.karousti.auth.requests.AdminRegistrationRequest;
import edu.usdb.cs.karousti.config.JwtService;
import edu.usdb.cs.karousti.user.admin.Admin;
import edu.usdb.cs.karousti.user.admin.AdminRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminRegistrationService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AdminRegistrationService(AdminRepository adminRepository, PasswordEncoder passwordEncoder,
            JwtService jwtService) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthenticationResponse register(AdminRegistrationRequest request) {
        Admin admin = Admin.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // Encode the password
                .adminLevel(request.getAdminLevel())
                .build();

        adminRepository.save(admin);

        String jwtToken = jwtService.generateToken(admin);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
