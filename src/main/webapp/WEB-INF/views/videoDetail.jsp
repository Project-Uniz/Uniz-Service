<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>





<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<style>
.inner-div {
	width: 800px;
}

.video-container {
	position: relative;
	height: 0;
	padding-bottom: 56.25%;
	top: 50%;
	left: 50%;
}

.video-container iframe {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
}

.video-text {
	position: relative;
	height: 0;
	top: 50%;
	left: 50%;
}
</style>
</head>
<body>

	<div class="">

		<div class="inner-div">

			<div class="video-container">
				<iframe width="600" height="315"
					src="//www.youtube.com/embed/${videoData.urlPath}" frameborder="0"
					allowfullscreen></iframe>

			</div>

			<div class="video-text">
				<p>${videoData.title}</p>
				<p>게시자닉네임${videoData.authorNick}</p>
				<p>조회수 :${videoData.viewCnt}회</p>
				<p>좋아요 : ${videoData.likeCnt}개</p>
				<p>업로드일:${videoData.createDateTime}</p>
				
				<div class='row'>
		<div class="col-lg-12">
			
			<div class="panel panel-default">
				<div class="panel-heading">
					
		<div class="container">
        	<label for="content">comment</label>
        	<form name="commentInsertForm">
            <div class="input-group">
               <input type="text" name="videoSN" value="${videoData.videoSN}"/>
               <input type="text" class="form-control" id="content" name="content" placeholder="내용을 입력하세요.">
               <input class='userSN' value="작성자 SN" name="userSN">
               <span class="input-group-btn">
                    <button class="btn btn-default" type="button" name="commentInsertBtn">등록</button>
               </span>
              </div>
        </form>
    </div>

				</div>
				
				<div class="panel-body">
					
					<ul class="chat">
						<li class="left clearfix" data-replySN='12'>
						<div>
							<div class="header">
								<strong class="primary-font"></strong>
								<small class="pull-right text-muted"></small>
							</div>
							<p></p>
						</div>
					</ul>
					<!-- /.panel .chat-panel 추가 -->
					<div class="panel-footer">
					</div>					
				</div>
			</div>
		</div>
	</div>
				
			</div>


		</div>
	</div>




	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script type="text/javascript" src="/resources/js/videoReply.js"></script>

	<script type="text/javascript">
		$(document).ready(function(){
		
	   		 showList(); //페이지 로딩시 댓글 목록 출력 
	    
		});
	
		var videoValue = '${videoData.videoSN}';
		var replyUL = $(".chat");
		var container = $('.container');
		var contextInputReply = container.find("input[name='content']");
		var contextInputReplyer = container.find("input[name='userSN']");
		var contextInputReplyDate = container.find("input[name='createDateTime']"); 
		
		
		
		//showList(목록)
		function showList(page){
			
			replyService.getList({videoSN:videoValue,page: page||1}, function(list){
			
				var str="";
				if(list == null || list.length == 0){
					
					replyUL.html("");
					
					return;
				}
				for(var i = 0, len =list.length || 0; i<len; i++){
					str +="	<li class='left clearfix' data-replySN='"+list[i].replySN+"'>";
					str +=" <div><div class='header'><strong class='primary-font'>"+list[i].userSN+"</strong>";
					str +='	<button class="commentUpdate" onclick="commentUpdate('+list[i].replySN+',\''+list[i].replyContent+'\');"> 수정 </button>';
	                str +='	<button class="remove" onclick="replyService.remove('+list[i].replySN+');"> 삭제 </button> </div>';
					str +=" <small class='pull-right test-muted'>"+replyService.displayTime(list[i].createDateTime)+"</small></div>";
					str +='	<div class="commentContent'+list[i].replySN+'"> <p> 내용 : '+list[i].replyContent +'</p>';
				
				}
				
				replyUL.html(str);
					
				});
				
			}//end showList
			
			
			//Insert
			$('[name=commentInsertBtn]').click(function(){ //댓글 등록 버튼 클릭시 
			    var insertData = $('[name=commentInsertForm]').serialize(); //commentInsertForm의 내용을 가져옴
			    
			    var reply ={
						replyContent : contextInputReply.val(),
						userSN : contextInputReplyer.val(),
						videoSN : videoValue
				}
			    replyService.add(reply, function(result){
					
					showList(1);
				}); //Insert 함수호출(아래)
		});//end insert 
		
		//Update
		function commentUpdate(replySN, replyContent){
			
			console.log("==================");
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
		        url : '/unizReply/'+updateReplySN,
		        type : 'put',
		        data : JSON.stringify(modify),			      
				contentType : "application/json; charset=utf-8",
		        success :function(data){
		        	
		            	showList(1); //댓글 수정후 목록 출력 
		            
		        }
				});
		  
			}//end Update
			 
	</script>




</body>
</html>