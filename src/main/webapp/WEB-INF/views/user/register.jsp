
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<b>${msg}</b>
<div><p>어서 가입하세요!!!!</p></div>
			<form action="/user/register" method="post" onsubmit="return checkForm();">
				nick<input type="text" placeholder="nick" id="nick" name="nick" ><br> 
				userId<input type="text" placeholder="userId" id="userId" name="userId" ><br>
				password<input type="password" placeholder="password" id="password"
					name="password"  ><br>
				userType<input type="text" placeholder="userType" id="userType" name="userType" value="1"><br> 
				imgUrl<input type="text" placeholder="imgUrl" id="imgUrl" name="imgUrl" value="jin.jpg"><br> 
				state<input type="text" placeholder="state" id="state" name="state" value="1"><br> 
				<!-- <input type="checkbox" value="1001">스포츠 <br>
				<input type="checkbox" value="1002">영화  <br>
				<input type="checkbox" value="1003">드라마   <br>
				<input type="checkbox" value="1004">음악   <br>
				<input type="checkbox" value="1005">키즈   <br>
				<input type="checkbox" value="1006">교육   <br>
				<input type="checkbox" value="1007">코미디   <br>
				<input type="checkbox" value="1008">뉴스   <br>
				<input type="checkbox" value="1009">정치   <br>
				<input type="checkbox" value="1010">게임   <br>
				<input type="checkbox" value="1011">요리   <br>
				<input type="checkbox" value="1012">습관   <br>
				<input type="checkbox" value="1013">동물   <br>
				  -->
				<button class="btn" type="submit" onclick="return check();">register</button>
			</form>
</body>
</html>