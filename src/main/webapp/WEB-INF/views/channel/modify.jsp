<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


게시글 수정 페이지
<h1>수정 할 게시글 번호 = <c:out value="${postSN}"/></h1>
<h2>dddd<c:out value="${channelSN}"/></h2>
<form action='/channel/modify' method='post' id='boardPost'>

</form>
<button type='submit'>게시글 수정</button>
<button type='submit' data-oper='remove'>게시글 삭제</button>
<button type='submit' data-oper='list'>글 목록으로</button>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/channel.js"></script>	
<script type="text/javascript">

$(document).ready(function(){
	var postSN = '<c:out value="${postSN}"/>';
	var channelSN = '<c:out value="${channelSN}"/>';
	channelService.modify({postSN : postSN , channelSN : channelSN});
	
	var formObj = $("form");
	$('button').on("click", function(e){
		e.preventDefault();
		var operation = $(this).data("oper");
		console.log("oper : " + operation);
		
		if(operation === 'remove'){
			formObj.attr("action" , "/channel/remove");
		}else if (operation === 'list'){
			self.location="/channel/board/" + <c:out value="${channelSN}"/>;
			return;
		}
		formObj.submit();
	});

});

</script>

</body>
</html>