<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<fmt:formatDate var="user_createDateTime" value="${user.createDateTime}" pattern="yyyy-MM-dd"/>
<h1> 내 프로필 </h1>
 
 <p>유저아이디. </p>
<input type="text" value="${user.userId}" readonly="readonly">
<p>닉네임</p>
<input type="text" value="${user.nick}" readonly="readonly">

<p>내 유니즈 키워드</p>
<c:forEach items="${user.myUnizPoint}" var="myUnizPoint">
<p>${myUnizPoint.unizSN}/${myUnizPoint.point}</p>
			
</c:forEach>
 
<p>가입일</p>

<input type="text" value="${user_createDateTime}" readonly="readonly"><br>

<button data-oper='modify' onclick="location.href='/user/modify'" >Modify</button>
<button data-oper='remove' onclick="location.href='/user/userDeleteConfirm'">탈퇴하기 </button>

</body>
</html>