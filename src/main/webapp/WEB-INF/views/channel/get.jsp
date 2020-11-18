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
	<h1>게시글 보여주는 페이지</h1>
	
	<div id="boardPost">
	</div>
	
	
	<button id='modify'>글 수정</button>
	<button id='list'>목록으로</button>
	

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/channel.js"></script>	
<script type="text/javascript">

$(document).ready(function(){

	
	var postSN = '<c:out value="${postSN}"/>';
	console.log("게시글 번호 : " + postSN);
	channelService.getPost({postSN:postSN});
	
	
});

</script>
</body>
</html>