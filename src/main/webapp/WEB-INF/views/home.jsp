

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- <link rel="stylesheet" href="grid-guide.css"> -->
    <title>Document</title>
    <!-- <link href='http://fonts.googleapis.com/css?family=Paytone+One' rel='stylesheet' type='text/css'> -->
    
    <!-- <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css"> -->
    <!-- 위에거 내가 폰트어썸사이트 가서 찾은 링크 렐인데 이거 써도 아이콘이 유지된다. 배치가 쪼오금 다른 것 같긴한데... 거의 같애 썅  -->
    <!-- <link rel="stylesheet" type="sample/css"> -->

    <!-- <link href="http://netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.css" rel="stylesheet"> -->
</head>
    <style>
        
      
    
    
  

    </style>
  </head>
  <body>
    
		
	<c:choose test="${sessionScope.userId==null }">
		<c:when test="${sessionScope.userId==null }">
                        <li><a href="/user/register" >Register</a></li>
                        
                        
                        <li><a href="/user/loginForm" >Login</a></li>
                       </c:when>
        <c:otherwise> 
                        
                        <li><a href="/user/logout" >Logout</a></li>
		</c:otherwise>
</c:choose>

  </body>
</html>