package com.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String URI = request.getRequestURI();
        /* 获取普通和multipart/form-data表单参数 */
        if ("/form".equals(URI) || "/singleUpload".equals(URI) || "/batchUpload".equals(URI)) {
            System.out.println(request.getParameter("name"));
        }
        return true;
    }

}
