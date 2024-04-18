package com.subham.ATM.filter;

import com.subham.ATM.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    JwtService jwtService;

    @Autowired
    UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(@NonNull  HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {


         final String authHeaders = request.getHeader("authorization");
         String jwt;
         String username;
         if(authHeaders == null || !authHeaders.startsWith("Bearer ")){
             filterChain.doFilter(request,response);
             return;
         }

         jwt = authHeaders.substring(7);
        System.out.println(jwt);


       username =  jwtService.extraUsername(jwt);
        System.out.println(username);

         if(username != null &&  SecurityContextHolder.getContext().getAuthentication() == null){
         UserDetails userDetails = userDetailsService.loadUserByUsername(username);

             UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                     userDetails,
                     null,
                     userDetails.getAuthorities()
             );

             usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
             SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
             filterChain.doFilter(request,response);
        }
    }

    @Override
    protected  boolean shouldNotFilter(@NonNull HttpServletRequest request) throws  ServletException{
        return request.getServletPath().contains("/account/auth/*");
    }
}
