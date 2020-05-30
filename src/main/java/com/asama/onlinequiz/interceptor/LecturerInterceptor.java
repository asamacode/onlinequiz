package com.asama.onlinequiz.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.asama.onlinequiz.model.AppUser;

@Component
public class LecturerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        AppUser user = (AppUser) session.getAttribute("user");
        
        if (user == null) {
            session.setAttribute("back-url", request.getRequestURI());
            response.sendRedirect("/login?message=You need to login first !");
            return false;
        } else {
            if (user.getRole().getName() == "LECTURER") {
                return true;
            } else {
                response.sendRedirect("/login?message=You not authorized !");
                return false;
            }
        }
    }
}
