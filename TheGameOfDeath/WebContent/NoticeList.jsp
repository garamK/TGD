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
	<h4>공지사항</h4>
	<c:if test="${sessionScope.userId eq 'Admin'}">
		<table border='1' class="table">
			<tr>
				<th width="30%">번호</th>
				<th width="30%">제 목</th>
				<th width="100%">내 용</th>
				<th width="100%">날 짜</th>
			</tr>

			<c:forEach var="nn" items="${list}">
				<tr>
					<td>${nn.noticeNum}</td>

					<td><a href="Notice.do?action=VIEW&noticeNum=${nn.noticeNum}">${nn.title}</a></td>
					<td><a
						href="Notice.do?action=UPDATE&noticeNum=${nn.noticeNum}">${nn.content}</a></td>
					<td>${nn.ndate}</td>



				</tr>
			</c:forEach>
		</table>
		<a href="Notice.do?action=INPUT">공지사항 등록</a>
	</c:if>



	<c:if test="${sessionScope.userId ne 'Admin'}">
		<table border='1' class="table">
			<tr>
				<th width="30%">번호</th>
				<th width="30%">제 목</th>
				<th width="100%">내 용</th>
				<th width="100%">날 짜</th>
			</tr>

			<c:forEach var="nn" items="${list}">
				<tr>
					<td>${nn.noticeNum}</td>

					<td>${nn.title}</td>
					<td>${nn.content}</td>
					<td>${nn.ndate}</td>
				</tr>
			</c:forEach>
		</table>

	</c:if>

</body>
</html>