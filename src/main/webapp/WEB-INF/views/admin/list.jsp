<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>크리에이터 신청 목록</h1>
	
	<div class="list">
		
	</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>

	$(document).ready(function(){
		
		console.log("test");
		
		var applyList = $(".list");
		var str = "";
		
		$.ajax({
			
			type : 'get',
			url : '/creator/getAllApply',
			dataType : 'json',
			contentType : "application/json; charset=UTF-8",
			success : function(list){
				
				str += "<thead><tr><th>채널명</th><th>신청자 번호</th>";
				str += "<th>카테고리</th><th>이메일</th><th>신청상태</th><th>등록 날짜</th></tr></thead>";
					
				for (var i = 0, len = list.length || 0; i < len; i++){
					
					str += "<thead><tr><td>"+list[i].channelTitle + "</td>";
					str += "<td><a href='/admin/detail/"+list[i].userSN+"'>"+list[i].userSN+"</a></td>";
					str += "<td>"+list[i].category + "</td>";
					str += "<td>"+list[i].email +"</td>";
					str += "<td>"+list[i].state +"</td>";
					str += "<td>"+list[i].createdatetime +"</td>";
					str += "</tr></thead>";
					
				}	
				
					applyList.html(str);
				
			}
			
		});
		
	});
	
</script>
</body>
</html>