package com.example.rail_e_ticket_api.filter;

import com.example.rail_e_ticket_api.component.JwtProvider;
import com.example.rail_e_ticket_api.entity.Owner;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if(token!=null && token.startsWith("Bearer ")){
          token=token.substring(7);
          if(!jwtProvider.validateJwtToken(token))
              throw new JwtException("TOKEN IS INVALID");
            Owner owner = jwtProvider.getOwnerByToken(token);
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(owner.getUsername(),null,owner.getAuthorities())
            );
        }
        filterChain.doFilter(request,response);
    }
}
