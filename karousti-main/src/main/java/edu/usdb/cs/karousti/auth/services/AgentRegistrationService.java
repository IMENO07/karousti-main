package edu.usdb.cs.karousti.auth.services;

import edu.usdb.cs.karousti.auth.AuthenticationResponse;
import edu.usdb.cs.karousti.auth.requests.AgentRegistrationRequest;
import edu.usdb.cs.karousti.config.JwtService;
import edu.usdb.cs.karousti.user.agent.Agent;
import edu.usdb.cs.karousti.user.agent.AgentRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AgentRegistrationService {

    private final AgentRepository agentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AgentRegistrationService(AgentRepository agentRepository, PasswordEncoder passwordEncoder,
            JwtService jwtService) {
        this.agentRepository = agentRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthenticationResponse register(AgentRegistrationRequest request) {
        Agent agent = Agent.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // Encode the password
                .build();

        agentRepository.save(agent);

        String jwtToken = jwtService.generateToken(agent);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
