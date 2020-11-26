
function getList( ){
	console.log("실행");
	
	var str ="";
	var hitdiv = $("#hit");
	
	//ajax 호출
	$.ajax({
		type : 'get',
		url : "/UnizHit/Hitlist",
		dataType : 'json',
		contentType : "application/json; charset=utf-8",
		success : function(data){
			
			
			
		//data.length를 5개씩 나눠서 보여주기;
			for (var i = 0, len = data.length || 0 ; i < len ; i++) {
			
				if(i == 0 || i % 5 == 0){
				
				str += "<h3>주제별 영상</h3>";
		
				}
				
				str += "<a href= '"+data[i].videoSN+"'><img src='"+data[i].thumbUrl+"'></a>";
			
			}
			
			hitdiv.html(str);

		}
	})
		
}
