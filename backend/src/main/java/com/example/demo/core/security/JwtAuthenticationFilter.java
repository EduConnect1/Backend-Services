package com.example.demo.core.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        if (isPublicEndpoint(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String jwt = getJwtFromRequest(request);
            
            if (StringUtils.hasText(jwt) && jwtService.validateToken(jwt)) {
                String email = jwtService.getEmailFromToken(jwt);
                
                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    Claims claims = jwtService.getClaims(jwt);
                    

                    List<SimpleGrantedAuthority> authorities = extractAuthorities(claims);
                    
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            authorities
                    );
                    
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (ExpiredJwtException ex) {
            log.error("JWT Token has expired: {}", ex.getMessage());
            request.setAttribute("expired", ex.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT Token has expired");
            return;
        } catch (JwtException | IllegalArgumentException ex) {
            log.error("JWT validation error: {}", ex.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTH_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(BEARER_PREFIX.length());
        }
        return null;
    }

    private List<SimpleGrantedAuthority> extractAuthorities(Claims claims) {
        try {
            String role = claims.get("role", String.class);
            if (role != null) {
                return List.of(new SimpleGrantedAuthority("ROLE_" + role));
            }
        } catch (Exception e) {
            log.warn("Failed to extract authorities from token: {}", e.getMessage());
        }
        return List.of();
    }

    private boolean isPublicEndpoint(String requestURI) {

        return requestURI.startsWith("/api/auth/register") ||
               requestURI.startsWith("/api/auth/login") ||
               requestURI.startsWith("/api/auth/verify-email") ||
               requestURI.startsWith("/api/auth/forgot-password") ||
               requestURI.startsWith("/api/auth/reset-password") ||
               requestURI.startsWith("/v3/api-docs") ||
               requestURI.startsWith("/swagger-ui") ||
               requestURI.startsWith("/swagger-resources") ||
               requestURI.startsWith("/webjars");
    }
}
