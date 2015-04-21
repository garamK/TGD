<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>The Game of Death</title>
</head>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="http://bootswatch.com/assets/css/bootswatch.min.css">
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript">

	function getTime(){
		
		var next = document.getElementById("ftime").value;
				
		var cur = new Date().getTime();
		
		var gap = (next - cur)/1000;
		
		var min = Math.floor(gap/60);
		var sec = Math.floor(gap%60);
		
		var sm = document.getElementById("sm");
		var ss = document.getElementById("ss");
		
		if(min <= 0 && sec <= 0){
			sm.innerHTML = ""
			ss.innerHTML = "충전완료";
		}
		else{
			sm.innerHTML = min + "분 ";
			ss.innerHTML = sec + "초";
		}
		
	}
	
	function startTimer() { 
		if (document.getElementById("ftime")!=null) {
			timerID = setInterval("getTime()", 1000);
		}
	}
	
	window.onload = startTimer;
	
</script>
<body>
<input type="hidden" id="ftime" value="${time}"/>

<c:if test="${empty nextPage}">
<%
	request.getRequestDispatcher("Notice.do").forward(request, response);
%>
</c:if>
<c:if test="${empty sessionScope.userId}">
<%
	request.setAttribute("msg", "세션이 만료되었습니다. 다시 로그인해주세요");
	request.getRequestDispatcher("Error.jsp").forward(request, response);
%>
</c:if>

<center>
	<table width="75%" height="100%">

		<tr height="50px">
			<td>
				<nav class="navbar navbar-inverse">
				  <div class="container-fluid">
				    <div class="navbar-header">
				      <a class="navbar-brand" href="Game.do">TheGameOfDeath</a>
				    </div>
				
				    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				      <ul class="nav navbar-nav">
				        <li><a href="Notice.do?action=LIST">공지사항</a></li>
				        <li><a href="Map.do">맵 정보</a></li>
				        <li><a href="Survivor.do">랭킹</a></li>
				        <li><a href="GameManual.html">게임설명</a></li>
				      </ul>
				      <ul class="nav navbar-nav navbar-right">
				        <li><a href="User.do?action=LOGOUT">LogOut</a></li>
				      </ul>
				    </div>
				  </div>
				</nav>	
				
			</td>
		</tr>
		<tr>
			<td>
				<c:if test="${not empty number1}">
						<table align="left">
							<tr><td>[알림] ${number1.nick} 유저는 현재 ${number1.location}구역에 있습니다. </td></tr>
						</table>
				</c:if>
				<table align="right">
					<tr><th width='80px'>행동력 </th><td align="center" width='100px'> ${sessionScope.userInfo.stamina}</td></tr>
					<tr><th>남은 시간</th><td align="center"><span id="sm"></span><span id="ss"></span></td></tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="center"><jsp:include page="${nextPage}" /></td>
		</tr>
	</table>
	
</center>

</body>
</html>
