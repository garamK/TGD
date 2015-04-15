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
	<h1>생존자 목록</h1>

	<table>
		<tr>
			<th>이미지</th>
			<th>회원번호</th>
			<th>아이디</th>
			<th>닉네임</th>
			<th>체력</th>
			<th>공격력</th>
			<th>행동력</th>
			<th>킬점수</th>
			<th>사망횟수</th>
		</tr>

		<c:forEach var="s" items="${list}">
			<tr>
			<td><img src="images/${s.image}"/></td>
				<td>${s.userNum}</td>
				<td>${s.userId}</td>
				<td>${s.nick}</td>
				<td>${s.health}</td>
				<td>${s.power}</td>
				<td>${s.stamina}</td>
				<td>${s.kill}</td>
				<td>${s.death}</td>
			</tr>
		</c:forEach>
		
	</table>
	<p>
</body>
</html>