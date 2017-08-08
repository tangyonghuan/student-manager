package com.tyh.studentmanager.servlet;

import com.tyh.studentmanager.entity.Student;
import com.tyh.studentmanager.redis.RedisOpreate;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by DWade on 2017/8/7.
 */
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/student_add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Student student = new Student();
        String id = request.getParameter("id");

        /**
         * 判断id是否为空
         */
        if(StringUtils.isEmpty(id)){
            request.setAttribute("nameEmpty", "id不能为空！");
            request.getRequestDispatcher("/WEB-INF/jsp/student_add.jsp").forward(request, response);

        }else {
            request.setAttribute("nameEmpty", "");
            student.setId(request.getParameter("id"));
            student.setName(request.getParameter("name"));
        }

        /**
         * 判断日期格式
         */
        try {
            student.setBirthday(format.parse(request.getParameter("birthday")));
        }catch (ParseException e) {
            request.setAttribute("dateError", "日期格式错误");
            request.getRequestDispatcher("/WEB-INF/jsp/student_add.jsp").forward(request, response);
        }
        request.setAttribute("dateError", "");
        student.setDescription(request.getParameter("description"));

        /**
         * 判断分数是否错误
         */
        try {
            student.setAvgScore(Integer.valueOf((request.getParameter("avgScore"))));
            request.setAttribute("scoreError","");
        }catch (Exception e){
            request.setAttribute("scoreError", "分数输入错误");
            request.getRequestDispatcher("/WEB-INF/jsp/student_add.jsp").forward(request, response);
        }

        /**
         * 判断id是否存在
         */
        if(!RedisOpreate.idIsExist(student.getId())){
            RedisOpreate.storageStudent(student);
            request.setAttribute("idExist", "");
            request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);

        }else{
            request.setAttribute("idExist", "用户名已存在");
            request.getRequestDispatcher("/WEB-INF/jsp/student_add.jsp").forward(request, response);
        }
    }
}
