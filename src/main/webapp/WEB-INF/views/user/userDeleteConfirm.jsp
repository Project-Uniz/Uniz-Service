<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
탈퇴하기 전에 개인정보를 입력해주세요 


<form action="" method="post">
유저아이디 : <input type="text" name="userId" id="userId"><br>
패스워드 : <input type="password" name="password" id="password"><br>
bye bye

<button type="submit" >확인버튼 </button><br>
</form>


<button data-oper='remove' onclick="location.href='/user/userInfoRead'" > 탈퇴  취소하기 .</button>


</body>
</html>