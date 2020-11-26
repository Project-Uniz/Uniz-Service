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
	
	<c:if test="${userId.userSN == board.userSN}">
	<button id='modify'>글 수정</button>
	</c:if>
	<button id='list'>목록으로</button>
	<div></div>
	<div>
		
		<form role="form" action="/chreplies/add" method="post" onsubmit="return checkTitle()">
		
			<input value="댓글 내용" name="replyContent" id="replyContent">
			<input value="작성자 SN" name="userSN">
			<input value="${postSN}" name="postSN">
			<button type="submit" ></button>
		
		</form>
		<div></div>
		
		<table class="reply" border="1"  width ="300px" height="10px" >

			<th>작성자</th>
			<th>내용</th>
			<th>작성 시간</th>
			
			<tr class="reply">
				<td> test </td>
				<td> test reply </td>
				<td> 2020-11-26</td>
			</tr>
		</table>
	</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/channel.js"></script>	
<script type="text/javascript" src="/resources/js/chreply.js"></script>	
<script type="text/javascript">

$(document).ready(function(){
	
	
	var postSN = '<c:out value="${postSN}"/>';
	var reply = $(".reply");
	
	console.log("게시글 번호 : " + postSN);
	channelService.getPost({postSN:postSN});
	
		chReplyService.getList({postSN:postSN , page:1}, function(list){
			console.log(list);
			for(var i = 0, len = list.length||0; i < len; i++){
				console.log(list[i]);
			}
		});
	
});

function checkTitle(){
	
	var str = document.getElementById('replyContent');
	var blank_pattern = /^\s+|\s+$/g;
	
	if(str.value == '' || str.value == null || str.value.replace(blank_pattern, '').length == 0){
		alert("댓글 내용을 입력하세요");
		return false;
	}
	
}


</script>
</body>
</html>