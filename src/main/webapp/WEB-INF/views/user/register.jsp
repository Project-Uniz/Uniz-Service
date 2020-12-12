<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</head>
<body>

<h1>회원가입 페이지</h1>
	<form action="/user/register" method="post">
		userId<input type="text" id="userId" name="userId"> <input type="button" id="userIdCheckBtn">중복체크 <br>
		password<input type="password" id="password" name="password"><br>
		비밀번호 확인.<input type="password" id="password2" name="password2"><br>		 
		nick<input type="text" id="nick" name="nick"><input type="button" id="userNickCheckBtn">중복체크 <br>
		
		<c:forEach items="${PresetList}" var="preset">
			<input type="checkbox" name="unizSN" value="${preset.unizSN}">${preset.unizKeyword}<br>
		</c:forEach>
		
		<input type ="hidden" name="state" value="1">
		<button class="btn" type="submit">register</button>
	</form>
	
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	
	<script>
 	
	$(document).ready(function(){
		
		$('#userIdCheckBtn').click(function(){
			
			if($("#userId").val()==""){
			alert("중복체크할 아이디를 입력해 주세요");
			return ;
			}
		
		$.ajax({
			type : "POST",
			url : "/user/userIdCheck",
			data : $("#userId").val(),		
			contentType : "application/json; charset=UTF-8",
			dataType: "json",
			success : function(data){
				
				const SUCCESS = "SUCCESS";
				const DUPLICATION = "DUPLICATION";
				
				if(data.data == SUCCESS){
					alert("사용할 수 있는 아이디 입니다.")
					$("#userId").attr("readonly", "true");
					$("#userIdCheckBtn").attr("disabled", "true");
				}else if(data.data == DUPLICATION){
					alert("이미 존재하는 아이디 입니다.")	
				}else{
					alert("데이터 입력 중 오류가 발생하였습니다.\n입력한 정보를 다시 확인해 주세요.");
				}			
			}
		});
	});
	
	$('#userNickCheckBtn').click(function(){
		
		if($("#nick").val()==""){
			alert("중복체크할 닉네임를 입력해 주세요");
			return ;
		}
		console.log("중복확인버튼클릭");

		$.ajax({
			type : "POST",
			url : "/user/userNickCheck",
			data : $("#nick").val(),		
			contentType : "application/json; charset=UTF-8",
			dataType: "json",
			success : function(data){
				
				const SUCCESS = "SUCCESS";
				const DUPLICATION = "DUPLICATION";
				if(data.data == SUCCESS){
					alert("사용할 수 있는 닉네임 입니다.")
				}else if(data.data == DUPLICATION){
					alert("이미 존재하는 닉네임 입니다.")	
				}else{
					alert("데이터 입력 중 오류가 발생하였습니다.입력한 정보를 다시 확인해 주세요.");
				}			
			}
		});			
	});
});
</script>

</body>
</html>