
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" import="java.util.*" %>

	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</head>
<body>
<b>${msg}</b>
<b>${Nickmsg}</b>

<div><p>어서 가입하세요!!!!</p></div>
	<form action="/user/register" method="post"
		onsubmit="">
		nick<input type="text" id="nick" name="nick">
		<span id="nickMsg"></span> 
		<span id="nickMsg2"></span>
		<span id="nickMsg3"></span>
		
		<a href="#" id="nickBtn">닉네임 중복버튼.</a>
		
		<br>  userId<input type="text"
			 id="userId" name="userId">
		<a href="#" id="userIdBtn">아이디 중복버튼.</a>
		<br>
		 <span id="userIdMsg"></span>
		 <span id="userIdMsg2"></span>
		 <span id="userIdMsg3"></span>
		  
		password<input type="password" id="password" name="password"><br>
		<span id="pwdMsg"></span> 
		<span id="pwdMsg2"></span> 
		<span id="pwdMsg3"></span> 
		비밀번호 확인.<input type="password" id="password2" name="password2"><br>
		<span id="pwdMsgChk"></span> 
		
		 userType<input type="text" id="userType" name="userType" value="1"><br>
		imgUrl<input type="text" placeholder="imgUrl" id="imgUrl"
			name="imgUrl" value="jin.jpg"><br> state<input
			type="text" placeholder="state" id="state" name="state" value="1"><br>

		<button class="btn" type="submit" onclick="return chkPW();">register</button>
	</form>
	</div>
	
	<script type="text/javascript">
 
	 $("#nickBtn").click(function(){
		 var nick = $("#nick").val();
		 if(nick==""){
			 $("#nickMsg").text("닉네임을 입력해주쇼");
			 $("#nick").focus();
/*			 return;  */
		 }else{
			 $("#nickMsg").text("");
		 }
	 });
	 $("#userIdBtn").click(function(){
		 var nick = $("#userId").val();
		 if(nick==""){
			 $("#userIdMsg").text("아이디를 입력해주쇼");
			 $("#userId").focus();
			 return;
		 }else{
			 $("#userIdMsg").text("");
		 }
	 });
	 /* $(function(){
		
		 $('#userIdBtn').click(function(){
			 $.ajax({
				 type: "GET",
				 url : "nickCheck",
				 data:{
					 "nick":$("#nick").val()
				 },
				 success:function(data){
					 if($.trim(data)=="YES"){
			               if($('#nick').val()!=''){ 
			               	alert("사용가능한 아이디입니다.");
			               }
			           	}else{
			               if($('#nick').val()!=''){
			                  alert("중복된 아이디입니다.");
			                  $('#nick').val('');
			                  $('#nick').focus();
			               }
			            }
			         }
			 })
		 })
	 }); */
</script>



</body>
</html>