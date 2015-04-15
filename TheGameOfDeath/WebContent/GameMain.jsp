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
<textarea rows="20" cols="50" readonly="readonly" >
<c:if test="${not empty msg}">${msg}</c:if>
<c:if test="${not empty eventList}">
<c:forEach var="ev" items="${eventList}">
${ev.msg}
</c:forEach>
</c:if>
</textarea>
<a href="Game.do">탐색</a>
<a href="Notice.do?action=LIST">공지사항리스트</a>
<a href="User.do?action=LOGOUT">로그아웃</a>

<p>
유저 정보 

</body>
</html>