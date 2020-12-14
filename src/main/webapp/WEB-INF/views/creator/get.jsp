<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>

.uploadResult{

	whidth : 100%;

}

.uploadResult ul{

	display : inline-block;
	flex-flow : row;
	justify-content : center;
	align-items: center;

}

.uploadResult ul li {

	list-style : none;
	padding : 10px;

}

.uploadResult ul li img{

	width : 100%;
	
}

.bigPictureWrapper {

	position : absolute;
	display : none;
	justify-content : center;
	align-items : center;
	top : 0%;
	width : 100%;
	height : 100%;
	background-color : gray;
	z-index : 100;
	background : rgba(255,255,255,0.5);

}

.bigPicture {

	position : relative;
	display : flex;
	justify-content : center;
	align-items : center;

}

.bigPicture img {

	width : 600px;

}

</style>
<body>
	<h1>등록 조회 페이지</h1>
	
	<div class="form-group">
						
	<p>[운영하는 채널 이름]</p>
		
	<label>채널 이름</label>
		
		<input class="form-control" name='channelTitle' id='channelTitle' 
		value="<c:out value="${apply.channelTitle}" />" readonly="readonly">
		
	</div>
		
	<br>
		
	<div class="form-group">
		
		<p>[userSN]</p>
		
		<label>userSN</label>
		
		<input class="form-control" name='userSN' id='userSN'
		value="<c:out value="${apply.userSN}" />" readonly="readonly">
		
	</div>
		
	<br>
		
	<div class="form-group">
		
		<p>[운영하는 채널의 주 카테고리]</p>
		
		<label>카테고리 목록</label>
		
		<input  class="form-control" name='category' 
		value='<c:out value="${apply.category}"/>' readonly="readonly">
		
		
	</div>
		
	<br>
		
	<div class="form-group">
		
		<p>[연락 받을 이메일 주소를 입력하세요] </p>
		
		<label>이메일 주소</label>
		
		<input  class="form-control" name='email' 
		value='<c:out value="${apply.email}"/>' readonly="readonly">
		
	</div>
	
	<br>
	
	<div class="uploadResult">
		<ul>
		</ul>
	</div>
	
	<br>
	
	<button class="modify">수정하기</button>
	<button class="list">채널 게시판으로 이동</button>
		
		
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>	
<script type="text/javascript">

	$(document).ready(function(e){
		
		let userSN = "${apply.userSN}";
		
		$(".modify").on("click", function(){
			
			self.location = "/creator/modify?userSN=" + userSN;
			
		});
		
		$(".list").on("click", function(){
			
			self.location = "/channel/ch";
			
		});
		
	});
	
</script>
<script>

$(document).ready(function(){
	
	(function(){
		
		var applySN = "${apply.applySN}";
		
		console.log("applySN : " + applySN);
		
		$.getJSON("/creator/getAttachList", {applySN: applySN}, function(arr){
		
			console.log(arr);
			
			var str = "";
			
			$(arr).each(function(i, attach){
				
				if(attach.fileType){
					
					var fileCallPath = encodeURIComponent( attach.uploadPath+"/"+ attach.uuid + "_" + attach.fileName);
					
					str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"'data-type'"+attach.fileType+"'><div>";
					str += "<img src='/apDisplay?fileName="+fileCallPath+"'>";
					str += "</div>";
					str += "</li>";
					
					console.log("str : " + str);
				}
				
			});
			
			$(".uploadResult ul").html(str);
	
		});
	
	})();
	
});

</script>
</body>
</html>
