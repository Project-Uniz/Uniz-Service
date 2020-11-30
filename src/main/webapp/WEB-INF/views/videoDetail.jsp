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
                <iframe width="600" height="315" src="//www.youtube.com/embed/${videoData.urlPath}" frameborder="0" allowfullscreen></iframe>
                
            </div>

                    <div class="video-text">
                        <p>${videoData.title}</p>
                        <p>게시자닉네임${videoData.authorNick}</p>
                        <p>조회수 :${videoData.viewCnt}회</p> <p>좋아요 : ${videoData.likeCnt}개</p> 
                        <p>업로드일:${videoData.createDateTime}</p>
                    </div>

       		 </div>
    
</div>
</body>
</html>