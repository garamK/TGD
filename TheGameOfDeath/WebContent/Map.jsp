<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function check(){
		
		var cur = document.getElementById("location");
		var next = document.getElementById("nextLocation");
		
		if(next.value == 0){
			alert("이동할 위치를 선택해주세요");
			next.focus();
			return false;
		}
		if(cur.value == next.value){
			alert("현재 위치입니다.");
			next.focus();
			return false;
		}
		
		if(cur.value == 1){
			if(next.value == 2 || next.value == 5){
				return true;
			}
			else{
				alert("현재 해당 위치로는 이동할 수 없습니다.");
				next.focus();
				return false;
			}
		}
		
		if(cur.value == 2){
			if(next.value == 1 || next.value == 3 || next.value == 6){
				return true;
			}
			else{
				alert("현재 해당 위치로는 이동할 수 없습니다.");
				next.focus();
				return false;
			}
		}
		
		if(cur.value == 3){
			if(next.value == 2 || next.value == 4 || next.value == 7){
				return true;
			}
			else{
				alert("현재 해당 위치로는 이동할 수 없습니다.");
				next.focus();
				return false;
			}
		}
		
		if(cur.value == 4){
			if(next.value == 3 || next.value == 8){
				return true;
			}
			else{
				alert("현재 해당 위치로는 이동할 수 없습니다.");
				next.focus();
				return false;
			}
		}
		
		if(cur.value == 5){
			if(next.value == 1 || next.value == 6 || next.value == 9){
				return true;
			}
			else{
				alert("현재 해당 위치로는 이동할 수 없습니다.");
				next.focus();
				return false;
			}
		}
		
		if(cur.value == 6){
			if(next.value == 2 || next.value == 5 || next.value == 7 || next.value == 10){
				return true;
			}
			else{
				alert("현재 해당 위치로는 이동할 수 없습니다.");
				next.focus();
				return false;
			}
		}
		
		if(cur.value == 7){
			if(next.value == 3 || next.value == 6 || next.value == 8 || next.value == 11){
				return true;
			}
			else{
				alert("현재 해당 위치로는 이동할 수 없습니다.");
				next.focus();
				return false;
			}
		}
		
		if(cur.value == 8){
			if(next.value == 4 || next.value == 7 || next.value == 12){
				return true;
			}
			else{
				alert("현재 해당 위치로는 이동할 수 없습니다.");
				next.focus();
				return false;
			}
		}
		
		if(cur.value == 9){
			if(next.value == 5 || next.value == 10 || next.value == 13){
				return true;
			}
			else{
				alert("현재 해당 위치로는 이동할 수 없습니다.");
				next.focus();
				return false;
			}
		}
		
		if(cur.value == 10){
			if(next.value == 6 || next.value == 9 || next.value == 11 || next.value == 14){
				return true;
			}
			else{
				alert("현재 해당 위치로는 이동할 수 없습니다.");
				next.focus();
				return false;
			}
		}
		
		if(cur.value == 11){
			if(next.value == 7 || next.value == 10 || next.value == 12 || next.value == 15){
				return true;
			}
			else{
				alert("현재 해당 위치로는 이동할 수 없습니다.");
				next.focus();
				return false;
			}
		}
		
		if(cur.value == 12){
			if(next.value == 8 || next.value == 11 || next.value == 16){
				return true;
			}
			else{
				alert("현재 해당 위치로는 이동할 수 없습니다.");
				next.focus();
				return false;
			}
		}
		
		if(cur.value == 13){
			if(next.value == 9 || next.value == 14){
				return true;
			}
			else{
				alert("현재 해당 위치로는 이동할 수 없습니다.");
				next.focus();
				return false;
			}
		}
		
		if(cur.value == 14){
			if(next.value == 10 || next.value == 13 || next.value == 15){
				return true;
			}
			else{
				alert("현재 해당 위치로는 이동할 수 없습니다.");
				next.focus();
				return false;
			}
		}
		
		if(cur.value == 15){
			if(next.value == 11 || next.value == 14 || next.value == 16){
				return true;
			}
			else{
				alert("현재 해당 위치로는 이동할 수 없습니다.");
				next.focus();
				return false;
			}
		}
		
		if(cur.value == 16){
			if(next.value == 12 || next.value == 15){
				return true;
			}
			else{
				alert("현재 해당 위치로는 이동할 수 없습니다.");
				next.focus();
				return false;
			}
		}
	}

</script>
</head>
<body>
<center>
<form action="Map.do" onsubmit="return check()" method="post">
	<input type="hidden" name="location" id="location" value="${sessionScope.location}"/>
	
	<table>
		<tr>
			<td>
				현재 위치 : ${sessionScope.location} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td>
				<select name="nextLocation" id="nextLocation">
					<option value="0">이동할 위치를 선택하세요</option>	
					<option value="1">1</option>	
					<option value="2">2</option>	
					<option value="3">3</option>	
					<option value="4">4</option>	
					<option value="5">5</option>	
					<option value="6">6</option>	
					<option value="7">7</option>	
					<option value="8">8</option>	
					<option value="9">9</option>	
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
					<option value="13">13</option>
					<option value="14">14</option>
					<option value="15">15</option>
					<option value="16">16</option>
				</select>
				<input type="submit" value="이동"/>
			</td>
		</tr>
	</table>
	
</form>
 <br>
<img width="550px" src="images/map_numbered.png"> <p>
</center>
</body>
</html>