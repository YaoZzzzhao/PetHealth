//package com.ascending.training.filter;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
//@WebFilter(filterName = "logFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
//    public class LogFilter implements Filter{
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//    private static String AUTH_URI = "/auth";
//
//    @Override
//    public void init(FilterConfig filterConfig){
//
//    }
//
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//        ServletRequest req = (HttpServletRequest)request;
//        int statusCode = authorization(req);
//    }
//
//    @Override
//    public void destroy(){
//
//    }
//}
