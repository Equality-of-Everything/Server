package com.eoe.filter;

import com.auth0.jwt.interfaces.Claim;
import com.eoe.utils.JWTTokenUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Author : Zhang
 * @Date : Created in 2023/11/28 21:09
 * @Decription : JWT过滤器,拦截 /secure 的请求
 * JWT过滤器中进行token的校验和判断，token不合法直接返回，合法则解密数据并把数据放到request中供后续使用。
 */

@Slf4j
@WebFilter(filterName = "JwtFilter", urlPatterns = "/secure/*")
public class JWTFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setCharacterEncoding("UTF-8");

        // 获取 header 里面的Token
        final String token = request.getHeader("Authorization");

        // 除了 OPTIONS 请求，其他请求都必须携带 token
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
        } else {

            if (token == null) {
                response.getWriter().write("没有token！");
                return;
            }

            Map<String, Claim> userLoginData = JWTTokenUtil.verifyToken(token);
            if (userLoginData == null) {
                response.getWriter().write("token不合法！");
                return;
            }
            String userId = userLoginData.get("userId").asString();
            System.out.println("JWTFilter:"+userId);
            String userName = userLoginData.get("username").asString();
            String password = userLoginData.get("password").asString();
            // 将用户信息放到request中
            request.setAttribute("userId", userId);
            request.setAttribute("username", userName);
            request.setAttribute("password", password);
            filterChain.doFilter(request, response);
        }


    }
}
