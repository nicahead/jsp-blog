package com.nic.servlet;

import com.nic.dao.UserDao;
import com.nic.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserService userservice;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userservice = new UserService();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String re = request.getParameter("VerifyCode");
        String answer =(String)request.getSession().getAttribute("VerifyCode");
        if((userservice.VerifyUser(username,password))&&(re.equals(answer))) {
            HttpSession session = request.getSession();
            session.setAttribute("username",username);
            session.setMaxInactiveInterval(3*60);
            response.sendRedirect("PostlistServlet?role=1");
        }
        else
            request.getRequestDispatcher(request.getContextPath()+"/error.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
