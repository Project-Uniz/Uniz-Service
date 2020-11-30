<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<style> 

table{
    border: 1px solid; 
    border-collapse: collapse;
}
table.tr , td, th{
    border: solid 1px ;
}

input{
    width:100% border:0;
}

</style>

<body>
	
	
	<h1>게시글 보여주는 페이지</h1>
	
	
	<div id="boardPost">
	</div>
	
	<c:if test="${userId.userSN == board.userSN}">
	<button id='modify'>글 수정</button>
	</c:if>
	<button id='list'>목록으로</button>
	<div></div>
	
		
	<div class="my-3 p-3 bg-white rounded shadow-sm" style="padding-top: 10px">
		
		<div class='newReply'>
			<h4>댓글 작성</h4>
			<input class='registerReply' value="댓글 내용" name="replyContent" id="replyContent">
			<input class='userSN' value="작성자 SN" name="userSN">
			<input class='postSN' value="${postSN}" name="postSN">
			<button id='registerBtn' type="submit" >댓글 작성</button>
		</div>
		
		
	</div>
		
		<div>
			
			<table class="reply">
			
			</table>
		
		</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/channel.js"></script>	
<script type="text/javascript" src="/resources/js/chreply.js"></script>	
<script type="text/javascript">

$(document).ready(function(){
	
	
	var postSN = '<c:out value="${postSN}"/>';
	var newReply = $('.newReply');
	var inputReply = newReply.find("input[name='replyContent']");
	var inputUserSN = newReply.find("input[name='userSN']");
	
	var reply = $(".reply");
	var replyUserSN = reply.find("input[name='userSN']");
	
	var registerReplyBtn = $("#registerBtn");
	
	
	console.log("게시글 번호 : " + postSN);
	channelService.getPost({postSN:postSN});
	
	showList(1);
	
	function showList(page){
	
	chReplyService.getList({postSN:postSN , page:1}, function(list){
		var reply = $(".reply");
		
		var str = "";
		
		var userSN = "";
		
		
		
		if(list == null || list.length == 0){
			reply.html("등록된 댓글이 없습니다");
			return;
		}
		
			str += "<thead><tr><th>작성자</th><th>댓글 내용</th><th>작성 시간</th></tr></thead>";
		for(var i = 0 , len = list.length || 0; i < len; i++){
			str += "<tr><td><input type='text' name='text' size='20'  value="+ list[i].nick +" readonly='readonly'><input type='text' name='userSN' size='20'  value="+ list[i].userSN +" readonly='readonly'></td>";
			str += "<td><input class='replyContent' value ='" + list[i].replyContent + "' readonly='readonly' />" +
			'<button class="modifyBtn" type="submit"> 수정 </button>' +
			'<button class="deleteBtn" >   삭제      </button>'
			 + "</td>";
			str += "<td>" + chReplyService.displayTime(list[i].createDateTime) + "</td></tr>";
			
			replySN = list[i].replySN;
			//console.log("replySN = " + replySN);
		}
		
		reply.html(str);
				
				$(".modifyBtn").on("click", function(e){
					var replySN = $(this).data("replySN");
					console.log(replySN);
					chReplyService.get(replySN, function(reply){
						alert("..... " + reply.replySN );
					});
				});		
				
				$(".deleteBtn").on("click", function(e){
					alert("deleteBtn " + replySN);
				});	
		});
	}
	
	
	
	registerReplyBtn.on("click" , function(e){
		
		var reply ={
				replyContent : inputReply.val(),
				userSN : inputUserSN.val(),
				postSN : postSN
		};
		if(reply.replyContent == '' || reply.replyContent.replace(str, '').length == 0 ){
			console.log("test======" + reply.replyContent);
			alert("댓글 내용을 입력해주세요");
			return false;
		}
		chReplyService.add(reply, function(result){
			alert(result);
			showList(1);
		});
		
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