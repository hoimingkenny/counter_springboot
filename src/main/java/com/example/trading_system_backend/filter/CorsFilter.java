package com.example.trading_system_backend.filter;


import com.example.trading_system_backend.service.IAccountService;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {}


    @Autowired
    private IAccountService accountService;

    private Set<String> whiteRootPaths = Sets.newHashSet(
            "login", "msgsocket", "test"
    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // solve cross-origin request
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        response.setHeader("Access-Control-Allow-Origin","*");

        String curOrigin = request.getHeader("Origin");
        System.out.println("###CORS current origin: " + curOrigin + "###");

        // http://localhost:8090/login/pwdsetting
        String path = request.getRequestURI(); //  "/login/pwdsetting"
        String[] split = path.split("/");

        if (split.length < 2) {
            request.getRequestDispatcher(
                    "/login/loginfail"
            ).forward(servletRequest, servletResponse);
        } else {
            if(!whiteRootPaths.contains(split[1])){
                // Not in whitelist -> check token
                if(accountService.accountExistInCache(
                        request.getParameter("token")
                )){
                    filterChain.doFilter(servletRequest, servletResponse);
                }else {
                    request.getRequestDispatcher(
                            "/login/loginfail"
                    ).forward(servletRequest, servletResponse);
                }
            }else {
                // In whitelist -> pass
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }

        // let the request pass
        filterChain.doFilter(servletRequest, servletResponse);
     }

    @Override
    public void destroy() {}
}
