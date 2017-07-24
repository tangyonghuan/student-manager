package com.tyh.redis.studentmanager.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.tyh.redis.studentmanager.entiy.Student;
import com.tyh.redis.studentmanager.redis.InfoOpreate;



public class AddServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/jsp/student_add.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Boolean validId = true;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		Student student = new Student();
		//����Studentʵ��
		String id = request.getParameter("id");
		//�ж�ID�Ƿ�Ϊ��
		if(id.isEmpty()){
			request.setAttribute("validname", "���ֲ���Ϊ�գ�");
			request.getRequestDispatcher("/WEB-INF/jsp/student_add.jsp").forward(request, response);
			
		}else{
			request.setAttribute("validname", "");
			student.setId(request.getParameter("id"));
			student.setName(request.getParameter("name"));
			
			//������ڸ�ʽ�����򷵻�ԭҳ��
			try {
				student.setBirthday(format.parse(request.getParameter("birthday")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				request.setAttribute("valid", "���ڸ�ʽ����");
				request.getRequestDispatcher("/WEB-INF/jsp/student_add.jsp").forward(request, response);
			}
			request.setAttribute("valid", "");
			student.setDescription(request.getParameter("description"));
		
			String avgscore = request.getParameter("avgscore");
			if(avgscore.isEmpty()){
				student.setAvgscore(0);
			}else{
				student.setAvgscore(Integer.valueOf(request.getParameter("avgscore")));
			}
			
			
			//�ж�ID �Ƿ��Ѿ�����
			Set<String>idSet = InfoOpreate.getSort("sort");

			if(idSet.contains(student.getId()))
				validId = false;
			
			
			if(validId){
				InfoOpreate.storage(student);
				request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
			}else{
				request.setAttribute("message", "�û����Ѵ���");
				request.getRequestDispatcher("/WEB-INF/jsp/student_add.jsp").forward(request, response);
				validId = true;
			}
			
			
		}
		
		
	}
	
}
