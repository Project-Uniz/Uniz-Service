<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<fmt:formatDate var="user_createDateTime" value="${user.createDateTime}" pattern="yyyy-MM-dd"/>
<h1> 내 프로필 </h1>
 
<form action="/user/modify" method="post">

 <p>유저아이디. </p>
<input type="text" name="userId" value="${user.userId}" readonly="readonly">

<p>현재비밀번호</p>
<input type="password" name="c_password" id="c_password">
<p>비밀번호</p>
<input type="password" name="password" id="password">
<p>비밀번호</p>
<input type="password" id="password2">

<p>닉네임</p>
<input type="text" name="nick" value="${user.nick}" readonly="readonly">
			
<div>
	<button data-oper='modify' onclick="location.href='/user/modify'" >Modify</button>
</div>

<input type = "hidden" id="msg" value = "${MSG}"> 

</form>
<%@ include file="/WEB-INF/views/includes/script.jsp"%>
<script>
	$(document).ready(function(){
		let msg =  $("#msg").val();
		
		if(msg == "FAIL"){
			alert(msg);
		}
		
	});
</script>
<!-- 1. 현재페이지에서 수정할 수 있는 사항은 비밀번호 변경밖에 없음 -->
<!-- 2. 현재 비밀번호 필드에 입력한 값과 세션의 password와 비교하고 같으면 회원정보 수정 진행 -->

</body>
</html>