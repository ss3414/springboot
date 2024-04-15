package com.demo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class DemoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String URI = request.getRequestURI();
        /* 获取普通和multipart/form-data表单参数 */
        if ("/form".equals(URI) || "/singleUpload".equals(URI) || "/batchUpload".equals(URI)) {
            System.out.println(request.getParameter("name"));
        }
        return true;
    }

}
