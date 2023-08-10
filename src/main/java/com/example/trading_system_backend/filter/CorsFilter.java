package com.example.trading_system_backend.filter;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // solve cross-origin request
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        response.setHeader("Access-Control-Allow-Origin","*");

        String curOrigin = request.getHeader("Origin");
        System.out.println("###CORS current origin: " + curOrigin + "###");




        filterChain.doFilter(servletRequest, servletResponse);
     }

    @Override
    public void destroy() {}
}
