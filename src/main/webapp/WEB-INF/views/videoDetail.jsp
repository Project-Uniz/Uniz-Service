<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
    
    .inner-div{
        width: 800px;

    }

    .video-container {
        
        position: relative;
        height: 0;
        padding-bottom: 56.25%;
        top:50%;
        left:50%;
    }
    
    .video-container iframe {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
    }
    .video-text {
        
        position: relative;
        height: 0;
        top:50%;
        left:50%;
        
    }
    </style>
</head>
<body>
    <div class="">

        <div class="inner-div">
            
            <div class="video-container">
                <iframe id="videoPlay" width="600" height="315" src="//www.youtube.com/embed/${videoData.urlPath}" frameborder="0" allowfullscreen></iframe>
                
            </div>

                    <div class="video-text">
                        <p>${videoData.title}</p>
                        <p>게시자닉네임${videoData.authorNick}</p>
                        <p>조회수 :${videoData.viewCnt}회</p> <p>좋아요 : ${videoData.likeCnt}개</p> 
                        <p>업로드일:${videoData.createDateTime}</p>
                        
                        
                        <form id="form">
	                        <input type="hidden" id="duration" name ="" value="${videoData.duration}">
	                        <input type="hidden" id="videoSN" name ="videoSN" value="${videoData.videoSN}">
	                        <input type="hidden" id="unizSN" name ="unizSN" value="${videoData.utbCateSN}">
	                        <input type="hidden" id="userSN" name ="userSN" value="${userSN}">
                        </form>
                        
                    </div>

       		 </div>
    
</div>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>

<script>
	$(document).ready(function(){
		
		let d = new Date();
		
		//페이지 시작시 타이머를 돌린다.
		let startTime = d.getTime();
		
		//전체 영상의 시간 
		let duration = $("#duration").val();
		console.log(duration);
		//전체 영상 길이의 20%
		let min_duration = duration * 0.20;
		
		let test = 5;
		
		console.log("start : " + startTime);
		
		//1. 머문시간이 전체 영상시간의 20프로가 넘으면 유니즈 포인트 증가
		setTimeout(getTimeCal, 1000 * min_duration);  
		
		function getTimeCal(){
			
			let ds = new Date();
			let endTime = ds.getTime();
			console.log("endTime" + endTime);
			
			let calTime = (endTime - startTime)/1000;
			
			let Form = $("#form").serialize()
			console.log("codeForm: "+Form);
			
			$.ajax({
				type : "POST",
				url : "/uniz/addMypoint",
				data : Form,
				dataType: "json",
				success: function(data){
					const SUCCESS = "SUCCESS";
					if(data.result == SUCCESS){
						console.log("전체영상의 길이 : " + duration + "  영상의 20% :"+ min_duration+"초를 을 시청하여 UnizPoint를 증가");
					}else{
						console.log("등록 실패")
					}
				}
			})
		}
		
		//2. 
		window.onbeforeunload = function(){
			
			let videoSN = $("#videoSN").val();
			let userSN = $("#userSN").val();
			
			// 페이지를 떠나기전 지금까지 본 영상을 저장
			let ds = new Date();
			let endTime = ds.getTime();
			
			let currentTime = Math.round(parseInt((endTime - startTime)/1000));
			console.log(currentTime);			
			
			$.ajax({
				type : "POST",
				url : "/user/addHistory",
				data : {videoSN : videoSN, userSN : userSN, currentTime : currentTime},
				dataType: "json",
				success: function(data){
					const SUCCESS = "SUCCESS";
					if(data.result == SUCCESS){
						console.log("전체영상의 길이 : " + duration + "  영상의 20% :"+ min_duration+"초를 을 시청하여 UnizPoint를 증가");
					}else{
						console.log("등록 실패")
					}
				}
			});
			
		};
	});
</script>
</body>
</html>