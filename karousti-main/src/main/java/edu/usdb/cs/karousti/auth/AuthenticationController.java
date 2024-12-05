package edu.usdb.cs.karousti.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.usdb.cs.karousti.auth.requests.AdminRegistrationRequest;
import edu.usdb.cs.karousti.auth.requests.AgentRegistrationRequest;
import edu.usdb.cs.karousti.auth.requests.AuthenticationRequest;
import edu.usdb.cs.karousti.auth.requests.CustomerRegistrationRequest;
import edu.usdb.cs.karousti.auth.services.AdminRegistrationService;
import edu.usdb.cs.karousti.auth.services.AgentRegistrationService;
import edu.usdb.cs.karousti.auth.services.AuthenticationService;
import edu.usdb.cs.karousti.auth.services.CustomerRegistrationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final CustomerRegistrationService customerRegistrationService;
    private final AdminRegistrationService adminRegistrationService;
    private final AgentRegistrationService agentRegistrationService;

    @PostMapping("/register/customer")
    public ResponseEntity<AuthenticationResponse> registerCustomer(@RequestBody CustomerRegistrationRequest request) {
        return ResponseEntity.ok(customerRegistrationService.register(request));
    }

    @PostMapping("/register/admin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(@RequestBody AdminRegistrationRequest request) {
        return ResponseEntity.ok(adminRegistrationService.register(request));
    }

    @PostMapping("/register/agent")
    public ResponseEntity<AuthenticationResponse> registerAgent(@RequestBody AgentRegistrationRequest request) {
        return ResponseEntity.ok(agentRegistrationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request,
            HttpServletResponse response) {
        return ResponseEntity.ok(authenticationService.authenticate(request, response));
    }

}
