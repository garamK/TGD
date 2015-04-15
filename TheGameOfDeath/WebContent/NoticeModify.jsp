<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
<h1>공지 수정 화면</h1>
<form action="Notice.do?action=UPDATESAVE&noticeNum=${notice.noticeNum}" method="post">
<table border='1'>

	<tr>
		<th>번호</th>
		<td>${notice.noticeNum}</td>
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
		<td height="50"><textarea rows="5" cols="50" name="content">${notice.content}</textarea></td>
	</tr>
	
	<tr>
		<td colspan='2'>
			<input type = "submit" value = "수정등록" />	
		</td>
	</tr>
</table>
</form>

<a href="Notice.do?action=LIST">공지목록</a>
<a href="Main.jsp">메인페이지</a>
<a href="Notice.do?action=DELETE&noticeNum=${notice.noticeNum}">공지 삭제</a>

</body>
</html>