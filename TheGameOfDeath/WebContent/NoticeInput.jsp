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
<h1>공지사항입력</h1><p>

	<form action="Notice.do?action=SAVE" method="post">
		<table>
			<tr>
				<th> 제 목 </th>
				<td><input type="text" name="title" id="title"/></td>
			</tr>
			<tr>
				<th> 내 용 </th>
				<td><textarea name="content" id="content" cols="100" rows="10"> </textarea></td>
			</tr>
			<tr>
				<td colspan="2">
				<center>
					<input type="submit" value="등록" style="background-color:black; border:0; color:white"/>
					<input type="reset" value="취소" style="background-color:black; border:0; color:white"/>
					<p>
				</center>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>