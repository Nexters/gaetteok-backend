package com.nexters.gaetteok.common.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("[{}] {}, token: {}, parameterCount: {}", request.getMethod(), request.getRequestURI(), request.getHeader("Authorization"), request.getParameterMap().values().size());
        request.getParameterMap().forEach((key, value) -> log.info("Request Parameter: {}={}", key, value));
        filterChain.doFilter(request, response);
    }

}
