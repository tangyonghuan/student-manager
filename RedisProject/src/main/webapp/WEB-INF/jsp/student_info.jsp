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
<
<script type="text/javascript">
    function jump(){
        var num = document.getElementById("jumpNum").value;
        window.location.href="${pageContext.request.contextPath}/info?pageNum="+num;
    }
</script>

<body>
<h2 align="center">学生信息管理</h2>
<div align="center">
    <a href="add" >新增</a>
</div>
<table border="1" align="center" style="width:50%">
    <tr>
        <th>ID</th>
        <th>姓名</th>
        <th>出生日期</th>
        <th>备注</th>
        <th>平均分</th>
        <th>操作</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${studentList}" var="student">
        <tr>
            <td><c:out value="${student.id}"></c:out></td>
            <td><c:out value="${student.name}"></c:out></td>

            <td><fmt:formatDate value="${student.birthday}" pattern="yyyy-MM-dd"/></td>
            <td><c:out value="${student.description}"></c:out></td>
            <td><c:out value="${student.avgScore}"></c:out></td>
            <td><a href="${request.contextPath}update?id=${student.id}&&pageNum=${page.pageNum}">修改</a></td>
            <td><a href="${request.contextPath}delete?id=${student.id}&&pageNum=${page.pageNum}"  onClick="return confirm('确定删除?')">删除</a></td>

        </tr>
    </c:forEach>
</table>


<br>
<div align="center">
    <br/>
    <a href="${pageContext.request.contextPath}/info?pageNum=1">首页</a>
    <a href="${pageContext.request.contextPath}/info?pageNum=${requestScope.page.pageNum-1}">上一页</a>
    ${page.pageNum}
    <a href="${pageContext.request.contextPath}/info?pageNum=${requestScope.page.pageNum+1}">下一页</a>
    <a href="${pageContext.request.contextPath}/info?pageNum=${requestScope.page.totalPage}">尾页</a>
    <input  size="3" name="jumpNum" id="jumpNum"><input type="button" value="跳转" onclick="jump()">
</div><br>

</body>
</html>