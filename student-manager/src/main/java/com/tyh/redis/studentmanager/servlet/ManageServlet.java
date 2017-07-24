package com.tyh.redis.studentmanager.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tyh.redis.studentmanager.entiy.Student;
import com.tyh.redis.studentmanager.redis.InfoOpreate;
import com.tyh.redis.studentmanager.redis.PageBean;

public class ManageServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Set<String> sort = InfoOpreate.getSort("sort");
		List<Student> students = new ArrayList<Student>();
		int pageNum;
		try {
			pageNum = Integer.valueOf(request.getParameter("pageNum"));
		} catch (Exception e) {
			// TODO: handle exception
			pageNum = 1;
		}
		
		
		int pageSize = 10; 
		
		for(String id:sort){
			try {
				students.add(InfoOpreate.getStudent(id));
			
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		PageBean pBean = findStudentByPage(pageNum, pageSize, students);
		request.setAttribute("studentList", pBean.getList());
		request.setAttribute("pageBean", pBean);
		request.getRequestDispatcher("/WEB-INF/jsp/student_info.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	public PageBean<Student> findStudentByPage(int pageNum,int pageSize,List<Student> students){
		int totalRecord = students.size();
		PageBean pb = new PageBean(pageNum, pageSize, totalRecord);
		int startIndex = pb.getStartIndex();
		pb.setList(find(startIndex,pageSize,students));
		return pb;
	}

	//根据索引查记录
	private List<Student> find(int startIndex, int pageSize,List<Student>students) {
		// TODO Auto-generated method stub
		List<Student> list = new ArrayList<Student>();
		int totalRecord = students.size();
		if(startIndex+pageSize<=totalRecord){
			for(int i=startIndex;i<startIndex+pageSize;i++){
				list.add(students.get(i));
			}
		}else{
			for(int i=startIndex;i<totalRecord;i++){
				list.add(students.get(i));
			}
		}
		
		return list;
	}
	
}
