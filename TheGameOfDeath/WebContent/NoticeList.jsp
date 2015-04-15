<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

function popUp(num){
	window.open("Notice.do?action=VIEW&noticeNum="+num, "공지상세보기", "top=200, left=200, width=600, height=600, scrollbar=yes");
}

</script>


</head>

<body>
	<h1>공지사항</h1>
	<table border='1'>
		<tr>
			<th width="30%">번호</th><th width="30%">제 목</th><th width="100%">내 용</th><th width="100%">날 짜</th>
		</tr>

		<c:forEach var="nn" items="${list}">
			<tr>
				<td>${nn.noticeNum}</td>
				
				<c:if test="${sessionScope.userId eq 'Admin'}">
				<td><a href="Notice.do?action=VIEW&noticeNum=${nn.noticeNum}">${nn.title}</a></td>
				<td><a href="Notice.do?action=UPDATE&noticeNum=${nn.noticeNum}">${nn.content}</a></td>
				<td>${nn.ndate}</td>
				</c:if>
				
				<c:if test="${sessionScope.userId eq! 'Admin'}">
				<td> <a href="javascript:popUp('${nn.noticeNum}')">${nn.title}</a></td>
				<td>${nn.content}</td>
				<td>${nn.ndate}</td>
				</c:if>
				
			</tr>
		</c:forEach>
	</table>
			<a href="Notice.do?action=INPUT">공지사항 등록</a>
			<a href="GameMain.jsp">게임메인페이지</a>
	
	
	
</body>
</html>