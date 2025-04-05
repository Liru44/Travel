package com.travel.travel.Config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        // JavaScript로 Alert 메시지 출력 후 리다이렉트
        response.setContentType("text/html;charset=UTF-8");
//        response.getWriter().print(
//                "<script>" +
//                        "alert('접근 권한이 없습니다. 메인 페이지로 이동합니다.');" +
//                        "location.href='/boardList';" +
//                        "</script>"
//        );
        response.getWriter().flush();
    }
}
