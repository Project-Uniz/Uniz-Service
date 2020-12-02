<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<title>Insert title here</title>
</head>
<body>	

		<p><b>여기는 로그인 페이지 입니다.</b></p>
		<form action="/user/loginForm" method="post" onsubmit="return checkForm();">		
 			<input type="text" placeholder="userId" id="userId" name="userId" ><br> 
			<input type="password" placeholder="password" id="password" name="password" ><br> 
			<input type="checkbox"  id="chk" name="chk"> 
			<label for="box">remember Id</label><br> 
			<button type="submit">log in</button>
		</form>
			<a href="/user/register">회원가입하러가기. </a><br>
	</body>
</html>