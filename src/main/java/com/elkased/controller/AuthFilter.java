package com.elkased.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/", "/todo", "/todo/*"})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {

        try {
            HttpServletRequest req = ((HttpServletRequest) servletRequest);
            HttpServletResponse resp = ((HttpServletResponse) servletResponse);
            HttpSession session = req.getSession();

            assert session != null;
            Object userstate = session.getAttribute("Active-user");

            if (userstate == null) {
                resp.sendRedirect(req.getContextPath() + "/login");
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (Exception e) {
            System.err.println(this.getClass().getName() + " => " + e.getMessage());
        }
    }

    @Override
    public void destroy() {

        Filter.super.destroy();
    }
}
