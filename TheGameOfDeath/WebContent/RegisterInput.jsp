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

<script type="text/javascript">

function registerCheck(){
	
	userId=document.getElementById("userId");
	if(userId.value.length<=0){
		alert("입력하세요");
		userId.focus();
		return;
	}
	
	nick=document.getElementById("nick");
	if(nick.value.length<=0){
		alert("입력하세요.");
		nick.focus();
		return;
	}
	
	pass=document.getElementById("pass");
	if(pass.value.length<=0){
		alert("입력하세요");
		pass.focus();
		return;
	}	
		
	pass2=document.getElementById("pass2");
	if(pass2.value!=pass.value){
		alert("비밀번호를 일치시켜주세요");
		pass2.focus();
		return;	
	}	

	if(userId.value.length > 0 && nick.value.length > 0 && pass.value.length > 0 && pass2.value ==pass.value){
		document.getElementById("ff").submit();
	}
	
}

</script>

</head>
<body>
	<div style="margin-top: 5%; margin-left: 5%; margin-right: 5%" class="jumbotron">
	<h2 align=center>회원가입</h2>
	<br><br>
	<form action="User.do?action=REGISTER" method="post" id="ff" name="ff">

		<table align="center" border="1">
		<tr>
			<td>
				<table align="center"  border='1'>
					<tr>
						<th>아이디</th>
						<td><input type="text" name="userId" id="userId"/></td>
					</tr>
					<tr>
						<th>닉네임</th>
						<td><input type="text" name="nick" id="nick" /></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="pass" id="pass" /></td>
					</tr>
					<tr>
						<th>비밀번호 확인</th>
						<td><input type="password" name="pass2" id="pass2" /></td>
					</tr>
				</table>
			</td>
			<td width="600" aligh="right">
				<div style="margin-left: 20%;"><h2>The Game of Death</h2></div>
			</td>
		</tr>
		</table>
			
		<table align=center border=1 >
			<tr><th colspan=6 align=center><center>캐릭터를 선택해주세요</center></th></tr>
			<tr>
				<th><img src="images/ch1.png" /></th>
				<th><img src="images/ch2.png" /></th>
				<th><img src="images/ch3.png" /></th>
				<th><img src="images/ch4.png" /></th>
				<th><img src="images/ch5.png" /></th>
				<th><img src="images/ch6.png" /></th>
			</tr>
			<tr>
				 <td><input type="radio" name="image" value="ch1.png" />캐릭터1</td>
				 <td><input	type="radio" name="image" value="ch2.png" />캐릭터2</td> 
				 <td><input	type="radio" name="image" value="ch3.png" />캐릭터3</td>
				 <td><input	type="radio" name="image" value="ch4.png" />캐릭터4</td>
				 <td><input type="radio" name="image" value="ch5.png" />캐릭터5</td>
				 <td><input type="radio" name="image" value="ch6.png" />캐릭터6</td>
			</tr>
			<tr align="center">
				<td colspan="6">
					<input type="button" value="가입" onclick="registerCheck()" style="background-color:black; border:0; color:white" /> 
					<input type="reset" value="취소" style="background-color:black; border:0; color:white"/>
				</td>
			</tr>
		</table>
		<br>
		<br>
	</form>
</div>	
</body>
</html>