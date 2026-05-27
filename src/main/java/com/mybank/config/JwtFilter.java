package com.mybank.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mybank.service.JWTService;
import com.mybank.service.UserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JWTService jwts;
    private final UserDetailsService uds;

    public JwtFilter(JWTService jwts, UserDetailsService uds) {
        this.jwts = jwts;
        this.uds = uds;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();

        // ✅ Skip auth endpoints
        if (path.startsWith("/api/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        // ❌ No token → continue without authentication
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // 🔥 Extract token
            String token = authHeader.substring(7);

            // 🔥 Extract username from token
            String username = jwts.extractUserName(token);

            // ❌ Invalid token
            if (username == null) {
                filterChain.doFilter(request, response);
                return;
            }

            // 🔥 If not already authenticated
            if (SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = uds.loadUserByUsername(username);

                // 🔥 Validate token
                if (jwts.validateToken(token, userDetails)) {

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    System.out.println("TOKEN = " + token);
                    System.out.println("USERNAME = " + username);
                    System.out.println("VALID = " + jwts.validateToken(token, userDetails));
                }
            }

        } catch (Exception e) {
            // ❌ Any JWT error → just continue (avoid breaking request)
            System.out.println("JWT Filter Error: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
    
}