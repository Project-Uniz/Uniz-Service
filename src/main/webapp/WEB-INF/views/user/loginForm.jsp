


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
private String getIdFromCookie(HttpServletRequest request){
	//쿠키 이름이 id라면 값을 받아서 저장한다.
	String userId = "";
	Cookie[] cookies = request.getCookies();
	for (int i = 0; i < cookies.length; i++) {
	if (cookies[i].getName().equals("userId")) {
	userId += cookies[i].getValue();
	}   }
	return userId;
	}//end method
%>
<%
String userId = getIdFromCookie(request);//"asdf"를 반환해서 id에 저장.  
String checked = "";
if(userId!=null && ! userId.equals("")){ //아이디가 null이나 빈 문자열이 아니라면
checked= "checked"; //이게 체크박스에서 value="checked"를 의미.
} 
%>
<% 
request.setCharacterEncoding("utf-8"); //한글깨짐 방지 사전호출. 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${msg}</h1>
		<p><b>여기는 로그인 페이지 입니다.</b></p>
		<form action="/user/loginForm" method="post" onsubmit="return checkForm();">
<!-- 			<input type="text" placeholder="nick" id="nick" name="nick" ><br>

 -->			
 			<input type="text" placeholder="userId" id="userId" name="userId" value="<%=userId%>" ><br> 
			<input type="password" placeholder="password" id="password" name="password" ><br> 
			<input type="checkbox" <%=checked%> id="chk" name="chk"> 
			<label for="box">remember Id</label><br> 
			<button type="submit">log in</button>
		</form>
			<a href="/user/register">회원가입하러가기. </a><br>
</body>
</html>
 <!--  <script>
			function checkForm() {
				//아이디에 대한 참조를 얻어온다. 
				let id = document.getElementById("userId");
				let pwd = document.getElementById("password");

				//value가 ""빈 문자열이면 다음 페이지로 넘어가지 않는다. 
				if (id.value == "") {
					alert("아이디를 입력해주세요. ");
					return false;
				}
				if (pwd.value == "") {
					alert("비밀번호를 입력해주세요.");
					return false;
				}
				return true;
			}
		</script>   -->
</body>
</html>
