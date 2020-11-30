<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 


<h1> 내 프로필 수정하는 중 건들지 마셈.  </h1>
 
<h1> 내 프로필 </h1>
<form  action="/user/modify" method="post">
 
 
 <p>닉네임</p>
<input type="text" value='<c:out value="${userDTO.nick}"  />' >
 <p>유저아이디. </p>
<input type="text" value='<c:out value="${userDTO.userId}"  />'  readonly="readonly">
 <p>패스워드. </p>
<input type="text" value='<c:out value="${userDTO.password}"  />' ><br>

<button data-oper='modify' onclick="location.href='/user/register'" >확인버튼 </button>
<button data-oper='remove' onclick="location.href='/user/read'" > 수정 취소하기 .</button>
 </form>

</body>
</html>