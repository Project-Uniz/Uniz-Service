<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<h1> 채널 게시판 </h1>
	<div>
	<button id="boardPost">카테고리 별 게시판으로 이동</button>
	<button id="createChannel">채널 게시판 신청 페이지로 이동</button>
	</div>
	<h3>채널 목록</h3>
	<div id = "channelList"></div>
	<div></div>
	
	
	<h3>전체 게시글 목록</h3>
	<div></div>
	<table class="allPostList">
	</table>
	
	<div class="postFooter"></div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/channel.js"></script>
<script type="text/javascript" src="/resources/js/channelPaging.js"></script>

</body>
</html>