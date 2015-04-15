<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
<h1>회원가입</h1>
<p>
	<form  enctype="multipart/form-data" action="User.do?action=REGISTER" method="post">
		<table>
			<tr>
				<th>이미지</th>
				<td><input type="file" style="width:100" name="Image" id="Image"/></td>
			</tr>
		
			<tr>
				<th>아이디</th>
				<td><input type="text" name="userId" id="userId"/></td>
			</tr>
			<tr>
				<th>닉네임</th>
				<td><input type="text" name="nick" id="nick"/></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="pass" id="pass"/></td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td><input type="password" name="pass2" id="pass2"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="가입"/>
					<input type="reset" value="취소"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>