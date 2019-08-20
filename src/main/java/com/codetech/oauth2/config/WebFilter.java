package com.codetech.oauth2.config;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;




@javax.servlet.annotation.WebFilter("/*")
public class WebFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader(

                "Access-Control-Allow-Origin", "*");
  httpServletResponse.setHeader(

                "Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader(

                "Access-Control-Allow-Headers", "Origin");


        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // ...
    }

    @Override
    public void destroy() {
        // ...
    }
}
