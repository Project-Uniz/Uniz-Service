<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<fmt:formatDate var="user_createDateTime" value="${user.createDateTime}" pattern="yyyy-MM-dd"/>
<fmt:formatDate var="user_updateDateTime" value="${user.updateDateTime}" pattern="yyyy-MM-dd"/>
<h1> 내 프로필 </h1>
 
 <p>유저아이디. </p>
<input type="text" value="${user.userId}" readonly="readonly">
<p>닉네임</p>
<input type="text" value="${user.nick}" readonly="readonly">

<p>내 유니즈 키워드</p>
<c:forEach items="${myUnizPoint}" var="myUnizPoint">
<p>${myUnizPoint.unizKeyword}/${myUnizPoint.point}</p>
			
</c:forEach>
 
<p>가입일 : ${user_createDateTime}</p>
<p>수정일 : ${user_updateDateTime}</p>


<button data-oper='modify' onclick="location.href='/user/modify'" >Modify</button>
<button data-oper='remove' id="removeBtn">탈퇴하기 </button>
<!-- <button data-oper='remove' onclick="location.href='/user/delete'">탈퇴하기 </button> -->
<%@ include file="/WEB-INF/views/includes/script.jsp"%>
<script>
	$(document).ready(function(){
		$("#removeBtn").click(function(){
			if(confirm("정말로 탈퇴하시겠습니까??") == true){
				location.href='/user/delete';
			}
		});
	});
</script>
</body>
</html>