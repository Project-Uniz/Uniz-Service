<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>      
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
	
	<div class="applyMain" style="height: 1300px;">
    <div class="creatorRegisterHeader">
        <h1>신청 수정 페이지</h1>
    </div>
	<div class="createForm">
	<form role="form" action="/creator/modify" method="post">
	
	
	
    <div class="RegisterForm">
        <div class="form-group">
        
        <label class="label">(운영하는)채널 이름</label>
		<input class="form-control" name='channelTitle' id='channelTitle' 
		value="<c:out value="${apply.channelTitle}" />" >
		
	</div>
	<div class="form-group">
		
		<label class="label">userSN</label>
		
		<input class="form-control" name='userSN' id='userSN'
		value="<c:out value="${apply.userSN}" />" readonly="readonly">

		<input type='hidden' class="form-control" name='applySN' id='applySN'
		value="<c:out value="${apply.applySN}" />" readonly="readonly">
		
	</div>
		
	<div class="labelB" style="height: 70px;">
            <label class="label" style="line-height: 30px;">(채널 운영자)카테고리 목록</label>
		
		<select name='category' id='category'>
			<option value="영화/애니메이션" ${apply.category == '영화/애니메이션' ? 'selected="selected"' : '' }>영화/애니메이션</option>
			<option value="자동차/교통" ${apply.category == '자동차/교통' ? 'selected="selected"' : '' }>자동차/교통</option>
			<option value="음악" ${apply.category == '음악' ? 'selected="selected"' : '' }>음악</option>
			<option value="애완동물/동물" ${apply.category == '애완동물/동물' ? 'selected="selected"' : '' }>애완동물/동물</option>
			<option value="스포츠" ${apply.category == '스포츠' ? 'selected="selected"' : '' }>스포츠</option>
			<option value="여행/이벤트" ${apply.category == '여행/이벤트' ? 'selected="selected"' : '' }>여행/이벤트</option>
			<option value="게임" ${apply.category == '게임' ? 'selected="selected"' : '' }>게임</option>
			<option value="인물/블로그" ${apply.category == '인물/블로그' ? 'selected="selected"' : '' }>인물/블로그</option>
			<option value="코미디" ${apply.category == '코미디' ? 'selected="selected"' : '' }>코미디</option>
			<option value="엔터테인먼트" ${apply.category == '엔터테인먼트' ? 'selected="selected"' : '' }>엔터테인먼트</option>
			<option value="뉴스/정치" ${apply.category == '뉴스/정치' ? 'selected="selected"' : '' }>뉴스/정치</option>
			<option value="노하우/스타일" ${apply.category == '노하우/스타일' ? 'selected="selected"' : '' }>노하우/스타일</option>
			<option value="교육" ${apply.category == '교육' ? 'selected="selected"' : '' }>교육</option>
			<option value="과학기술" ${apply.category == '과학기술' ? 'selected="selected"' : '' }>과학기술</option>
			<option value="비영리/사회운동" ${apply.category == '비영리/사회운동' ? 'selected="selected"' : '' }>비영리/사회운동</option>
		</select>
	</div>
		
	 <div class="emailForm">
                <label class="label" style="line-height: 40px;">[연락 받을 이메일 주소를 입력하세요]</label>
		
		<input class="form-control" name='email' 
		value='<c:out value="${apply.email}"/>' >
		
	</div>
	
	<div class="thumbNail">
		<div class="upload">
			<ul>
			</ul>
		</div>
	</div>
	
	<div class="thumbNail">
		<div class="uploadResult">
			<ul>
			<li><p>[첨부한 이미지]</p></li>
			</ul>
		</div>	
	</div>
	
	<div class="uploadFileBox" style="margin-top: 30px;"> 
		<label for="uploadFile">파일 선택하기</label>
		<div class="ChseBtn">
		<input type="file" name="uploadFile" multiple="multiple">
		</div>
	</div>
	
	<!-- <button type="submit" data-oper='modify' class="btn btn-default">수정완료</button> -->
	<button class="submitBtn" type="submit" data-oper='modify' class="btn btn-default">수정완료</button>
	<!-- <button data-oper='cancle'>신청 취소</button> -->
	<button class="submitBtn" data-oper='cancle'>신청 취소</button>
	<!-- <button data-oper='list'>널 게시판으로 이동</button> -->
	<button class="submitBtn" data-oper='list'>채널 게시판으로 이동</button>
	</div>
	</form>
	
	</div>
