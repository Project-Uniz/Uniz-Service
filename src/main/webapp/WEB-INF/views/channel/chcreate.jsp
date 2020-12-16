<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Document</title>
	<link rel="stylesheet" href="/resources/css/chcreate.css"/>
</head>
<body>

	<%@ include file="/WEB-INF/views/includes/nav.jsp"%>

	<div class="applyMain">
		<div class="creatorRegisterHeader">
			<h1>채널 등록 페이지</h1>
		</div>
		<div class="createForm">
			<form action="/channel/chcreate" method="post">
				<div class="RegisterForm">
					<label class="label">등록할 채널 이름</label>
					<input class="form-control"name='channelTitle'>
					<label class="label">등록할 채널 설명</label>
					<input class="form-control"name='channelComment'>
				
				</form>
				<div class="applyBtnBox">
					 <button class="submitBtn" id='create' type='submit'>채널 만들기</button> 
					 <button class="submitBtn" id='list'>채널 목록으로 이동</button>
				</div>
				
				</div>
				</div>
			</div>
				
		

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>	

<script type="text/javascript">

$(document).ready(function(){
	$("#list").on("click",function(){
		self.location = "/channel/ch";
	});
	
})

</script>

</body>
</html>