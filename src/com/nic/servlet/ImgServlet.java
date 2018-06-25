package com.nic.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "ImgServlet")
public class ImgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedImage bfi = new BufferedImage(68, 25, BufferedImage.TYPE_INT_RGB); //图像缓冲区
        Graphics g = bfi.getGraphics();
        StringBuffer sb = new StringBuffer();
        //画背景框
        Color color = new Color(200,215,250);
        g.setColor(color);
        g.fillRect(0, 0, 68,30);
        //第一个数字
        Random r = new Random();
        int tmp1 = r.nextInt(20);
        g.setColor(new Color(r.nextInt(100),r.nextInt(100),r.nextInt(100))); //设置随机颜色
        g.drawString(tmp1+"",3, 18);
        //加号
        g.setColor(new Color(r.nextInt(100),r.nextInt(100),r.nextInt(100))); //设置随机颜色
        g.drawString("+",18, 18);
        //第二个数字
        int tmp2 = r.nextInt(20);
        g.setColor(new Color(r.nextInt(100),r.nextInt(100),r.nextInt(100))); //设置随机颜色
        g.drawString(tmp2+"",33, 18);
        //等号
        g.setColor(new Color(r.nextInt(100),r.nextInt(100),r.nextInt(100))); //设置随机颜色
        g.drawString("=",48, 18);
        //问号
        g.setColor(new Color(r.nextInt(100),r.nextInt(100),r.nextInt(100))); //设置随机颜色
        g.drawString("?",57, 18);
        //保存到session
        int result = tmp1+tmp2;
        request.getSession().setAttribute("VerifyCode", result+"");
        //写入response输出流
        ImageIO.write(bfi, "JPG", response.getOutputStream());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
}
