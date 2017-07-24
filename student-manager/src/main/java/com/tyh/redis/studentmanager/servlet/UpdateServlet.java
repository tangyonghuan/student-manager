package com.tyh.redis.studentmanager.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tyh.redis.studentmanager.entiy.Student;
import com.tyh.redis.studentmanager.redis.InfoOpreate;

public class UpdateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		try {
			Student student = InfoOpreate.getStudent(id);
			request.setAttribute("student", student);
			request.getRequestDispatcher("/WEB-INF/jsp/student_update.jsp").forward(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Boolean valid = true;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		Student student = new Student();
	
		try {
			
			student.setId(request.getParameter("id"));
			student.setName(request.getParameter("name"));
			student.setBirthday(format.parse(request.getParameter("birthday")));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			request.setAttribute("id", student.getId());
			request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
			
			valid =false;
		}
		student.setDescription(request.getParameter("description"));
		String avgscore = request.getParameter("avgscore");
		if(avgscore.isEmpty()){
			student.setAvgscore(0);
		}else{
			student.setAvgscore(Integer.valueOf(request.getParameter("avgscore")));
		}
		
		
		if(valid==true){
			InfoOpreate.storage(student);		
			request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
		}
	
		
	}
	
}
