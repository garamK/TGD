<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
function check(){
	location.href="RegisterInput.jsp";
	
}

function popUp(num){
	window.open("Notice.do?action=VIEW&noticeNum="+num, "공지상세보기", "top=200, left=200, width=600, height=600, scrollbar=yes");
}

</script>
</head>

<body>

		<tr width="100%" height="45%"> <td>
		
		 <table align="center" border='1'>
		<tr>
			<th width="30%">번호</th><th width="30%">제 목</th><th width="100%">날 짜</th>
		</tr>

		<c:forEach var="nn" items="${list}">
			<tr>
				<td>${nn.noticeNum}</td>
				<td> <a href="javascript:popUp('${nn.noticeNum}')">${nn.title}</a></td>
				<td>${nn.ndate}</td>
			</tr>
		</c:forEach>
	</table>
		 
		 </td></tr>
			<center>
			<h1>죽음의 세계로 당신을 초대합니다.</h1>
			<p>
			<form action="User.do?action=LOGIN" method="post">
			<table>
				<tr>
					<th width="70" height="30">아이디</th>
					<td><input type="text" id="userId" name="userId" /></td>
				</tr>
				<tr>
					<th height="30">비밀번호</th>
					<td><input type="password" id="pass" name="pass" /></td>
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