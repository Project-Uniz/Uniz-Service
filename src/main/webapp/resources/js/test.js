function getAllPost(){
		
		var str = "";
		var allPostList = $("#allPostList");
		$.ajax({
			type : 'get',
			url : "/channel/list/all",
			dataType : 'json',
			contentType : "application/json; charset=utf-8",
			success : function(list){
				
				str += "<thead><tr><th>채널명</th><th>글 제목</th><th>작성자</th><th>작성 일</th></tr></thead>"
				
					for (var i = 0, len = list.length || 0; i < len; i++){
					
					str += "<thead><tr><th>"+list[i].channelTitle + "</th>";
					str += "<th>"+list[i].title + "</th>";
					str += "<th>"+list[i].nick + "</th>";
					str += "<th>"+displayTime(list[i].createDateTime) +"</th></tr></thead>";
					
				}
				allPostList.html(str);
			}
		});
		
	}