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

	<h1> <c:out value="${board.boardComment}"/></h1>
		
				<div class="form-group">
					<label>글 번호</label> <input class="form-control" name='postSN'
						value='<c:out value="${board.postSN}" />' readonly="readonly">
				</div>

				<div class="form-group">
					<label>제목</label> <input class="form-control" name='title'
						value='<c:out value="${board.title}" />' readonly="readonly">
				</div>


				<div class="form-group">
					<label>작성자</label> <input class="form-control" name='writer'
						value='<c:out value="${board.nick}" />' readonly="readonly">
				</div>
		
				<div class="form-group">
					<label>내용</label>
					<p><c:out value="${board.postContent}" /></p>
				</div>
		
		<div id="imgs">
		</div>
		
		<div>Files</div>
			<div class="uploadResult">
				<ul>
				</ul>
			</div>
	
	
	<button id='modify'>글 수정</button>
	<button id='list'>목록으로</button>
	
	<div>
	</div>

	<div class="container">
        <label for="content">comment</label>
        <form name="commentInsertForm" >
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
			<div class="panel-footer">
			</div>	
		
		</div>
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/category.js"></script>	
<script type="text/javascript" src="/resources/js/reply.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	
	
	var postSN = '<c:out value="${postSN}"/>';
	var boardSN = '<c:out value="${board.boardSN}"/>';
	console.log(postSN);
	console.log(boardSN);
	//categoryService.getPost(postSN);
	
	//categoryService.getImg(postSN);
	
	showList(1);
	
	function showList(page){
		
		replyService.commentList({postSN : postSN, page : page || 1} ,function(replyCnt, list){
			
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
				  a += '<div class="commentArea" style="border-bottom:1px solid darkgray; margin-bottom: 15px;">';
	                a += '<div class="commentInfo'+list[i].replySN+'">'+'댓글번호 : '+list[i].replySN+' / 작성자 : '+list[i].nick;
	                a += '<a onclick="commentUpdate('+list[i].replySN+',\''+list[i].replyContent+'\');"> 수정 </a>';
	                a += '<a role="button" class="deleteBtn" onclick="remove('+list[i].replySN+');"> 삭제 </a> </div>';
	                a += '<div class="commentContent'+list[i].replySN+'"> <p> 내용 : '+list[i].replyContent +'</p>';
	                a += '</div></div>';
			}
			 $(".reply").html(a);
			 showReplyPage(replyCnt);
		});

	}
	
	$("#modify").on("click", function(){
		self.location = "/category/modify/" + postSN + "/"+ boardSN;
	});
	
	$("#list").on("click", function(){
		self.location = "/category/board/"+boardSN;
	});
	
	var str = "";
	var newReply = $(".container");

	var inputReply = newReply.find("input[name='replyContent']");
	var inputUserSN = newReply.find("input[name='userSN']");
	var inputReplySN = newReply.find("input[name='replySN']");
	var registerBtn = $("#registerBtn");
	
   
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
	
		replyService.add(reply, function(result){
			location.reload(true);
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

	
});

function commentUpdate(replySN, replyContent){
    var a ='';
    console.log("test = " + replySN);
    a += '<div class="input-group">';
    a += '<input type="text" class="form-control" name="content_'+replySN+'" value="'+replyContent+'"/>';
    a += '<input type="text" class="form-control" name="replySN_'+replySN+'" value="'+replySN+'"/>';
    a += '<span class="input-group-btn"><button class="btn btn-default" type="button" onclick="replyUpdate('+replySN+');">수정</button> </span>';
    a += '</div>';
    
    $('.commentContent'+replySN).html(a);
    
}

function replyUpdate(replySN){
	replyService.commentUpdateProc(replySN);
	location.reload(true);
}

function remove(replySN){
	
	if(confirm("삭제하시겠습니까?")){
		replyService.remove(replySN);
		location.reload(true);
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
<script>

$(document).ready(function(){
	
	(function(){
		
		var postSN = '<c:out value="${postSN}"/>';
		
		$.getJSON("/category/getAttachList", {postSN: postSN}, function(arr){
		
			console.log(arr);
			
			var str = "";
			
			$(arr).each(function(i, attach){
				
				if(attach.fileType){
					
					var fileCallPath = encodeURIComponent( attach.uploadPath+"/s_"+ attach.uuid + "_" + attach.fileName);
					
					str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"'data-type'"+attach.fileType+"'><div>";
					str += "<img src='/display?fileName="+fileCallPath+"'>";
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
