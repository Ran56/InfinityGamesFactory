package com.infinity.gamesFactory.filter;


import com.infinity.gamesFactory.model.User;
import com.infinity.gamesFactory.service.FileInfoService;
import com.infinity.gamesFactory.service.JWTService;
import com.infinity.gamesFactory.service.UserService;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@WebFilter(filterName = "securityFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
public class SecurityFilter implements Filter {
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserService userService;
    @Autowired
    private FileInfoService fileInfoService;
    private Logger logger = LoggerFactory.getLogger(getClass());
    private Set<String> AUTH_URI = new HashSet<>(Arrays.asList("/auth","/auth/"));


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (userService == null) {
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, servletRequest.getServletContext());
        }


        HttpServletRequest request = (HttpServletRequest) servletRequest;
        int statusCode = authorization(request);
        if(statusCode == HttpServletResponse.SC_ACCEPTED)
        filterChain.doFilter(servletRequest,servletResponse);
        else ((HttpServletResponse)servletResponse).sendError(statusCode);
    }

    private int authorization(HttpServletRequest request)
    {
        int statusCode = HttpServletResponse.SC_UNAUTHORIZED;
        String uri = request.getRequestURI();
        String verb = request.getMethod();
        if(AUTH_URI.contains(uri)) return HttpServletResponse.SC_ACCEPTED;
        try
        {
            String token = request.getHeader("Authorization").replaceAll("^(.*?) ","");
            if(token==null || token.isEmpty()) return statusCode;
            Claims claims = jwtService.decryptJwtToken(token);
            if(claims.getId()!=null)
            {
                User user = userService.getById(Long.valueOf(claims.getId()));

                if(user == null) return statusCode;
//                statusCode = HttpServletResponse.SC_ACCEPTED;

                fileInfoService.userInside = user;

            }

            String allowedResources = "";
            switch (verb)
            {
                case "GET" : allowedResources = claims.get("allowedReadResources").toString(); break;
                case "POST" : allowedResources = claims.get("allowedCreateResources").toString();break;
                case "DELETE" : allowedResources = claims.get("allowedDeleteResources").toString();break;
                case "PUT" : allowedResources = claims.get("allowedUpdateResources").toString();break;
                case "PATCH" : allowedResources = (String)claims.get("allowedUpdateResources"); break;

            }

            for(String s: allowedResources.split(","))
            {
                if(s!="" && uri.trim().toLowerCase().startsWith(s.trim().toLowerCase())) {
                    statusCode = HttpServletResponse.SC_ACCEPTED;
                    break;
                }
            }

        }
        catch (Exception e)
        {
            logger.error("cannot verify the token",e);
        }
        return statusCode;
    }

}
