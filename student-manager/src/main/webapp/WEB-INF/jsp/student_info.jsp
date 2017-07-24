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
    		<td><c:out value="${student.avgscore}"></c:out></td>
    		<td><a href="${request.contextPath}update?id=${student.id}">修改</a></td>
    		<td><a href="${request.contextPath}delete?id=${student.id}"  onClick="return confirm('确定删除?')">删除</a></td>
    			
  		</tr>
  		</c:forEach>
	</table>

	
	<br>
 	<div align="center">
		  <br/>
            <a href="${pageContext.request.contextPath}/info?pageNum=1">首页</a>
            <%--如果当前页为第一页时，就没有上一页这个超链接显示 --%>
            <c:if test="${requestScope.pageBean.pageNum ==1}">
                <c:forEach begin="${requestScope.pageBean.start}" end="${requestScope.pageBean.end}" step="1" var="i">
                    <c:if test="${requestScope.pageBean.pageNum == i}">
                        ${i}
                    </c:if>                
                    <c:if test="${requestScope.pageBean.pageNum != i}">
                        <a href="${pageContext.request.contextPath}/info?pageNum=${i}">${i}</a>                                        
                    </c:if>                        
                </c:forEach>
                <a href="${pageContext.request.contextPath}/info?pageNum=${requestScope.pageBean.pageNum+1}">下一页</a>                    
            </c:if>
            
            <%--如果当前页不是第一页也不是最后一页，则有上一页和下一页这个超链接显示 --%>
            <c:if test="${requestScope.pageBean.pageNum > 1 && requestScope.pageBean.pageNum < requestScope.pageBean.totalPage}">
                <a href="${pageContext.request.contextPath}/info?pageNum=${requestScope.pageBean.pageNum-1}">上一页</a>
                <c:forEach begin="${requestScope.pageBean.start}" end="${requestScope.pageBean.end}" step="1" var="i">    
                    <c:if test="${requestScope.pageBean.pageNum == i}">
                        ${i}
                    </c:if>            
                    <c:if test="${requestScope.pageBean.pageNum != i}">
                        <a href="${pageContext.request.contextPath}/info?pageNum=${i}">${i}</a>                                        
                    </c:if>                        
                </c:forEach>
                <a href="${pageContext.request.contextPath}/info?pageNum=${requestScope.pageBean.pageNum+1}">下一页</a>    
            </c:if>
            
            <%-- 如果当前页是最后一页，则只有上一页这个超链接显示，下一页没有 --%>
            <c:if test="${requestScope.pageBean.pageNum == requestScope.pageBean.totalPage}">
                <a href="${pageContext.request.contextPath}/info?pageNum=${requestScope.pageBean.pageNum-1}">上一页</a>
                <c:forEach begin="${requestScope.pageBean.start}" end="${requestScope.pageBean.end}" step="1" var="i">
                    <c:if test="${requestScope.pageBean.pageNum == i}">
                        ${i}
                    </c:if>
                    <c:if test="${requestScope.pageBean.pageNum != i}">
                        <a href="${pageContext.request.contextPath}/info?pageNum=${i}">${i}</a>                                        
                    </c:if>                
                </c:forEach>
            </c:if>
            <%--尾页 --%>
            <a href="${pageContext.request.contextPath}/info?pageNum=${requestScope.pageBean.totalPage}">尾页</a>
	</div>
</body>
</html>