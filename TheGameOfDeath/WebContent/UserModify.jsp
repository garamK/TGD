<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
<h1>회원 정보 수정 화면</h1>

<form action="User.do?action=UPDATESAVE" method="post">

<table border='1'>

	<tr>
		<th>회원번호</th>
		<td><input type="text" name="userNum" value='${user.userNum}' readonly="readonly"/> </td>
	</tr>
	<tr>
		<th>아이디</th>
		<td>${user.userId} </td>
	</tr>
	<tr>
		<th>닉네임</th>
		<td><textarea rows="1" cols="20" name="nick">${user.nick}</textarea></td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td><textarea rows="1" cols="20" name="pass">${user.pass}</textarea></td>
	</tr>
	
	<tr>
		<td colspan='2'>
			<input type = "submit" value = "수정" />	
			<input type = "reset" value = "지우기" />	
		</td>
	</tr>
</table>
</form>

<a href="User.do?action=UPDATE&userNum=${user.userNum}">정보수정</a>
<a href="User.do?action=DELETE&userNum=${user.userNum}">탈퇴하기</a>

<a href="GameMain.jsp">게임메인페이지</a>

</body>
</html>