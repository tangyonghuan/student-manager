package com.tyh.studentmanager.servlet;

import com.tyh.studentmanager.redis.RedisOpreate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by DWade on 2017/8/7.
 */
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String path = "info?pageNum="+request.getParameter("pageNum");
        RedisOpreate.deleteStudentById(id);
        request.getRequestDispatcher(path).forward(request, response);
    }
}
