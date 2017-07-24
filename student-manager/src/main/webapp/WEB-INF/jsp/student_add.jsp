<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="add" method="post">
	学号：<input type="text" name="id"><font color="red">${message}</font><font color="red">${validname}</font><br>
	姓名：<input type="text" name="name"><br>
	出生日期：<input type="text" name="birthday"><font color="red">${valid}</font><br>
	备注：<input type="text" name="description"><br>
	平均分：<input type="text" name="avgscore"><br> 
	<div>
		<input type="submit" value="提交">
		<input type="button" value="返回" onClick="location.href='${pageContext.request.contextPath}/info?pageNum=1'">
	</div>
</form>
</body>
</html>