<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Document</title>
	<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
	<link rel="stylesheet" href="../resources/css/apply.css">
</head>
<body>

<%@ include file="/WEB-INF/views/includes/nav.jsp"%>

	<div class="applyMain">
			<div class="creatorRegisterHeader">
				<h1>크리에이터 등록 페이지</h1>
			</div>
				<div class="createForm">
					<form role="form" action="/creator/apply" method="post" >
						
					<div class="RegisterForm">
						
							<label class="label">(운영하는)채널 이름</label>
							<!--<input class="form-control" name='channelTitle' id='channelTitle'> -->
							<input class="form-control" name='channelTitle' id='channelTitle' placeholder="channelName">
				
							<label class="label">UserSN</label>
							<!-- <input class="form-control" name='userSN' id='userSN'> -->
							<input class="form-control" name='userSN' id='userSN' placeholder="userSN">
						
						<div class="CateList">
							<label class="label catList">(운영 채널 주)카테고리 목록</label>
							<select name='category' id='category'>
								<option value='영화/애니메이션'>영화/애니메이션</option>
								<option value='자동차/교통'>자동차/교통</option>
								<option value='음악'>음악</option>
								<option value='애완동물/동물'>애완동물/동물</option>
								<option value='스포츠'>스포츠</option>
								<option value='여행/이벤트'>여행/이벤트</option>
								<option value='게임'>게임</option>
								<option value='인물/블로그'>인물/블로그</option>
								<option value='코미디'>코미디</option>
								<option value='엔터테인먼트'>엔터테인먼트</option>
								<option value='뉴스/정치'>뉴스/정치</option>
								<option value='노하우/스타일'>노하우/스타일</option>
								<option value='교육'>교육</option>
								<option value='과학기술'>과학기술</option>
								<option value='비영리/사회운동'>비영리/사회운동</option>
							</select>
						</div>
						
						<div class="emailForm">
							<label class="label" for="">[연락 받을 이메일 주소를 입력하세요] </label>
							
							<!-- <input  class="form-control" name='email' value=''> -->
							<input  class="form-control" name='email' value='' placeholder="EmailAddress">
						</div>
						
						
						<div class="uploadResult">
							<ul>
							</ul>
							
						</div>
						
						
						
						<label class="label label5">[본인 유튜브 스튜디오 메인 페이지 캡쳐 이미지 업로드 해주세요]</label>						
		   				<div class="uploadFileBox"> 
							<label for="uploadFile">파일 선택하기</label> 
								<div class="ChseBtn">
								
								<input type='file' name='uploadFile'id="uploadFile" multiple >
							</div>
						</div>
						
						<div class="applyBtnBox">
						<!-- <button type="submit" >등록 신청하기</button> -->
						<button class="submitBtn" type="submit" >등록 신청하기</button>
						<!-- <button type="reset" class="btn btn-default">전체 지우기</button> -->
 						<button class="delBtn" type="reset" >전체 지우기</button>
						</div>
					</div>
				</form>
		</div>
 	</div>
	
	
					
    
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>

$(document).ready(function(e){
	
	var formObj = $("form[role='form']");
	
	$("button[type='submit']").on("click", function(e){
		
		e.preventDefault();
		
		console.log("submit clicked");
		
		var str = "";
	
	$(".uploadResult ul li").each(function(i, obj){
		
		var jobj = $(obj);
		console.dir(jobj);
		
		str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
		str += "<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
		str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
		str += "<input type='hidden' name='attachList["+i+"].fileType' value='"+jobj.data("type")+"'>";
		
	});
		
		console.log("str : " + str);
		formObj.append(str).submit();
		
		
	});
	
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	var maxSize = 5242880;
	
	function checkExtension(fileName, fileSize){
		if(fileSize >= maxSize){
			alert("파일 사이즈 초과");
			return false;
		}
		if(regex.test(fileName)){
			alert("해당 종류의 파일은 업로드 할 수 없습니다.");
			return false;
		}
		return true;
	}
	
	function showUploadResult(uploadResultArr){
		
		if(!uploadResultArr || uploadResultArr.length == 0){return;}
		
		var uploadUL = $(".uploadResult ul");
		
		var str = "";
		
		$(uploadResultArr).each(function (i, obj){
			
	        if(obj.image){
	        	var fileCallPath = encodeURIComponent( obj.uploadPath + "/s_"+
	        			obj.uuid + "_" + obj.fileName);
	        	str += "<li data-path='"+obj.uploadPath+"'";
	        	str += "data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"'data-type='"+obj.image+"'"		
	        	str += "><div>";
	        	str += "<span>" + obj.fileName + "</span>";
	        	str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='image' class='btn btn-warning btn-circle'> <i class='fa fa-times'>x</i></button><br>";
	        	str += "<img src='/apDisplay?fileName="+fileCallPath+"'>";
	        	str += "</div>";
	        	str += "</li>";
	        }else {
	        	var fileCallPath = encodeURIComponent( obj.uploadPath +"/"+
	        			obj.uuid + "_" + obj.fileName);
	        	var fileLink = fileCallPath.replace(new RegExp(/\\/g), "/");
	        	
	        	str += "<li data-path'"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"'data-type='"+obj.image+"'><div>";
	        	str += "<span>" + obj.fileName + "</span>";
	        	str += "<button type='button' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
	        	str += "<img src='/resources/img/attach.png'></a>";
	        	str += "</div>";
	        	str += "</li>";
	        }
			
		});
		
		uploadUL.append(str);
		
	}
	
	$("input[type='file']").change(function(e){

		var formData = new FormData();
		
		var inputFile = $("input[name='uploadFile']");
		
		var files = inputFile[0].files;
		
		console.log(files);
		
		for(var i = 0; i < files.length; i++){
				
			if(!checkExtension(files[i].name, files[i].size)){
				return false;
			}
			formData.append("uploadFile", files[i]);
			console.log("formData : " + formData);
		}
	    
	    $.ajax({
	    	url : '/apUploadAjaxAction',
			processData : false,
			contentType : false,
			data : formData,
			type : 'POST',
			dataType : 'json' ,
			success : function(result){
				console.log(result);
			    showUploadResult(result); //업로드 결과 처리 함수 
	     }
	 }); //$.ajax
	    
 });  
});

$(".uploadResult").on("click", "button" , function(e){
	
	console.log("delete file");
	
	var targetFile = $(this).data("file");
	var type = $(this).data("type");
	
	var targetLi = $(this).closest("li");
	
	$.ajax({
		
		url: '/apDeleteFile',
		data: {fileName: targetFile, type: type},
		dataType: 'text',
		type: 'POST',
			success: function(result){
				
				targetLi.remove();
			}
		
	});
	
});



function checkTitle(){


var str = document.getElementById('title');
var blank_pattern = /^\s+|\s+$/g;

if(str.value == '' || str.value == null || str.value.replace(blank_pattern, '').length == 0){
	alert("제목을 입력하세요");
	return false;
}

}

</script>
</body>
</html>