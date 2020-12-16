<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../resources/css/get.css">
    <link rel="stylesheet" href="../resources/css/apply.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/includes/nav.jsp"%>
	
<div class="applyMain" style="height: 1100px;">
    <div class="creatorRegisterHeader">
        <h1>등록 조회 페이지</h1>
    </div>
	
	<div class="createForm">
    	<div class="RegisterForm">
	        <label class="label">(운영하는)채널 이름</label>
		
		<input class="form-control" name='channelTitle' id='channelTitle' 
		value="<c:out value="${apply.channelTitle}" />" readonly="readonly">
		
		<label class="label">UserSN</label>
		
		<input class="form-control" name='userSN' id='userSN'
		value="<c:out value="${apply.userSN}" />" readonly="readonly">
		
		<label class="label">채널 운영 주 카테고리 목록</label>
		
		<input  class="form-control" name='category' 
		value='<c:out value="${apply.category}"/>' readonly="readonly">
		
		<label class="label">[연락 받을 이메일 주소를 입력하세요]</label>
		
		<input  class="form-control" name='email' 
		value='<c:out value="${apply.email}"/>' readonly="readonly">
	
	<div class="uploadResult">
		<ul>
		</ul>
	</div>
	
	<br>
	<div class="applyBtnBox" style="margin-top: 40px;">
	<!-- <button class="modify">수정하기</button> -->
	<button class="modify">수정하기</button>
	<!-- <button class="list">채널 게시판으로 이동</button> -->
	<button class="list">채널 게시판으로 이동</button>
	</div>
	</div>
</div>
</div>	
		
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
