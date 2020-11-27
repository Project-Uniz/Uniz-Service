
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
		
		//review #20201125 코드섞지않기.
	 	data = data || [];		
			
			
		//data.length를 5개씩 나눠서 보여주기;
			for (var i = 0, len = data.length ; i < len ; i++) {
				console.log(data[1].videoSN);
			
				if(i == 0 || i % 5 == 0){
				
				str += "<h3>"+"주제별 영상"+(i+1)+"~"+(i+5)+"</h3>";
		
				}
				
				str += "<a href= '"+data[i].videoSN+"'><img src='"+data[i].thumbUrl+"'></a>";
				
			}
			
			hitdiv.html(str);

		}
	})
		
}
