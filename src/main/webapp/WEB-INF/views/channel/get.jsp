<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
	
				
		
	<div class="container">
        <label for="content">comment</label>
        <form name="commentInsertForm" action="/chreplies/add" method="post">
            <div class="input-group">
               <input class='postSN' type="text" name="postSN" value="${postSN}"/>
               <input class='userSN' type="text" name="userSN" value=""/>
               <input class='registerReply' type="text" class="form-control" id="replyContent" name="replyContent" placeholder="내용을 입력하세요.">
               <span class="input-group-btn">
                    <button id='registerBtn' type="submit" >등록</button>
               </span>
              </div>
        </form>
    </div>


		
		
		<div>
			
			<div class="reply">
			
			</div>
		
		</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/channel.js"></script>	
<script type="text/javascript" src="/resources/js/chreply.js"></script>	
<script type="text/javascript">

$(document).ready(function(){	
	
	var postSN = '<c:out value="${postSN}"/>';
	
	console.log("게시글 번호 : " + postSN);
	channelService.getPost({postSN:postSN});
	showList(1);
	
});	


function showList(page){
	
	var postSN = '<c:out value="${postSN}"/>';
	chReplyService.commentList({postSN : postSN, page : page || 1});

}

$("#registerBtn").on("click" , function(e){
	
	var str = "";
	var postSN = '<c:out value="${postSN}"/>';
	var newReply = $(".container");
	var inputReply = newReply.find("input[name='replyContent']");
	var inputUserSN = newReply.find("input[name='userSN']");
	var inputReplySN = newReply.find("input[name='replySN']");
	
	
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
	
	chReplyService.add(reply);
	
});



	
function commentUpdate(replySN, replyContent){
    var a ='';
    console.log("test = " + replySN);
    a += '<div class="input-group">';
    a += '<input type="text" class="form-control" name="content_'+replySN+'" value="'+replyContent+'"/>';
    a += '<input type="text" class="form-control" name="replySN_'+replySN+'" value="'+replySN+'"/>';
    a += '<span class="input-group-btn"><button class="btn btn-default" type="button" onclick="commentUpdateProc('+replySN+');">수정</button> </span>';
    a += '</div>';
    
    $('.commentContent'+replySN).html(a);
    
}
function commentUpdateProc(replySN){
    var updateContent = $('[name=content_'+replySN+']').val();
   	var updateReplySN = $('[name=replySN_'+replySN+']').val();
var modify = {'replyContent' : updateContent, 'replySN' : updateReplySN};
    
    $.ajax({
        url : '/chreplies/update/'+updateReplySN,
        type : 'put',
        data : JSON.stringify(modify),
		contentType : "application/json; charset=utf-8",
        success : function(data){
            if(data == 1)
            	commentList(replySN);
            	showList(1); //댓글 수정후 목록 출력 
            
        }
    });
}

function commentDelete(replySN){
	
	if(confirm("삭제하시겠습니까?")){
    $.ajax({
        url : '/chreplies/delete/'+replySN,
        type : 'post',
        success : function(data){
            if(data == 1) commentList(replySN); //댓글 삭제후 목록 출력 
            
            showList(1);
        	}
    	});
	}
}


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