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
	<h1>공지 사항</h1>

	<table border='1'>
		<tr>
			<th width="100">번호</th>
			<th>제 목</th>
		</tr>

		<c:forEach var="notice" items="${list}">
			<tr>
				<td>${notice.noticeNum}</td>
				<td><a href="Notice.do?action=VIEW&num=${notice.noticeNum}"> ${notice.title}</a></td>
			</tr>
		</c:forEach>
	</table>
	<p>
	
	
	<c:choose>
		<c:when test="${만약 관리자라면}">
			<a href="Notice.do?action=INPUT">공지사항 등록</a>
			<a href="또 어딘가로 가자!! 어디로??">어디로 갈까??</a>
			<p />
		</c:when>
		
		<c:when test="${만약 회원이라면}">
			<a href="Notice.do?action=LIST">공지목록</a>
			<a href="또 어딘가로 가자!! 어디로??">어디로 갈까??</a>
			<p />
		</c:when>
	</c:choose>
	
	
</body>
</html>