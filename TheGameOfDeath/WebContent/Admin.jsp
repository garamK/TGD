<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="http://bootswatch.com/assets/css/bootswatch.min.css">
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<center>
<h1>관리자 페이지</h1>
<a href="Notice.do?action=LIST"><font size="6">공지목록</font></a>
<a href="Notice.do?action=INPUT"><font size="6">공지등록</font></a>
<p>
<a href="Admin.do?action=START"><font size="6">시즌시작</font></a>
<a href="Admin.do?action=END"><font size="6">시즌종료</font></a>
<p>
<a href="UserList.do?action=LIST"><font size="6">회원리스트</font></a>
<p>
<a href="User.do?action=LOGOUT"><font size="6">로그아웃</font></a>
</center>
</body>
</html>