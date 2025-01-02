package com.example.api_server.global.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {


        String path = request.getRequestURI();

        // 인증이 필요 없는 경로 설정
        if (path.equals("/") ||path.equals("/login") || path.equals("/signup") || path.equals("/check-username")) {
            filterChain.doFilter(request, response);
            return;
        }

//        HttpSession session = request.getSession(false);
//        if (session == null || session.getAttribute("userId") == null) {
//            System.out.println("Session is null");
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            response.getWriter().write("로그인이 필요합니다.");
//            return;
//        }

        // 다음 필터로 요청 전달
        filterChain.doFilter(request, response);
    }
}
