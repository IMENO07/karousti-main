package edu.usdb.cs.karousti.config;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String requestURI = request.getRequestURI();

        // Step 1: Bypass JWT filter for public endpoints
        if (isPublicEndpoint(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Step 2: Check for Authorization header
        final String authHeader = request.getHeader("Authorization");
        String jwt = null;
        String userEmail = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            userEmail = jwtService.extractUsername(jwt);
        }

        // Step 3: If JWT not found in header, check cookies
        if (jwt == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("jwt")) {
                        jwt = cookie.getValue();
                        userEmail = jwtService.extractUsername(jwt);
                        break;
                    }
                }
            }
        }

        // Step 4: Validate JWT and set authentication context
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }

    /**
     * Determines if the request URI matches public endpoints.
     */
    private boolean isPublicEndpoint(String uri) {
        return uri.startsWith("/api/v1/auth/");
    }
}
