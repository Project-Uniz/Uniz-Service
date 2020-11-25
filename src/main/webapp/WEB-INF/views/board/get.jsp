<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="../includes/header.jsp" %>
		
	<div class="row">
		<div class="col-lg-22">
			<h1 class="page-header">게시글 읽기</h1>
		</div>
		<!--  /.col-lg-12 -->
	</div>
	<!-- /.row -->
	
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">게시판 읽기  </div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					
						<div class="form-group">
							<label>번호</label>
							<input class="form-control" name="postSN" value='<c:out value="${board.postSN}" />' readonly="readonly">
						</div>
						
						<div class="form-group">
							<label>제목</label>
							<input class="form-control" name="title" value='<c:out value="${board.title}" />' readonly="readonly">
						</div>
						
						<div class="form-group">
							<label>내용</label>
							<textarea class="form-control" rows="3" name='postContent' readonly="readonly"><c:out value="${board.postContent}" /></textarea>
						</div>
						
						<div class="form-group">
							<label>작성자</label>
							<input class="form-control" name=userSN value='<c:out value="${board.nick}" />' readonly="readonly">
						</div>
						
						<div class="form-group">
							<label>작성 날짜</label>
							<input class="form-control" name="createDateTime" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${board.createDateTime}"/>'  readonly="readonly">
						</div>
						
						<c:if test="${userId.userSN == board.userSN}">
						<button data-oper='modify' class="btn btn-default" onclick="location.href='/board/modify?postSN=<c:out value="${board.postSN}"/>'"> 게시글 수정</button>
						</c:if>
						
						<button data-oper='list' class="btn btn-info" onclick="location.href='/board/list'"> 목록으로 </button>
						
						<form id='operForm' action="/board/modify" method="get">
							<input type='hidden' id="postSN" name="postSN" value='<c:out value="${board.postSN}"/> '>
							<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
							<input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>
							<input type='hidden' name='type' value ='<c:out value="${cri.type}"/>'>
							<input type='hidden' name='keyword' value ='<c:out value="${cri.keyword}"/>'>
						</form>
						
				</div>
			</div>
		</div>
	</div>
	
	<div class='row'>
		<div class="col-lg-12">
			
			<div class="panel panel-default">
				<div class="panel-heading">
					<i class="fa fa-comments fa-fw"></i> 댓글
					<c:if test="${sessionScope.userId !=null }">
					<button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>댓글 작성</button>
					</c:if>
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
	<!-- 댓글 모달 추가 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">댓글 ${userId.userSN}</h4>
				</div>
				<div class="modal-body">
				<form onsubmit="return checkReply()">	
					<div class="form-group">
						<label>댓글</label>
						<input id='reply' class="form-control" name='replyContent'>
					</div>
       			</form>	
					<div class="form-group">
						<label>작성자</label>
						<input class="form-control" name='userSN' value='${userId.userSN}'>
					</div>
					<div class="form-group">
						<label>작성 시간</label>
						<input class="form-control" name='createdatetime'>
					</div>
				</div>
				<div class="modal-footer">
       				<button id='modalRegisterBtn' type="button" class="btn btn-primary">추가</button>
        			<button id='modalCloseBtn' type='button' class="btn btn-default">취소</button>
					<button id='modalModBtn' type="button" class="btn btn-warning">수정</button>
					<button id='modalRemoveBtn' type="button" class="btn btn-danger">삭제</button>
				</div>
			</div>
		</div>
	</div>
	
		
<%@include file="../includes/footer.jsp" %>

<script type="text/javascript" src="/resources/js/reply.js"></script>
<script type="text/javascript" src="/resources/js/channel.js"></script>

<script type="text/javascript">

function checkReply(){
	
	var checkReply = document.getElementById('reply');
	var blank_pattern = /^\s+|\s+$/g;
	
	if(checkReply.value == '' || checkReply.value == null || checkReply.value.replace(blank_pattern, '').length == 0){
		alert("댓글 내용을 입력하세요");
		return false;
	}
	
	}
	

