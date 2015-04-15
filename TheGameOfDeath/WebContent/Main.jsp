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
	<table width="100%" border="1">

		<tr height="20%">
			<a href="Notice.do?action=LIST">공지사항리스트 </a>
			<a href="User.do?action=LOGOUT">로그아웃</a>
		</tr>

		<tr height="80%">
			<td align="center"><jsp:include page="GameMain.jsp" /></td>
		</tr>

	</table>
</body>
</html>