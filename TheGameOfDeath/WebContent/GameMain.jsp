<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	function iSubmit() {

		iForm.submit();
	}

	function dSubmit() {

		dForm.submit();
	}
</script>

</head>
<body>

	<table align=center bgcolor=silver>

		<tr>
			<td align=center width="500" height="500">
				<textarea rows="20"	cols="50" readonly="readonly" class="form-control">
<c:if test="${not empty msg}">${msg}</c:if>
<c:if test="${not empty eventList}"> 
<c:forEach var="ev" items="${eventList}">
 ${ev.msg}
</c:forEach>
</c:if>
				</textarea>
			</td>
			<td width="50">
			
			</td>
			<td align=center>
				<table align=center>
					<tr>
						<td align=center colspan="2" width="250">유저 정보</td>
					</tr>
					<tr>
						<td align=center colspan="2"><img
							src="images/${userInfo.image}" /></td>
					</tr>
					<tr>
						<td><br/></td>
					</tr>

					<tr align=center>
						<th align=center>닉네임</th>
						<td align=center>${userInfo.nick}</td>
					</tr>
					<tr>
						<th align=center>체력</th>
						<td align=center>${userInfo.health}/${userInfo.maxHealth}</td>
					</tr>
					<tr>
						<th align=center>공격력</th>
						<td align=center>${userInfo.power}</td>
					</tr>
					<tr>
						<th align=center>살해수</th>
						<td align=center>${userInfo.kill}</td>
					</tr>
					<tr>
						<th align=center>사망수</th>
						<td align=center>${userInfo.death}</td>
					</tr>
					<tr>
						<th align=center>위치</th>
						<td align=center>${userInfo.location}</td>
					</tr>
					<tr>
						<th align=center>장비</th>
						<td align=center>${weapon}</td>
					</tr>
					<tr>
						<td><br /></td>
					</tr>
					<tr>
						<td><br /></td>
					</tr>
					<tr>
						<td colspan="2">
							<a href="Game.do?action=explore" class="btn btn-default">탐색</a> 
							<a href="javascript:iSubmit()"	class="btn btn-default">아이템사용</a> 
							<a href="javascript:dSubmit()"	class="btn btn-default">행동지침</a></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr align="center">
			<td>
				<form action="Game.do?action=decision" method="post" id="dForm">
					행동지침&nbsp;&nbsp;&nbsp; 
					<input type="radio" name="decision" value="1" /> 전투&nbsp;&nbsp; 
					<input type="radio" name="decision"value="0" /> 회피&nbsp;
				</form>
			</td>
			<td colspan="2">
				<form action="Game.do?action=itemUse" method="post" id="iForm">
					<table align="center">
						<tr>
							<th width="70">선택</th>
							<th width="70">아이템</th>
							<th width="70">수량</th>
							<th width="70">체력</th>
						</tr>

						<c:forEach var="i" items="${itemList}">

							<tr>
								<td><input type="radio" name="itemNum" value="${i.itemNum}" /></td>
								<td>${i.itemName}</td>
								<td>${i.quantity}</td>
								<td>${i.stat}</td>
							</tr>
						</c:forEach>
						<c:if test="${empty itemList}">
							<tr>
								<td colspan="4" align="center">아이템이 없습니다!</td>
							</tr>
						</c:if>
					</table>
				</form>
			</td>
			
		</tr>
		<tr><td colspan="2"><br><br></td></tr>
	</table>


</body>
</html>
