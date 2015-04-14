<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
function check(){
	location.href="RegisterInput.jsp";
	
}
</script>
</head>

<body>
			<center>
			<h1>죽음의 세계로 당신을 초대합니다.</h1>
			<p>
			<form action="User.do?action=LOGIN" method="post">
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
					<input type="button" value="회원가입" onclick="check()"/>
					</td>
				</tr>
			</table>
			</form>
			<p />
			</center>
</html>