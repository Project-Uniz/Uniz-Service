<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 


<h1> 내 프로필 수정하는 중 건들지 마셈.  </h1>
 
<h1> 내 프로필 </h1>
<form  action="/user/modify" method="post">
 
 
 <p>닉네임</p>
<input type="text" value='<c:out value="${UserDTO.nick}"  />' >
 <p>유저아이디. </p>
<input type="text" value='<c:out value="${UserDTO.userId}"  />'  readonly="readonly">
 <p>패스워드. </p>
<input type="text" value='<c:out value="${UserDTO.password}"  />' ><br>

<a href="/user/modifySuccess">수정완료하고 페이지 이동.  </a>
<a href="/user/read">수정취소 하고 읽기페이지로 가기.  </a>
 </form>

</body>
</html>