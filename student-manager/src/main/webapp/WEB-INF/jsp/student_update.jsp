<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息管理</title>
</head>
<body>
<form action="${request.contextPath}update" method="post">
	学号：<input value="${student.id}" type="text" name="id"><br>
	姓名：<input type="text" name="name" value="${student.name}"><br>
	出生日期：<input type="text" name="birthday" value="<fmt:formatDate value="${student.birthday}" pattern="yyyy-MM-dd"/>"><br>
	备注：<input type="text" name="description" value="${student.description}"><br>
	平均分：<input type="text" name="avgscore" value="${student.avgscore}"><br> 
	<div>
		<input type="submit" value="提交">
		<input type="button" value="返回" onClick="location.href='${pageContext.request.contextPath}/info?pageNum=1'">
	</div>
</form>
</body>
</html>