$(document).ready(function(){
	
	console.log("=============");
	
	var postValue = '<c:out value="${board.postSN}"/>';
	var replyUL = $(".chat");
	
	showList(1);
	
	
	function showList(page){
		
		replyService.getList({postSN:postValue, page: page || 1 }, function(replyCnt, list){
			
			
			if(page == -1 ){
				pageNum = Math.ceil(replyCnt / 10.0);
				showList(pageNum);
				return;
			}
			
			var str="";
			if(list == null || list.length == 0){
				return;
			}
			for (var i = 0, len = list.length || 0; i < len; i++){
				str += "<li class='left clearfix' data-replysn='"+list[i].replySN+"'>";
				str += "<div><div class='header'><strong class='primary-font'>["+list[i].replySN+"]"+list[i].userSN+"</strong>";
				str +=" <samll class='pull-right text-muted'>"+replyService.displayTime(list[i].createdatetime)+"</samll></div>";
				str +=" <p>"+list[i].replyContent+"</p></div></li>";
			}
			replyUL.html(str);
			showReplyPage(replyCnt);
		});
	}
	
	var modal = $(".modal");
	var modalInputReply = modal.find("input[name='replyContent']");
	var modalInputReplyer = modal.find("input[name='userSN']");
	var modalInputReplyDate = modal.find("input[name='createdatetime']");
	var reply = $("#reply").val();
	
	var modalModBtn = $("#modalModBtn");
	var modalRemoveBtn = $("#modalRemoveBtn");
	var modalRegisterBtn = $("#modalRegisterBtn");
	var modalCloseBtn = $("#modalCloseBtn");
	
	
	
	$("#addReplyBtn").on("click", function(e){
		modal.find("input").val("");
		modalInputReplyDate.closest("div").hide();
		modal.find("button[id !='modalCloseBtn']").hide();
		
		modalRegisterBtn.show();
		
		$(".modal").modal("show");
	});
	
	modalRegisterBtn.on("click",function(e){
		var str = /^\s+|\s+$/g;
		var reply = {
				replyContent : modalInputReply.val(),
				userSN : modalInputReplyer.val(),
				postSN : postValue
		};
		if(reply.replyContent == '' || reply.replyContent.replace(str, '').length == 0 ){
			console.log("test======" + reply.replyContent);
			alert("댓글 내용을 입력해주세요");
			return false;
		}
		replyService.add(reply, function(result){
			alert(result);
			modal.find("input").val("");
			modal.modal("hide");
			
			showList(-1);
		});
	});
	
	$(".chat").on("click", "li" ,function(e){
		var replysn = $(this).data("replysn")
			
		replyService.get(replysn, function(reply){
			modalInputReply.val(reply.replyContent);
			
			modalInputReplyer.val(reply.userSN);
		
			modalInputReplyDate.val(replyService.displayTime(reply.createdatetime)).attr("readonly","readonly");
			modal.data("replySN" , reply.replySN);
			
			modal.find("button[id !='modalCloseBtn']").hide();
			modalModBtn.show();
			modalRemoveBtn.show();
			$(".modal").modal("show");
		
		});
	});	
		modalModBtn.on("click", function(e){
			var reply = {replySN : modal.data("replySN"), replyContent : modalInputReply.val()};
			replyService.update(reply, function(result){
				alert(result);
				modal.modal("hide");
				showList(pageNum);
			});
		});
		
		modalRemoveBtn.on("click", function(e){
			var replySN = modal.data("replySN");
			
			replyService.remove(replySN , function(result){
				alert(result);
				modal.modal("hide");
				showList(pageNum);
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

</script>
<script type="text/javascript">

	$(document).ready(function(){
		var operForm = $("#operForm");
		$("button[data-oper='modify']").on("click", function(e){
			operForm.attr("action" , "/board/modify").submit();
		});
		
		$("button[data-oper='list']").on("click" , function(e){
			operForm.find("#postSN").remove();
			operForm.attr("action","/board/list");
			operForm.submit();
		});
});

</script>

