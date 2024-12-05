package edu.usdb.cs.karousti.auth.services;

import edu.usdb.cs.karousti.auth.AuthenticationResponse;
import edu.usdb.cs.karousti.auth.requests.AuthenticationRequest;
import edu.usdb.cs.karousti.config.JwtService;
import edu.usdb.cs.karousti.user.admin.Admin;
import edu.usdb.cs.karousti.user.admin.AdminRepository;
import edu.usdb.cs.karousti.user.agent.Agent;
import edu.usdb.cs.karousti.user.agent.AgentRepository;
import edu.usdb.cs.karousti.user.customer.Customer;
import edu.usdb.cs.karousti.user.customer.CustomerRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

	private final AdminRepository adminRepository;
	private final CustomerRepository customerRepository;
	private final AgentRepository agentRepository;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public AuthenticationService(AdminRepository adminRepository, CustomerRepository customerRepository,
			AgentRepository agentRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
		this.adminRepository = adminRepository;
		this.customerRepository = customerRepository;
		this.agentRepository = agentRepository;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request, HttpServletResponse response) {
		UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(request.getEmail(),
				request.getPassword());

		try {
			authenticationManager.authenticate(upat);
		} catch (AuthenticationException ex) {
			throw new RuntimeException("Authentication failed: " + ex.getMessage());
		}

		Admin admin = adminRepository.findByEmail(request.getEmail()).orElse(null);
		Customer customer = customerRepository.findByEmail(request.getEmail()).orElse(null);
		Agent agent = agentRepository.findByEmail(request.getEmail()).orElse(null);

		if (admin == null && customer == null && agent == null) {
			throw new RuntimeException("User not found");
		}

		var user = (admin != null) ? admin : (customer != null) ? customer : agent;
		var jwtToken = jwtService.generateToken(user);

		// Create the cookie with JWT token
		Cookie jwtCookie = new Cookie("jwt", jwtToken);
		jwtCookie.setHttpOnly(true); // Prevent JavaScript access to the cookie
		jwtCookie.setSecure(true); // Ensure the cookie is sent only over HTTPS
		jwtCookie.setPath("/"); // Make the cookie available to the entire domain
		jwtCookie.setMaxAge(24 * 60 * 60); // Set cookie expiration to 1 day (adjust as needed)

		// Add the cookie to the response
		response.addCookie(jwtCookie);

		// Return only the AuthenticationResponse object (optionally including the token
		// if you need it in the response body)
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}
}
