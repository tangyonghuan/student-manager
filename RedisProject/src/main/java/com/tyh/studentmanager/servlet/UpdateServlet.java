package com.tyh.studentmanager.servlet;

import com.tyh.studentmanager.entity.Student;
import com.tyh.studentmanager.redis.RedisOpreate;
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
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String pageNum = request.getParameter("pageNum");
            String id = request.getParameter("id");
            Student student = RedisOpreate.getStudentById(id);
            request.setAttribute("student", student);
            request.setAttribute("pageNum",pageNum);
            request.getRequestDispatcher("/WEB-INF/jsp/student_update.jsp").forward(request, response);
        } catch (ParseException e) {

            e.printStackTrace();
        }catch (Exception e) {
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pageNum = request.getParameter("pageNum");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Student student = null;
        try {
            student = RedisOpreate.getStudentById(request.getParameter("id"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        /**
         * 判断日期格式
         */
        try {
            student.setName(request.getParameter("name"));
            student.setBirthday(format.parse(request.getParameter("birthday")));
            student.setDescription(request.getParameter("description"));

        } catch (ParseException e) {

            request.setAttribute("id", student.getId());
            request.setAttribute("dateError","日期格式有误");
            request.setAttribute("student",student);
            request.setAttribute("pageNum",pageNum);
            request.getRequestDispatcher("/WEB-INF/jsp/student_update.jsp").forward(request, response);
        }

        /**
         * 判断分数是否错误
         */
        try{

            student.setAvgScore(Integer.valueOf(request.getParameter("avgScore")));
            request.setAttribute("scoreError","");
        }catch (Exception e){
            request.setAttribute("scoreError","输入分数有误");
            request.setAttribute("student",student);
            request.setAttribute("pageNum",pageNum);
            request.getRequestDispatcher("/WEB-INF/jsp/student_update.jsp").forward(request,response);
        }
        RedisOpreate.storageStudent(student);
        request.setAttribute("pageNum",pageNum);
        request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
    }
}
