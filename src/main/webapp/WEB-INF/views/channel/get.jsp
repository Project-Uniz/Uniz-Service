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

.uploadResult{

	whidth : 100%;
	background-color : gray;

}

.uploadResult ul{

	display : flex;
	flex-flow : row;
	justify-content : center;
	align-items: center;

}

.uploadResult ul li {

	list-style : none;
	padding : 10px;

}

.uploadResult ul li img{

	width : 50%;
	
}

.bigPictureWrapper {

	position : absolute;
	display : none;
	justify-content : center;
	align-items : center;
	top : 0%;
	width : 100%;
	height : 100%;
	background-color : gray;
	z-index : 100;
	background : rgba(255,255,255,0.5);

}

.bigPicture {

	position : relative;
	display : flex;
	justify-content : center;
	align-items : center;

}

.bigPicture img {

	width : 600px;

}

</style>

<body>
	
	
	<h1>게시글 보여주는 페이지</h1>
	
	
	<div id="boardPost">
	</div>
	
			<div>Files</div>
				<div class="uploadResult">
					<ul>
					</ul>
				</div>
	<c:if test = "${user.userSN eq board.userSN}" >
	<button id='modify'>글 수정</button>
	</c:if>
	<button id='list'>목록으로</button>
	<div></div>
	

				
		
	<div class="container">
        <label for="content">comment</label>
        <form name="commentInsertForm" >
            <div class="input-group">
               <input class='postSN' type="hidden" name="postSN" value="${postSN}"/>
               <input class='userSN' type="hidden" name="userSN" value="${user.userSN}"/>
               <input class='registerReply' type="text" class="form-control" 
               		id="replyContent" name="replyContent" placeholder="내용을 입력하세요." onclick="return checkSession();">
               <span class="input-group-btn">
                    <button id='registerBtn' type="submit" >등록</button>
               </span>
              </div>
        </form>
    </div>
	<div></div>

		
		
		<div>
			
			<div class="reply">
			
			</div>
			<div class="panel-footer">
			</div>	
		
		</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/channel.js"></script>	
<script type="text/javascript" src="/resources/js/chreply.js"></script>	
<script type="text/javascript">

	$(document).ready(function(){	
		
		var postSN = '<c:out value="${postSN}"/>';
		
		channelService.getPost({postSN:postSN});
		
		showList(1);
		
	});	
		
	var postSN = '<c:out value="${postSN}"/>';
		
	var str = "";
	var newReply = $(".container");
	
	var inputReply = newReply.find("input[name='replyContent']");
	var inputUserSN = newReply.find("input[name='userSN']");
	var inputReplySN = newReply.find("input[name='replySN']");
	var registerBtn = $("#registerBtn");
	
	
	function showList(page){
		
		
		var chSession = '<c:out value="${user.userSN}"/>';
		
		sessionStorage.setItem('user', chSession);
		
		var session = parseInt(sessionStorage.getItem('user'));
		
		chReplyService.commentList({postSN : postSN, page : page || 1} ,function(replyCnt, list){
			
			if(page == -1 ){
				pageNum = Math.ceil(replyCny / 10.0);
				showList(pageNum);
				return;
			}
			
			var a = "";
			
			if(list == null || list.length == 0){
				return;
			}
			
			for (var i = 0, len = list.length || 0; i < len; i++){
				console.log("userSN : " + list[i].userSN);
				  a += '<div class="commentArea" style="border-bottom:1px solid darkgray; margin-bottom: 15px;">';
	                a += '<div class="commentInfo'+list[i].replySN+'">'+'댓글번호 : '+list[i].replySN+' / 작성자 : '+list[i].nick;
	                
	                if(session == list[i].userSN){
		                a += '<a onclick="commentUpdate('+list[i].replySN+',\''+list[i].replyContent+'\');"> 수정 </a>';
		                a += '<a role="button" class="deleteBtn" onclick="remove('+list[i].replySN+');"> 삭제 </a></div>';
	                }
	                
	                a += '<div class="commentContent'+list[i].replySN+'"> <p> 내용 : '+list[i].replyContent +'</p></div>';
	                a += '</div></div>';
			}
			 $(".reply").html(a);
			 showReplyPage(replyCnt);
		});
	
	}
	
	registerBtn.on("click" , function(e){
		var str = /^\s+|\s+$/g;
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
			showList(1);
		});
	
	});
	
	var pageNum = 1;
	var replyPageFooter = $(".panel-footer");
	
	function showReplyPage(replyCnt){
		var endNum = Math.ceil(pageNum / 10.0) * 10;
		var startNum = endNum -9;
		
		var prev = startNum != 1;
		var next = false;
		
		if(endNum * 10 >= replyCnt){
			endNum = Math.ceil(replyCnt/10.0);
		}
		if(endNum * 10 < replyCnt){
			next = true;
		}
		var str = "<ul class='pagaination pull-right'>";
		if(prev){
			str += "<li class='page-item'><a class='page-link' href='"+(startNum -1) +"'>Previous</a></li>";
		}
		for ( var i = startNum; i <= endNum; i++){
			var active = pageNum == i ? "active":"";
			str += "<li class='page-item "+active +" '><a class='page-link' href='"+i+"'>"+i+"</a></li>";
		}
		if(next){
			str += "<li class='page-item'><a class='page-link' href='"+ (endNum + 1) + "'>Next</a></li>";
		}
		str += "</ul></div>";
		
		replyPageFooter.html(str);
	}
		replyPageFooter.on("click", "li a", function(e){
		e.preventDefault();
		
		
		var targetPageNum = $(this).attr("href");
		
		pageNum = targetPageNum;
		showList(pageNum);
	});
	
	function commentUpdate(replySN, replyContent){
	    var a ='';
	   
	    a += '<div class="input-group">';
	    a += '<input type="text" class="form-control" name="content_'+replySN+'" value="'+replyContent+'"/>';
	    a += '<input type="hidden" class="form-control" name="replySN_'+replySN+'" value="'+replySN+'"/>';
	    a += '<span class="input-group-btn"><button class="btn btn-default" type="button" onclick="replyUpdate('+replySN+');">수정</button> </span>';
	    a += '</div>';
	    
	    $('.commentContent'+replySN).html(a);
	    
	}

	function replyUpdate(replySN){
		
		chReplyService.commentUpdateProc(replySN);
		showList(1);
		//location.reload(true);
		
	}
	
	function remove(replySN){
		
		if(confirm("삭제하시겠습니까?")){
			chReplyService.remove(replySN);
			showList(1);
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
	
	var chSession = '<c:out value="${user.userSN}"/>';
	
	sessionStorage.setItem('user', chSession);
	
	var session = sessionStorage.getItem('user');
	
	function checkSession(){
		
		console.log("===== " + session);
		
		if(session == '' ){
			alert("로그인이 필요합니다");
			return false;
		}
	}

</script>
<script>

$(document).ready(function(){
	
	(function(){
		
		var postSN = '<c:out value="${postSN}"/>';
		
		$.getJSON("/channel/chgetAttachList", {postSN: postSN}, function(arr){
		
			console.log(arr);
			
			var str = "";
			
			$(arr).each(function(i, attach){
				
				if(attach.fileType){
					
					var fileCallPath = encodeURIComponent( attach.uploadPath+"/s_"+ attach.uuid + "_" + attach.fileName);
					
					str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"'data-type'"+attach.fileType+"'><div>";
					str += "<img src='/chdisplay?fileName="+fileCallPath+"'>";
					str += "</div>";
					str += "</li>";
				}
				
			});
			
			$(".uploadResult ul").html(str);
	
		});
	
	})();
	
});

</script>
</body>
</html>