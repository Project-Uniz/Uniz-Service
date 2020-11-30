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
	
	
	
	<div class="board"></div>
	<div></div>
	<h3>게시글 목록</h3>
	<div class="post">
	
	</div>
	
	<div>
		<c:if test="${sessionScope.userId !=null }">
		<button id="createBtn" type="button">게시글 작성</button>
		</c:if>
		<button id="listBtn" type="button">채널 게시판으로 이동</button>
	</div>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/channel.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	console.log("Channle Board");
	var channelSN = '<c:out value="${channelSN}"/>';
	console.log("-----------"+channelSN);
	var board = $(".board");
	var post = $(".post");
	
	channelService.getChannelPostList( {channelSN : channelSN} , function(list){
		
		var str = "";
		
		str += "<thead><tr><th>글 번호</th><th>글 제목</th><th>작성자</th><th>작성 일</th></tr></thead>"
		for ( var i = 0, len = list.length || 0; i < len; i ++){
			str += "<th>"+list[i].postSN + "</th>";
			str +=  "<th><a class='move' href='/channel/get/"+list[i].postSN+"'>"+list[i].title+"</a></th>";
			str += "<th>"+list[i].nick + "</th>";
			str += "<th>"+channelService.displayTime(list[i].createDateTime) +"</th></tr></thead>";	
			
		}
		board.html("<h1>"+list[0].channelTitle+" 게시판</h1>");
		post.html(str);
	});
	
	$("#listBtn").on("click", function(){
		self.location ="/channel/ch";
	});
	
	$("#createBtn").on("click",function(){
		self.location = "/channel/register/"+channelSN;
	});
	
});
</script>
</body>
</html>
