<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
<h1>시즌별 회원관리 목록</h1>

<table>
	<tr>
	<th width="100">회원번호</th><th width="100">아이디</th><th width="100">닉네임</th><th width="100">강퇴</th>
	</tr>
	
	<c:forEach var="user" items="${list}">	
	<tr>
	<td>${user.userNum}</td><td>${user.userId}</td><td>${user.nick}</td><td><a href="UserList.do?action=DELETE&userId=${user.userId}">강퇴시킬거다</a></td>
	</tr>
	</c:forEach>
</table>
<p>
<a href="Admin.jsp">관리자 페이지</a>
</body>
</html>



