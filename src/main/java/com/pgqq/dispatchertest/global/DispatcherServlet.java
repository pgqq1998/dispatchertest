package com.pgqq.dispatchertest.global;

import com.pgqq.dispatchertest.scan.RequestMappingScan;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;

public class DispatcherServlet extends GenericServlet {
    private Map<String,Method> urlToMethodMap;
    @Override
    public void init() throws ServletException {
        super.init();
        //    扫描注解，初始化(url,Method)的HashMap
        urlToMethodMap = RequestMappingScan.scan("com.pgqq.dispatchertest");
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //    访问的url
        String path = request.getServletPath();
        try {
            //    TODO 单独处理静态资源
            //    通过url获得对应类和方法，并调用
            Method m = urlToMethodMap.get(path);
            Class c = m.getDeclaringClass();
            m.invoke(c.newInstance(),request,response);
        } catch (Exception e) {
            //    404
            PrintWriter pw = response.getWriter();
            pw.write("404");
            e.printStackTrace();
        }
    }
}
