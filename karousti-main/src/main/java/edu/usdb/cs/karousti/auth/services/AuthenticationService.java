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
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(),
				request.getPassword());

		try {
			authenticationManager.authenticate(usernamePasswordAuthenticationToken);
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

		Cookie jwtCookie = new Cookie("jwt", jwtToken);
		jwtCookie.setHttpOnly(true);
		jwtCookie.setSecure(true);
		jwtCookie.setPath("/");
		jwtCookie.setMaxAge(24 * 60 * 60);

		response.addCookie(jwtCookie);

		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}
}
