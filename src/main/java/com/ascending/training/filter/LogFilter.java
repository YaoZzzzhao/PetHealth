package com.ascending.training.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebFilter(filterName = "logFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
    public class LogFilter implements Filter{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final List<String> excludeWords = Arrays.asList("newPassword","confirmPassword","password","passwd");
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss.SSS");

    @Override
    public void init(FilterConfig filterConfig){

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        HttpServletRequest req = (HttpServletRequest)request;
        String logInfo = logInfo(req);
        logger.info(logInfo.replace("responseTime",String.valueOf(System.currentTimeMillis()-startTime)));
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy(){

    }
    private boolean isIgnoredWord(String word, List<String> excludeWords){
        for(String i : excludeWords){
            if(word.toLowerCase().contains(i)) return true;
        }
        return false;
    }

    private String logInfo(HttpServletRequest req){
        String formData = null;
        String httpMethod = req.getMethod();

        Date startDateTime = new Date();
        String requestURL = req.getRequestURI();
        String userIP = req.getRemoteHost();
        String sessionID = req.getSession().getId();
        Enumeration<String> parameterNames = req.getParameterNames();
        List<String> parameters = new ArrayList();

        while (parameterNames.hasMoreElements()){
            String paramName = parameterNames.nextElement();
            if(isIgnoredWord(paramName,excludeWords)) continue;
            String paramValues = Arrays.asList(req.getParameterValues(paramName)).toString();
            parameters.add(paramName + "-" + paramValues);
        }

        if(!parameters.isEmpty()){
            formData = parameters.toString().replaceAll("^.|.$","");
        }
        return new StringBuilder("|")
                .append(formatter.format(startDateTime)).append("|")
                .append(userIP).append(" | ")
                .append(httpMethod).append(" | ")
                .append(requestURL).append(" | ")
                .append(sessionID).append(" | ")
                .append("responseTime ms").append(" | ")
                .append(formData).toString();
    }
}
