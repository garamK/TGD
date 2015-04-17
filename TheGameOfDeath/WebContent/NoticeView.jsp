<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="http://bootswatch.com/assets/css/bootswatch.min.css">
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>

<body>
<center>
	<c:if test="${empty userId}">
	<table border='1'>
	<tr>
		<th>번호</th>
		<td width="90%">${notice.noticeNum}</td>
	</tr>
	
	<tr>
		<th>작성일</th>
		<td>${notice.ndate}</td>
	</tr>
	<tr>
		<th>제목</th>
		<td >${notice.title}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>${notice.content}</td>
	</tr>
</table>
	</c:if>
	
	
	<c:if test="${not empty userId}">
	
	<h1> 공지상세화면</h1>
	<table border='1'>

	<tr>
		<th>번호</th>
		<td width="90%">${notice.noticeNum}</td>
	</tr>
	
	<tr>
		<th>작성일</th>
		<td>${notice.ndate}</td>
	</tr>
	<tr>
		<th>제목</th>
		<td >${notice.title}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>${notice.content}</td>
	</tr>
	</table>
	
	
	<c:if test="${sessionScope.userId eq 'Admin'}">
		<a href="Notice.do?action=LIST">공지목록</a>
	</c:if>
		<a href="Main.jsp">메인페이지</a>
	
	</c:if>
</center>
</body>
</html>