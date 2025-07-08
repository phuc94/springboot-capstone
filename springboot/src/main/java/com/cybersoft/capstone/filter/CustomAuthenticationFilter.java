package com.cybersoft.capstone.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.cybersoft.capstone.dto.CustomAdminDetails;
import com.cybersoft.capstone.dto.CustomUserDetails;
import com.cybersoft.capstone.service.implement.AdminDetailsServiceImpl;
import com.cybersoft.capstone.service.implement.UserDetailsServiceImpl;
import com.cybersoft.capstone.utils.JwtHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private AdminDetailsServiceImpl adminDetailsServiceImpl;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authenHeader = request.getHeader("Authorization");

        if(authenHeader != null && authenHeader.startsWith("Bearer ")) {
            String token = authenHeader.substring(7);
            String email = jwtHelper.decodeToken(token);

            if (request.getRequestURI().startsWith("/admin")) {
                CustomAdminDetails admin = adminDetailsServiceImpl.loadUserByUsername(email);
                if(email != null) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(admin, null, admin.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } else if (request.getRequestURI().startsWith("/api")) {
                CustomUserDetails user = userDetailsServiceImpl.loadUserByUsername(email);
                if(email != null) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

}
