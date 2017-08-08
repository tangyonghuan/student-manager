package com.tyh.studentmanager.servlet;

import com.tyh.studentmanager.entity.Page;
import com.tyh.studentmanager.entity.Student;
import com.tyh.studentmanager.redis.RedisOpreate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by DWade on 2017/8/7.
 */
public class InfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNum;
        int pageSize = 10;
        List<Student> studentList = new ArrayList<Student>();

        try{
            pageNum = Integer.valueOf(request.getParameter("pageNum"));
        }catch(Exception e){
            pageNum = 1;
        }
        /**
         * 通过pageNum获取索引
         */
        Page page = new Page(pageNum,pageSize,0,100);
        int start = page.getStart();
        int end = page.getEnd();
        Set<String> ids = RedisOpreate.getIdSortByScore("sort",start,end);
        for(String id : ids){
            try {
                studentList.add(RedisOpreate.getStudentById(id));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("page",page);
        request.setAttribute("studentList",studentList);
        request.getRequestDispatcher("/WEB-INF/jsp/student_info.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