</div>

	<div class="footer">
 </div>
</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(e){
		
		var formObj = $("form");
		
		let category = '${apply.category}';
		
		console.log(category);
		
		$("#test").val(category).prop("selected", true);
		
		$('button').on("click", function(e){
			
			e.preventDefault();
			
			var oper = $(this).data("oper");
			
			console.log(oper);
			
			if(oper === 'cancle'){
				
				if(confirm("정말 취소 하시겠습니까?")){
					alert("취소 되었습니다.");
					formObj.attr("action" , "/creator/remove");
				}
				
			} else if (oper === 'list' ){
				
				self.location = "/channel/ch";
				
			}else if(oper === 'modify'){
		        
		        console.log("submit clicked");
		        
		        var str = "";
		        
		        $(".uploadResult ul li").each(function(i, obj){
		          
		          var jobj = $(obj);
		          
		          console.dir(jobj);
		          
		          str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
		          str += "<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
		          str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
		          str += "<input type='hidden' name='attachList["+i+"].fileType' value='"+ jobj.data("type")+"'>";
		          
		        });
		        
				alert("수정 되었습니다.");
		        formObj.append(str).submit();
		        
	        }
				formObj.submit();
			
		});
		
	});
</script>
<script>

$(document).ready(function(){
	
	(function(){
		
		var applySN = '${apply.applySN}';
		
		$.getJSON("/creator/getAttachList", {applySN: applySN}, function(arr){
		
			console.log(arr);
			
			var str = "";
			
			$(arr).each(function(i, attach){
				
				if(attach.fileType){
					
					var fileCallPath =  encodeURIComponent( attach.uploadPath+ "/s_"+attach.uuid +"_"+attach.fileName);
		            
		            str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' "
		            str +=" data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
		            str += "<span> "+ attach.fileName+"</span>";
		            str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='image' "
		            str += "class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
		            str += "<img src='/apDisplay?fileName="+fileCallPath+"'>";
		            str += "</div>";
		            str +"</li>";
				}
			});
			
			$(".uploadResult ul").html(str);
	
		});
	
	})();
	
(function(){
		
		var applySN = '${apply.applySN}';
		
		$.getJSON("/creator/getAttachList", {applySN: applySN}, function(arr){
		
			console.log(arr);
			
			var str = "";
			
			$(arr).each(function(i, attach){
				
				if(attach.fileType){
					
					var fileCallPath =  encodeURIComponent( attach.uploadPath+ "/s_"+attach.uuid +"_"+attach.fileName);
		            
					str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"'data-type'"+attach.fileType+"'><div>";
					str += "<img src='/apDisplay?fileName="+fileCallPath+"'>";
					str += "</div>";
					str += "</li>";
				}
			});
			
			$(".upload ul").html(str);
	
		});
	
	})();
	
	//첨부 파일 올라와 있는 이미지 지우기
	$(".uploadResult").on("click", "button" , function(e){
	
		console.log("delete file");
		
		var targetLi = $(this).closest("li");
		
		targetLi.remove();
		
	}) ;
	
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
	        	str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'> x </i></button><br>";
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
	
	var formObj = $("form");
	
	$('button').on("click", function(e){
		
		e.preventDefault();
		
		var operation = $(this).data("oper");
		
		if(operation === 'cancle'){
			
			formObj.attr("action", "/creator/remove");
			
		}
		
		formObj.submit();
		
	});
	
});

</script>
</html>