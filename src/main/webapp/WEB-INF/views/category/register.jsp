<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>

.uploadResult{

	whidth : 100%;
	background-color : gray;

}

.uploadResult ul{

	display : flex;
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


    
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading"><h1>글 쓰기</h1></div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					
					<form role="form" action="/category/register" method="post" onsubmit="return checkTitle()">
						<div class="form-group">
							<label>제목</label>
							<input class="form-control" name='title' id='title'>
						</div>
						
						<div class="form-group">
							<label>내용</label>
							<textarea  class="form-control" name='postContent'></textarea>
						</div>
						
						<div class="form-group">
							
							<input  class="form-control" name='userSN' value=''  >
						</div>
						
						<div class="form-group">
							
							<input type="text" class="form-control" name='boardSN' value="${boardSN}">
						</div>
						
						<div class="uploadResult">
							<ul>
							</ul>
						</div>
						
					<div class="panel-body">	
						<div class="form-group uploadDiv">
							<input type='file' name='uploadFile' multiple>
						</div>
						
					</div>
					<br>
						<button type="submit" class="btn btn-default">작성완료</button>
						<button type="reset" class="btn btn-default">전체 지우기</button>
						
					</form>
				</div>
			</div>
		</div>
	</div>    
				
					
  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/category.js"></script>
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
			
			alert(str);
			
		});
		
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
		        	str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='image' class='btn btn-warning btn-circle'> x <i class='fa fa-times'></i></button><br>";
		        	str += "<img src='/display?fileName="+fileCallPath+"'>";
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
		    	url : '/uploadAjaxAction',
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
			
			url: '/deleteFile',
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