package com.nic.servlet;

import com.google.gson.Gson;
import com.nic.model.Category;
import com.nic.model.MainCategory;
import com.nic.model.SubCategory;
import com.nic.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CategoryServlet")
public class CategoryServlet extends HttpServlet {
    CategoryService categoryService = new CategoryService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        if (request.getParameter("action").equals("ajaxmain")) {
            ajaxMain(request, response);
        } else if (request.getParameter("action").equals("ajaxsub")) {
            ajaxSub(request, response);
        } else if (request.getParameter("action").equals("getall")) {
            getCategory(request, response);
        }
    }

    private void ajaxMain(HttpServletRequest request, HttpServletResponse response) {
        List<MainCategory> result = categoryService.getMaincategory();
        Map<Integer, String> mainMap = new HashMap<Integer, String>();
        Gson gson = new Gson();
        for (MainCategory item : result)
            mainMap.put(item.getId(), item.getName());
        String json = gson.toJson(mainMap);
        response.setContentType("application/json");
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            pw.print(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pw.close();
        }
    }

    private void ajaxSub(HttpServletRequest request, HttpServletResponse response) {
        int main_id = Integer.parseInt(request.getParameter("main_id"));
        List<SubCategory> result = categoryService.getSubcategory(main_id);
        Map<Integer, String> mainMap = new HashMap<Integer, String>();
        Gson gson = new Gson();
        for (SubCategory item : result)
            mainMap.put(item.getId(), item.getName());
        String json = gson.toJson(mainMap);
        response.setContentType("application/json");
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            pw.print(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pw.close();
        }
    }

    private void getCategory(HttpServletRequest request, HttpServletResponse response) {
        Category searchModel = new Category();
        if (request.getParameter("main_id") != null) {
            searchModel.setMain_id(Integer.parseInt(request.getParameter("main_id")));
        }
        if (request.getParameter("sub_id") != null) {
            searchModel.setMain_id(Integer.parseInt(request.getParameter("sub_id")));
        }
        List<MainCategory> mainCategory = categoryService.getMaincategory();
        request.setAttribute("mainCategory", mainCategory);
        List<Category> category = categoryService.getCategory(searchModel);
        request.setAttribute("category", category);
        try {
            request.getRequestDispatcher(request.getContextPath() + "/admin/category.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
