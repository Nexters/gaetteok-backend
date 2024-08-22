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
        if (request.getRequestURI().startsWith("/api/admin")) {
            // 헤더 없으면 설정할 수 있도록 친절하게 안내
            if (request.getHeader("Authorization") == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("관리자 토큰이 설정되지 않았습니다. 우측 상단 Authorize 설정에 관리자 토큰을 입력해주세요.");
                return;
            }
        }
        log.info("[{}] {}, token: {}, parameterCount: {}", request.getMethod(), request.getRequestURI(), request.getHeader("Authorization"), request.getParameterMap().values().size());
        request.getParameterMap().forEach((key, value) -> log.info("Request Parameter: {}={}", key, value));
        filterChain.doFilter(request, response);
    }

}
