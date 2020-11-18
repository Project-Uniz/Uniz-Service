<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


    
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading"><h1>글 쓰기</h1></div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					
					<form role="form" action="/channel/register" method="post">
						<div class="form-group">
							<label>제목</label>
							<input class="form-control" name='title'>
						</div>
						
						<div class="form-group">
							<label>내용</label>
							<textarea class="form-control" rows="3" name='postContent'></textarea>
						</div>
						
						<div class="form-group">
							<label>작성자</label>
							<input class="form-control" name='userSN'>
						</div>
						
						<div class="form-group">
							
							<input type="hidden" class="form-control" name='channelSN' value="${channelSN}">
						</div>
						
						<button type="submit" class="btn btn-default">작성완료</button>
						<button type="reset" class="btn btn-default">전체 지우기</button>
					
					</form>
				</div>
			</div>
		</div>
	</div>    
    
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/channel.js"></script>
<script>

$(document).ready(function(){

});

</script>	
</body>
</html>