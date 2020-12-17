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
    <link rel="stylesheet" href="../resources/css/Navbar.css">
    <link rel="stylesheet" href="../resources/css/videoDetail.css">
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

</head>
<body>

   <%@ include file="/WEB-INF/views/includes/nav.jsp"%>

   <div class="mainPage">
      <div class="watchMainBox">
			<div class="videoWatch">
				<iframe src="//www.youtube.com/embed/${videoData.urlPath}" frameborder="0" allowfullscreen></iframe>
			</div>
			
			<div class="class101">
          		<div class="slideshow-container">
            		
				<h3>${videoData.title}</h3>


				<p>게시자닉네임${videoData.authorNick}</p>
				<p><i class="far fa-eye"></i>조회수 :${videoData.viewCnt}회</p>
				<p><i class="far fa-thumbs-up"></i>좋아요 : ${videoData.likeCnt}개</p>
				<p><i class="far fa-clock"></i>업로드일:${videoData.createDateTime}</p>
				
				
					
					
		 	<form id="form" >
	                <input type="hidden" id="duration" name ="" value="${videoData.duration}">
	                <input type="hidden" id="videoSN" name ="videoSN" value="${videoData.videoSN}">
	                <input type="hidden" id="unizSN" name ="unizSN" value="${videoData.utbCateSN}">
	                <input type="hidden" id="userSN" name ="userSN" value="${userSN}">

                </form>  
                
				<div class="commentPage">

					<ul class="chat">
					
						<!-- <li class="left clearfix" data-replySN='12'> -->
						<li class="left clearfix" data-replySN='12'><i class="far fa-comment"></i>
						<div>
							<p></p>
						</div>
						</li>
					</ul>		
				 </div>
				 
			<!-- <div class="container"> -->
			<div class="addCmtBox">
            	<div class="input-group">
        			<form name="commentInsertForm">
		               <input type="hidden" name="videoSN" value="${videoData.videoSN}"/>
		               <!-- <input type="text" class="form-control" id="content" name="content" placeholder="내용을 입력하세요."> -->
		               <input type="text" class="form-control" id="content" name="content" onclick="return checkSession();" placeholder="add a comment..........">
		               <input type="hidden" class='userSN' value="${user.userSN}" name="userSN">
		               <span class="input-group-btn">
					<!-- <button class="btn btn-default" type="button" name="commentInsertBtn">등록</button> -->    
		               <button  type="button" name="commentInsertBtn"  ><i class="far fa-paper-plane"></i></button>
		               </span>
        			</form>
              	</div>
    		</div>
    		
			
			</div> <!-- end slideshow-container -->
		</div>
	</div>
</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script type="text/javascript" src="/resources/js/videoReply.js"></script>
<script>
	$(document).ready(function(){
	
		showList();
		
		let d = new Date();
		
		//페이지 시작시 타이머를 돌린다.
		let startTime = d.getTime();
		
		//전체 영상의 시간
		let duration = $("#duration").val();
		console.log(duration);
		//전체 영상 길이의 20%
		let min_duration = duration * 0.20;
		
			
		console.log("start : " + startTime);
		
		//1. 머문시간이 전체 영상시간의 20프로가 넘으면 유니즈 포인트 증가
		setTimeout(getTimeCal, 1000 * min_duration);
		
		function getTimeCal(){
			let ds = new Date();
			let endTime = ds.getTime();
			console.log("endTime" + endTime);
			
			let calTime = (endTime - startTime)/1000;
			
			let Form = $("#form").serialize()
			console.log("codeForm: "+Form);
			
			$.ajax({
				type : "POST",
				url : "/uniz/addMypoint",
				data : Form,
				dataType: "json",
				success: function(data){
					const SUCCESS = "SUCCESS";
					if(data.result == SUCCESS){
						console.log("전체영상의 길이 : " + duration + "  영상의 20% :"+ min_duration+"초를 을 시청하여 UnizPoint를 증가");
					}else{
						console.log("등록 실패")
					}
				}
			})
		}
		//2.
		window.onbeforeunload = function(){
			
			let videoSN = $("#videoSN").val();
			let userSN = $("#userSN").val();
			
			// 페이지를 떠나기전 지금까지 본 영상을 저장
			let ds = new Date();
			let endTime = ds.getTime();
			
			let currentTime = Math.round(parseInt((endTime - startTime)/1000));
			console.log(currentTime);			
			
			$.ajax({
				type : "POST",
				url : "/user/addHistory",
				data : {videoSN : videoSN, userSN : userSN, currentTime : currentTime},
				dataType: "json",
				success: function(data){
					const SUCCESS = "SUCCESS";
					if(data.result == SUCCESS){
						console.log("전체영상의 길이 : " + duration + "  영상의 20% :"+ min_duration+"초를 을 시청하여 UnizPoint를 증가");
					}else{
						console.log("등록 실패")
					}
				}
			});
			
		};
	});
	
	var videoValue = '${videoData.videoSN}';
	var replyUL = $(".chat");

	var container = $('.addCmtBox');
	var contextInputReply = container.find("input[name='content']");
	var contextInputReplyer = container.find("input[name='userSN']");
	var contextInputReplyDate = container.find("input[name='createDateTime']");
	var chSession = '<c:out value="${user.userSN}"/>';
		sessionStorage.setItem('user', chSession);
	var session = sessionStorage.getItem('user');

	
		//페이지 로딩시 댓글 목록 출력
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
					/* str +=" <small class='pull-right test-muted'>"+replyService.displayTime(list[i].createDateTime)+"</small></div>"; */
					str +='	<div class="commentContent'+list[i].replySN+'"> <li> '+list[i].nick+' : '+list[i].replyContent ;
					if(list[i].userSN == session){
					str +='	<button class="commentUpdate" onclick="commentUpdate('+list[i].replySN+',\''+list[i].replyContent+'\');"> 수정 </button>';
	                str +='	<button class="remove" onclick="replyService.remove('+list[i].replySN+');"> 삭제 </button></li> </div></li>';
					}
				}
				
				replyUL.html(str);
					
				});
			}//end showList
    	//Insert
			$('[name=commentInsertBtn]').click(function(){ //댓글 등록 버튼 클릭시
			    var insertData = $('[name=commentInsertForm]').serialize(); //commentInsertForm의 내용을 가져옴
			    
			    var str = /^\s+|\s+$/g;

			    var reply ={
						replyContent : contextInputReply.val(),
						userSN : contextInputReplyer.val(),
						videoSN : videoValue
				}
			    if(reply.replyContent == '' || reply.replyContent.replace(str, '').length == 0){
					alert("댓글 내용을 입력하세요");
					return false;
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
		    a += '<span class="input-group-btn"><button  type="button" onclick="commentUpdateProc('+replySN+');">수정</button> </span>';
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
  
			function checkSession(){
			
			if(session == ""){
				alert("로그인이 필요합니다");
				return false;
				}
			}
</script>
</body>
</html>