<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>공지사항입력</h1><p>

	<form action="Notice.do?action=SAVE" method="post">
		<table>
			<tr>
				<th> 제 목 </th>
				<td><input type="text" name="title"/></td>
			</tr>
			<tr>
				<th> 내 용 </th>
				<td><textarea name="cont" cols="50" rows="5"> </textarea></td>
			</tr>
		
			<tr>
				<td colspan="2">
					<input type="submit" value="등록"/>
					<input type="reset" value="취소"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>