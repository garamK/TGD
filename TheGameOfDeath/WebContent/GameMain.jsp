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
	<textarea rows="20" cols="50" readonly="readonly">
<c:if test="${not empty msg}">${msg}</c:if>
<c:if test="${not empty eventList}">
<c:forEach var="ev" items="${eventList}">
${ev.msg}
</c:forEach>
</c:if>
</textarea>
	<a href="Game.do?action=explore" class="btn btn-default">탐색</a>
	<p>
		유저 정보<br>
	<table>
		<tr>
			<td><img src="images/${userInfo.image}" /></td>
			<td>${userInfo.nick}</td>
		</tr>
		<tr>
			<td>체력</td>
			<td>${userInfo.health}/ ${userInfo.maxHealth}</td>
		</tr>
		<tr>
			<td>공격력</td>
			<td>${userInfo.power}</td>
		</tr>
		<tr>
			<td>살해수</td>
			<td>${userInfo.kill}</td>
		</tr>
		<tr>
			<td>사망수</td>
			<td>${userInfo.death}</td>
		</tr>
		<tr>
			<td>위치</td>
			<td>${userInfo.location}</td>
		</tr>
		<tr>
			<td>장비</td>
			<td>${weapon}</td>
		</tr>

	</table>


	<form action="Game.do?action=itemUse" method="post">
	<table>
	<tr>
	<th width="100">선택</th><th width="100">아이템</th><th width="100">수량</th><th width="100">체력</th>
	</tr>
		<c:forEach var="i" items="${itemList}">
			<tr>
				<td><input	type="radio" name="itemNum" value="${i.itemNum}" /></td>
				<td>${i.itemName}</td>
				<td>${i.quantity}</td>
				<td>${i.stat}</td>
			</tr>
		</c:forEach>
	</table>
	
	<input type="submit" value="아이템사용"/>
	</form>
	
	
	

</body>
</html>