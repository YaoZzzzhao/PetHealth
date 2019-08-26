package com.ascending.training.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@WebFilter(filterName = "securityName", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
public class SecurityFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static String AUTH_URL = "/auth";

    @Override
    public void init(FilterConfig filterConfig){

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException{
        HttpServletRequest req = (HttpServletRequest)request;

        logger.info("Filter shows here.");
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy(){

    }
}
