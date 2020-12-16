<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/resources/css/Navbar.css">
    <link rel="stylesheet" href="/resources/css/board.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/includes/nav.jsp"%>
	
	<div class="mainPage">
	
	<c:forEach items="${channel}" var="channel">
	<h1 class="boardHeader"><c:out value="${channel.channelTitle}"/></h1>
	</c:forEach>
	<div id ="board"></div>
	
	<div class="board"></div>
	<div></div>
	<h3 class="list">게시글 목록</h3>
	
	<div class="post">
	
	</div>
	
	<div class="chBoardBtn">
		<c:if test="${sessionScope.userId !=null }">
		</c:if>
		<button id="createBtn" type="button">게시글 작성</button>
		<button id="listBtn" type="button">채널 게시판으로 이동</button>
	</div>
	
	<div class="postFooter" id="postFooter">
	</div>

	</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/channel.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	
	var channelTitle = '<c:out value="${channelTitle}"/>';
	
	var channelSN = '<c:out value="${channelSN}"/>';
	var board = $(".board");
	var post = $(".post");
	var postFooter = $("#postFooter");
	
	console.log( "channelTitle" + channelTitle);
	showList(1);
	
	function showList(page){
		
		console.log("show List " + page);
		
		channelService.getChannelPostList( { channelSN : channelSN ,page: page || 1 }, function(postCnt, list){
		
			console.log("postCnt= " + postCnt);
			
			if(page == -1 ){
				pageNum = Math.ceil(postCnt / 10.0);
				showList(pageNum);
				return;
			}
			
			var str = "";
			
			if(list == null || list.length == 0){

				str = "<table class='boardTable' style='table-layout: fixed;'>"
				str += "<thead><tr><th>글 번호</th><th>글 제목</th><th>작성자</th><th>작성 일</th></tr></thead>";
				
			}
			
					str = "<table class='boardTable' style='table-layout: fixed;'>"
					str += "<thead><tr><th>글 번호</th><th>글 제목</th><th>작성자</th><th>작성 일</th></tr></thead>"

				for ( var i = 0, len = list.length || 0; i < len; i ++){

						str += "<thead><tr>"
						str += "<td>"+list[i].postSN + "</td>";
						str += "<td><a class='move' href='/channel/get/"+list[i].postSN+"'>"+list[i].title+"["+list[i].replyCnt+"]"+"</a></td>";
						str += "<td>"+list[i].nick + "</td>";
						str += "<td>"+channelService.displayTime(list[i].createDateTime) +"</td>";	
						str += "</tr></thead>"
						}
					str +="</table>"

				post.html(str);
				showPostPage(postCnt);
			});
			
		}
	
	var pageNum = 1;
	
	
	function showPostPage(postCnt){
		
		var endNum = Math.ceil(pageNum / 10.0) * 10 ;
		var startNum = endNum -9;
		
		var prev = startNum != 1;
		var next = false;
		
		if(endNum * 10 >= postCnt){
			endNum = Math.ceil(postCnt/10.0);
		}
		if(endNum * 10 < postCnt){
			next = true;
		}
		
		var str = "<ul>";
		
		if(prev){
			str += "<li><a href='"+(startNum -1) +"'>Previous</a></li>";
		}
		for ( var i = startNum; i <= endNum; i++){
			var active = pageNum == i ? "active":"";
			str += "<li "+active +"'><a href='"+i+"'>"+i+"</a></li>";
		}
		if(next){
			str += "<li><a href='"+ (endNum + 1) + "'>Next</a></li>";
		}
		str += "</ul>";
		
		
		postFooter.html(str);
	}
	
	postFooter.on("click", "li a", function(e){
		e.preventDefault();
		
		var targetPageNum = $(this).attr("href");
		
		pageNum = targetPageNum;
		showList(pageNum);
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
