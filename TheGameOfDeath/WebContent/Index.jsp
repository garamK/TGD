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
	<!--  로그인 안했을 때 -->
	<!--  로그인 했을 때 -->

	<c:choose>
		<c:when test="${empty UserId}">
			<center>
			<h1>죽음의 세계로 당신을 초대합니다.</h1>
			<p>
			<table>
				<tr>
					<th width="70" height="30">아이디</th>
					<td><input type="text" id="UserId" name="UserId" /></td>
				</tr>
				<tr>
					<th height="30">비밀번호</th>
					<td><input type="password" id="Pass" name="Pass" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<input type="submit" value="로그인" /> 
					<input type="reset" value="취소" />
					<a href="Register.html">회원가입</a>
					</td>
				</tr>
			</table>
			<p />
			</center>
		</c:when>

		<c:when test="${not empty UserId}">
			<a href="login.do?action=LOGOUT">로그아웃</a>
			<p />
			<a href="NoticeInput.jsp">공지사항</a>
			<p />
			<a href="notice.do?action=LIST">공지목록</a>
			<p />
		</c:when>
	</c:choose>
</body>
</html>