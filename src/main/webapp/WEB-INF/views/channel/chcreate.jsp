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
					<button id='create' data-oper='create' type='submit' >채널 만들기</button>
					<button data-oper='list' id='list'>채널 목록으로 이동</button>
			</form>
		</div>
	</div>
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>	

<script type="text/javascript">

$(document).ready(function(){
	
	console.log("ggggg");
	
	var formObj = $("form");
	
	$('button').on("click", function(e){
		
		e.preventDefault();
		
		var oper = $(this).data('oper');
		
		if(oper == 'list'){
			
			self.location="/channel/ch";
			return;
			
		} if(oper == 'create'){
			
			formObj.submit();
			
		}
		
	});
	
});

</script>

</body>
</html>