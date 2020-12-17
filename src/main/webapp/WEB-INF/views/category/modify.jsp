<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

	width : 50%;
	
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


게시글 수정 페이지


<form action='/category/modify' method='post' id='boardPost' enctype="multipart/form-data" >

</form>

		<div>Files</div>
			
			<div class="uploadResult">
				<ul>
				</ul>
			</div>
			
			<div class="form-group uploadDiv">
				<input type="file" name="uploadFile" multiple="multiple">
			</div>
			<br>
			
<button type='submit' data-oper='modify'>게시글 수정</button>
<button type='submit' data-oper='remove'>게시글 삭제</button>
<button type='submit' data-oper='list'>글 목록으로</button>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/category.js"></script>	
<script type="text/javascript">

$(document).ready(function(){
	var postSN = '<c:out value="${postSN}"/>';
	var boardSN = '<c:out value="${boardSN}"/>';
	
	categoryService.modify({postSN : postSN , boardSN : boardSN});
	
	
	var formObj = $("form");
	$('button').on("click", function(e){
		e.preventDefault();
		var operation = $(this).data("oper");
		console.log("oper : " + operation);
		
		if(operation === 'remove'){
			
			if(confirm("정말 삭제 하시겠습니까?")){
			formObj.attr("action" , "/category/remove");
			}
			
		}else if (operation === 'list'){
			self.location="/category/board/" + boardSN;
			
		}
		else if(operation === 'modify'){
	        
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
	        formObj.append(str).submit();
        }
		formObj.submit();
	});

});

</script>
<script>

$(document).ready(function(){
	
	(function(){
		
		var postSN = '<c:out value="${postSN}"/>';
		
		$.getJSON("/category/getAttachList", {postSN: postSN}, function(arr){
		
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
		            str += "<img src='/display?fileName="+fileCallPath+"'>";
		            str += "</div>";
		            str +"</li>";
				}
			});
			
			$(".uploadResult ul").html(str);
	
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
			    showUploadResult(result); //업로드 결과 처리 함수 
	     }
	 }); //$.ajax
	    
 });  
	
	var formObj = $("form");
	
	$('button').on("click", function(e){
		
		e.preventDefault();
		
		var operation = $(this).data("oper");
		
		if(operation === 'remove'){
			
			formObj.attr("action", "/category/remove");
			
		}
		
		formObj.submit();
		
	});
	
});

</script>
</body>
</html>