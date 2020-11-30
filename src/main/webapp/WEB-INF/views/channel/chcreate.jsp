<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>채널 등록 페이지</h1>
	
	<div>
		<div>
			<form action="/channel/chcreate" method="post">
				<div>
					<label><h3>등록할 채널 이름</h3></label>
					<input name='channelTitle'>
				</div>
				<div>
					<label><h3>등록할 채널 설명</h3></label>
					<input name='channelComment'>
				</div>
				<div>
				</div>
					<button id='create' type='submit'>채널 만들기</button>
			</form>
					<button id='list'>채널 목록으로 이동</button>
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