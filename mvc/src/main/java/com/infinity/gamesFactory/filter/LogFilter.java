package com.infinity.gamesFactory.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@WebFilter(filterName = "logFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
public class LogFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private final List<String> excludeWords = Arrays.asList("newPasswd", "confirmPasswd", "passwd", "password");
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS");


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Instant startTime = Instant.now();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String logInfo = logInfo(request);
        filterChain.doFilter(servletRequest,servletResponse);
        logger.info(logInfo.replace("responseTime",String.valueOf(Instant.now().getEpochSecond()-startTime.getEpochSecond())));

    }

    private boolean isIgnoredWord(String word, List<String> excludeWords)
    {
        for(String excludedWord : excludeWords)
            if(word.toLowerCase().contains(excludedWord))
                return true;
            return false;
    }

    private String logInfo(HttpServletRequest request)
    {
        String formatData = null;
        String httpMethod = request.getMethod();
        LocalDateTime startDateTime = LocalDateTime.now();
        String requestURI = request.getRequestURI();
        String userIP = request.getRemoteHost();
        String  sessionID = request.getSession().getId();
        Enumeration<String> parameterNames = request.getParameterNames();//
        List<String> parameters = new ArrayList<>();
        while(parameterNames.hasMoreElements())
        {
            String paramName = parameterNames.nextElement();//
            if(isIgnoredWord(paramName,excludeWords)) continue;

            String paramValues = Arrays.asList(request.getParameterValues(paramName)).toString();
            parameters.add(paramName + "=" + paramValues);
        }
        if(!parameters.isEmpty())
            formatData = parameters.toString().replaceAll("^.|.$","");

        return new StringBuilder("| ")
                .append(formatter.format(startDateTime)).append(" | ")
                .append(userIP).append(" | ")
                .append(httpMethod).append(" | ")
                .append(requestURI).append(" | ")
                .append(sessionID).append(" | ")
                .append("responseTime ms").append(" | ")
                .append(formatData).toString();
    }



}
