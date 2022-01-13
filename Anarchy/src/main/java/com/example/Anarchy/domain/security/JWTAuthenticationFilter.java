package com.example.Anarchy.domain.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.Anarchy.domain.model.User;
import com.example.Anarchy.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final UserRepository userRepository;
    @Value("${anarchy.auth.password}")
    private String tokenPassword;
    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var validarToken = validarToken(request);
        if(validarToken.isPresent())
            SecurityContextHolder.getContext().setAuthentication(validarToken
                    .map(value->new UsernamePasswordAuthenticationToken(value, null, Collections.emptyList())).orElseThrow());
        filterChain.doFilter(request, response);
    }
    private Optional<User> validarToken(HttpServletRequest request){
        var token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(token == null || !token.startsWith(BEARER_PREFIX))
            return Optional.empty();
        var decodedToken = JWT.require(Algorithm.HMAC256(tokenPassword)).build()
                .verify(token.replace(BEARER_PREFIX, ""));
        return userRepository.findById(decodedToken.getClaim("id").asLong());
    }
}
