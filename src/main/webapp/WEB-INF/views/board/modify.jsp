<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="../includes/header.jsp" %>

<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">게시판 수정</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
	
					<form role="form" action="/board/modify" method="post">
						<div class="form-group">
							<label>번호</label>
							<input class="form-control" name='postSN' value='<c:out value="${board.postSN}"/>' readonly="readonly">
						</div>
						
						<div class="form-group">
							<label>제목</label>
							<input class="form-control" name='title' value='<c:out value="${board.title}"/>'>
						</div>
						
						<div class="form-group">
							<label>내용</label>
							<textarea class="form-control" rows="3" name='postContent'><c:out value="${board.postContent}"/></textarea>
						</div>
						
						<div class="form-group">
							<label>작성자</label>
							<input class="form-control" name='userSN' value='<c:out value="${board.nick}"/>' readonly="readonly">
						</div>
						
						<div class="form-group">
							<label>등록 시간</label>
							<input class="form-control" name="createDateTime"
							value='<fmt:formatDate pattern="yyyy/MM/dd" value="${board.createDateTime}"/>' readonly="readonly">
						</div>
						
						<button type="submit" data-oper='modify' class="btn btn-default">수정</button>
						<button type="submit" data-oper='remove' class="btn btn-danger">삭제</button>
						<button type="submit" data-oper='list' class="btn btn-info">목록으로</button>
						
					</form>	
					
			</div>
		</div>
	</div>
</div>
	
<%@include file="../includes/footer.jsp" %>

<script type="text/javascript">
	
$(document).ready(function(){
	
	var formObj = $("form");
	
	$('button').on("click" , function(e){
		
		e.preventDefault();
		
		var operation = $(this).data("oper");
		
		console.log(operation);
		
		if(operation === 'remove'){
			formObj.attr("action" , "/board/remove");
		}else if(operation ==='list'){
			formObj.attr("action", "/board/list").attr("method","get");
			
			var pageNumTag = $("input[name='pageNum']").clone();
			var amountTag = $("input[name='amount']").clone();
			var keywordTag = $("input[name='keyword']").clone();
			var typeTag = $("input[name='type']").clone();
			
			formObj.empty();
			formObj.append(pageNumTag);
			formObj.append(amountTag);
			formObj.append(keywordTag);
			formObj.append(typeTag);
		}
		formObj.submit();
	});
	
});
		

</script>
