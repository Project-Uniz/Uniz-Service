<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../includes/header.jsp" %>
	
	
	<%= session.getAttribute("userId")  %>
	
	<div class="row">
		<div class="col-lg-22">
			<h1 class="page-header">게시글 쓰기</h1>
			
		</div>
		<!--  /.col-lg-12 -->
	</div>
	<!-- /.row -->
	
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">게시판 작성</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<form id='regPost' role="form" action="/board/register" method="post" onsubmit="return checkTitle()">
						<div class="form-group">
							<label>제목</label>
							<input type='text' name='title' class="form-control"  id='title'>
						</div>
						
						<div class="form-group">
							<label>내용</label>
							<textarea class="form-control" rows="3" name='postContent'></textarea>
						</div>
						
						<div class="form-group">
							
							<input class="form-control" name='userSN' value='${userId.userSN}' >
						</div>
						
						<button type="submit" class="btn btn-default">작성완료</button>
						<button type="reset" class="btn btn-default">전체 지우기</button>
					
					</form>
				</div>
			</div>
		</div>
	</div>
		
<%@include file="../includes/footer.jsp" %>

<script type="text/javascript">

function checkTitle(){
	
	var str = document.getElementById('title');
	var blank_pattern = /^\s+|\s+$/g;
	
	if(str.value == '' || str.value == null || str.value.replace(blank_pattern, '').length == 0){
		alert("제목을 입력하세요");
		return false;
	}
	
}

</script>


