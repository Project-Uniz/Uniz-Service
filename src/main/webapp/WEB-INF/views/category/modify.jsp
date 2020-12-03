<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


게시글 수정 페이지


<form action='/category/modify' method='post' id='boardPost'>

</form>
<button type='submit'>게시글 수정</button>
<button type='submit' data-oper='remove'>게시글 삭제</button>
<button type='submit' data-oper='list'>글 목록으로</button>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/category.js"></script>	
<script type="text/javascript">

$(document).ready(function(){
	var postSN = '<c:out value="${postSN}"/>';
	var boardSN = '<c:out value="${boardSN}"/>';
	categoryService.modify({postSN : postSN , boardSN : boardSN});
	
	
	var formObj = $("form");
	$('button').on("click", function(e){
		e.preventDefault();
		var operation = $(this).data("oper");
		console.log("oper : " + operation);
		
		if(operation === 'remove'){
			
			if(confirm("정말 삭제 하시겠습니까?")){
			formObj.attr("action" , "/category/remove");
			}
			
			
		}else if (operation === 'list'){
			self.location="/category/board/" + boardSN;
			return;
		}
		formObj.submit();
	});

});

</script>

</body>
</html>