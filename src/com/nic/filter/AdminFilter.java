package com.nic.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter")
public class AdminFilter implements Filter {
    private FilterConfig config;  //定义全局的config变量
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;  //强制类型转换
        HttpServletResponse res = (HttpServletResponse) response;
        String noFilterPaths = config.getInitParameter("noFilterPaths");  //获取web.xml中配置的初始化参数
        if(noFilterPaths!=null){
            String[] noFilter = noFilterPaths.split(";");
            for (String path : noFilter) {
                if(path==null || "".equals(path))
                    continue;
                if(req.getRequestURI().indexOf(path)!=-1 ){    //匹配到不需要过滤的路径
                    chain.doFilter(request, response);         //允许放行
                    return;
                }
            }
        }
        HttpSession session = req.getSession();
        if(session.getAttribute("username")!=null){
            chain.doFilter(request, response);   //允许放行
        }else{
            res.sendRedirect(req.getContextPath()+"../login.html");
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;   //获取到config对象
    }
}
