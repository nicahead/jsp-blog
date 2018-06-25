package com.nic.servlet;

import com.nic.model.Article;
import com.nic.service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "PosteditServlet")
public class PosteditServlet extends HttpServlet {
    ArticleService articleService;
    Article article;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        articleService = new ArticleService();
        String action = request.getParameter("action");
        int id=0;
        if (action.equals("delete")) {
            if (articleService.deleteArticle(Integer.parseInt(request.getParameter("id")))) {
                response.getWriter().write("删除成功");
            }
        }
        else {
            article = new Article();
            article.setTitle(request.getParameter("title"));
            article.setMd_content(request.getParameter("test-editormd-markdown-doc"));
            article.setHtml_content(request.getParameter("text"));
            int top = request.getParameter("top") == null ? 0 : 1;
            article.setTop(top);
            String year = request.getParameter("year");
            String month = request.getParameter("month");
            String day = request.getParameter("day");
            String hour = request.getParameter("hour");
            String minute = request.getParameter("minute");
            String createdate = year + "-" + month + "-" + day + " " + hour + ":" + minute;
            article.setCreatedate(createdate);
            article.setSubtitle(request.getParameter("subtitle"));
            article.setMain_id(Integer.parseInt(request.getParameter("main_id")));
            if(request.getParameter("sub_id")!=null)
                article.setSub_id(Integer.parseInt(request.getParameter("sub_id")));
            if(request.getParameter("id")!=null)
                id = Integer.parseInt(request.getParameter("id"));
            if (action.equals("add")) {
                if (articleService.addArticle(article) == true) {
                    request.getRequestDispatcher(request.getContextPath() + "/success.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher(request.getContextPath() + "/error.jsp").forward(request, response);
                }
            } else if (action.equals("update")) {
                if (articleService.updateArticle(article, id) == true) {
                    request.getRequestDispatcher(request.getContextPath() + "/success.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher(request.getContextPath() + "/error.jsp").forward(request, response);
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}