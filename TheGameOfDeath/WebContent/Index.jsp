<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	function check() {
		location.href = "RegisterInput.jsp";

	}

	function popUp(num) {
		window.open("Notice.do?action=VIEW&noticeNum=" + num, "공지상세보기",
				"top=200, left=200, width=600, height=600, scrollbar=yes");
	}
</script>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="http://bootswatch.com/assets/css/bootswatch.min.css">
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>

<body>
<div style="margin-top: 10%; margin-left: 15%; margin-right: 15%" class="jumbotron">
<div style="margin-left: 5%;"><h2>The Game of Death</h2></div>
<br><br><br>
<center>
<table>
	<tr>
		<td width="350px">
			<table align="center" class="table" width="300px">
				<tr>
					<td colspan="3" align="center"> 공지사항 </td>
				</tr>
				<tr>
					<th>번호</th>
					<th>제 목</th>
					<th>날 짜</th>
				</tr>
			
				<c:forEach var="nn" items="${list}">
					<tr>
						<td>${nn.noticeNum}</td>
						<td><a href="javascript:popUp('${nn.noticeNum}')">${nn.title}</a></td>
						<td>${nn.ndate}</td>
					</tr>
				</c:forEach>
			</table>
		</td>
		<td width="100px">
		</td>
		<td>
			<form action="User.do?action=LOGIN" method="post">
				<table class="table">
					<tr>
						<th >아이디</th>
						<td><input type="text" id="userId" name="userId" /></td>
						<td><input type="submit" value="로그인" /></td>
					</tr>
					<tr>
						<th >비밀번호</th>
						<td><input type="password" id="pass" name="pass" /></td>
						<td><input type="button" value="회원가입" onclick="check()" /></td>
					</tr>
				</table>
			</form>
		</td>
	</tr>

</table>
</center>

</div>
	
</html>