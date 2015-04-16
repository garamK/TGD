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
	<center>
	<table >
		<tr align="center">
			<td width="110"></td>
			<td width="90"><b>순위</b></td>
			<td width="90"><b>닉네임</b></td>
			<td width="90"><b>살해횟수</b></td>
			<td width="90"><b>사망횟수</b></td>
		</tr>
		<c:forEach var="s" items="${list}" varStatus="i">
			<tr align="center">
				<td><img width="100px" src="images/${s.image}"/></td>
				<td>${i.count}</td>
				<td>${s.nick}</td>
				<td>${s.kill}</td>
				<td>${s.death}</td>
			</tr>
		</c:forEach>
		
	</table>
	</center>
	<p>
</body>
</html>