<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  


    





<h1> 내 프로필 </h1>
 
 <p>닉네임</p>
<input type="text" value='<c:out value="${userDTO.nick}"  />' readonly="readonly">
 <p>유저아이디. </p>
<input type="text" value='<c:out value="${userDTO.userId}"  />' readonly="readonly">
 <p>패스워드. </p>
<input type="text" value='<c:out value="${userDTO.password}"  />' readonly="readonly">
<br>

<button data-oper='modify' onclick="location.href='/user/modify'" >Modify</button>
<button data-oper='remove' onclick="location.href='/user/userDeleteConfirm'">탈퇴하기 </button>

</body>
</html>