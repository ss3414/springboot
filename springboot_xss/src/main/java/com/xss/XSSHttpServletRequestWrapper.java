package com.xss;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.Map;

public class XSSHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public XSSHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        return super.getParameter(name);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return super.getParameterMap();
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return super.getParameterNames();
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] before = super.getParameterValues(name);
        String[] after = new String[before.length];
        /* 正则过滤</> */
        for (int i = 0; i < before.length; i++) {
            after[i] = before[i].replaceAll("<", "").replaceAll(">", "");
        }
        return after;
//        return super.getParameterValues(name);
    }

}
