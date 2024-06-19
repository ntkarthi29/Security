package com.basic.base.config;
import com.basic.base.service.JWTService;
import com.basic.base.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JWTService JwtService;
    private final UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if(StringUtils.isEmpty(authHeader) || !authHeader.startsWith("Bearer ")){ //authheader null is changed from original
            filterChain.doFilter(request,response);
            return;
        }
        jwt = authHeader.substring(7);
        userEmail = JwtService.extractUserName(jwt);
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails;
            try {
                userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if(JwtService.isTokenValid(jwt,userDetails)){
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                securityContext.setAuthentication(token);
                SecurityContextHolder.setContext(securityContext);
            }
        }
        filterChain.doFilter(request,response);
    }
}
