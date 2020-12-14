<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>신청 상세 내용 보는 페이지</h1>
	
	<div class="form-group">
						
	<p>[운영하는 채널 이름]</p>
		
	<label>채널 이름</label>
		
		<input class="form-control" name='channelTitle' id='channelTitle' 
		value="${apply.channelTitle}" readonly="readonly">
		
	</div>
		
	<br>
		
	<div class="form-group">
		
		<p>[userSN]</p>
		
		<label>userSN</label>
		
		<input class="form-control" name='userSN' id='userSN'
		value="${apply.userSN}" readonly="readonly">
		
	</div>
		
	<br>
		
	<div class="form-group">
		
		<p>[운영하는 채널의 주 카테고리]</p>
		
		<label>카테고리 목록</label>
		
		<input  class="form-control" name='category' 
		value='${apply.category}' readonly="readonly">
		
		
	</div>
		
	<br>
		
	<div class="form-group">
		
		<p>[연락 받을 이메일 주소를 입력하세요] </p>
		
		<label>이메일 주소</label>
		
		<input  class="form-control" name='email' 
		value='${apply.email}' readonly="readonly">
		
	</div>
	
	<br>
	
	<form role="form" action="/admin/accept" method="post">
		<input id="userSN" name="userSN" value="${apply.userSN}">
		<button type="submit" data-oper='accept'>수락</button>
		<button data-oper='refuse'>거절</button>
	</form>
	
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(document).ready(function(e){
	
		var formObj = $("form");
		
		$('button').on("click", function(e){
			
			e.preventDefault();
			
			var oper = $(this).data("oper");
			
			if(oper === 'accept'){
				
				formObj.attr("action" , "/admin/accept");
				
			} else if(oper === 'refuse'){
				
				if(confirm("거절 하시겠습니까?")){
					
					formObj.attr("action" , "/admin/refuse");
					
				}
				
			}
			
			formObj.submit();
			
		});
		
	});
</script>	
</body>
</html>