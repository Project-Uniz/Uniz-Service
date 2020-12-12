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
	
	<%@ include file="/WEB-INF/views/includes/script.jsp"%>
	<script type="text/javascript">
	//테스트용 아이디 비밀번호 고정
		$("#userId").val("test0003");
		$("#password").val("1234");
		
		
	</script>
</html>