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
        
        *{
            font-family: '맑은 고딕', 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            border: 0;
        }

      video { max-width: 100%;  display: block; margin: 0px auto; }
      
      .videoContainer{
          position:relative;
          height: 100px; 
          overflow:hidden;
      }
      
.box11 {
  position: relative;
  background-color: black;
          width: auto;
          height: 240px;
          }

/* Slides */
.mySlides {
  display: none;
  padding: 0;
  
  /* text-align: center; */
}
.slideBox{
    width: 100%;
    height: 100%;
    opacity: 0.8;
    
    
    

}
.slideBox  p{


    width: 17%;
    height: 100%;
    /* display: inline-flex; */
    display: inline-block;
    margin-top: 50px;
    margin-left: 13px;
    margin-right: 13px;
    opacity: 1;


   
}



/* Next & previous buttons */
.prev, .next {
  cursor: pointer;
  position: absolute;
  top: 50%;
  width: auto;
  margin-top: -30px;
  padding: 16px;
  color: #888;
  font-weight: bold;
  font-size: 20px;
  border-radius: 0 3px 3px 0;
  user-select: none;
}

/* Position the "next button" to the right */
.next {
  position: absolute;
  right: 0;
  border-radius: 3px 0 0 3px;
}

/* On hover, add a black background color with a little bit see-through */
.prev:hover, .next:hover {
  background-color: rgba(0,0,0,0.8);
  color: white;
}
      .navbar{
          width: 100%;
          height: 50px;
          /* background-color: skyblue; */
          position: absolute;
          left: 0px;
          top: 0px;
          list-style: none;
          display: inline-block;

      }
      
      .navbar ul{
          display: flex;
          
          
      }
      .navbar ul li{
          list-style: none;
          padding: 15px;

      }
      .navbar ul li a{
        text-decoration: none;
          padding: 12px 16px;
          color: black;
          font-size: 20px;
           /* background-color: rosybrown; */
          
      }
      
      span{
          position: absolute;
          right: 0;
      }
      span ul{
          display: inline-block;
      }
      .text{
          color: white;
          font-size: 52px;
          padding:  50px 0px 0px 30px;
          margin: 0;
          box-sizing: border-box;
      }
      .smText{
          font-size: 26px;
      }
      
.box2{
    box-sizing: border-box;
    width:100%;
    height: 600px;
    background-color: tan;
    position:relative;
}
.box3{
    background-color: tomato;
    position: absolute;
    width: 70%;
    left: 0;
    height: 60%;
}
.box4{
    /* background-color: violet; */
    background-color: white;
    position:absolute;
    top: 60%;
    width: 70%;
    left: 0;
    height: 40%;
    
}
.box5{
    /* background-color: turquoise;
     */
     
     background-color:rgb(15, 31, 32);
     color: rgb(234, 236, 255);
    position:absolute;
    right: 0;
    width: 30%;
    height: 100%;
}
.imgbox{

    width: 80%;
    height: 80%;
    /* background-color: chartreuse; */
    margin: auto;
    float:inline-end;
    overflow: auto;
}
.imgbox ul{
    
    list-style-type: none;
    
    margin-top: 10px;

}
/* .imgbox ul li{
    
    position: absolute;
    width: 100%;
    height: auto;    
   
}  */
 .imgbox p img{
    width: 100%;
    margin-bottom: 10px;
    opacity: 0.6;


} 
.imgbox p img:hover{
    opacity: 1;
}
.banner{
    position:absolute;
    top: 30px;
    left: 20px;
    font-size: 30px;
    color:whitesmoke;

    /* background-color: black; */
}
.box6{
    /* background-color: yellowgreen; */
    width: 100%;
    height: 40%;
    position:absolute;
    top: 45%;
}
.box7{
    display:flex;
    justify-content: space-around;
    margin: 30px 0 10px 0;
    height: 60%;
    overflow: auto;
}
.items{
    background-color: rgb(19, 224, 243);
    border-radius: 30px;
    width: 15%;
    height: 100%;
}
.items div{
    text-align: center;
    margin: 20px 0 20px 0;
}
.words p{
    font-size: 35px;
    z-index: 3;
    text-align: center;
    color: #000000;
    font-weight: bold;
    padding-top: 20px;
}
.community{
    width: 100%;
    height: 290px;
    background-color: gold;
    position: relative;
}
.cmBox{
    position: relative;
}
.commu{
    position: absolute;
    
    right: 450px;
    
}
.commu li a{
    text-decoration: none;
    color: black;
    margin: auto;
}
.commu li{

    list-style-type: none;
}
.commu h1{
    padding: 30px  0 30px 0;
}
.buttonBox{
    position: absolute;
    left: 40%;
    top: 70%;
}
#button{
    padding: 20px 35px;
    background-color: orange;
    border-radius: 20px;
    font-size: 20px;
    font-weight: bold;
    border: 0;
    outline: 0;
    
}
.footer{
    background-color: black;
    width: 100%;
    height: 200px;
    overflow: hidden;
    position:relative;
}
.f1{
    background-color: indigo;
    width:50%;
    height: 100%;
    position:absolute;
    left:0;
}
        
.f2{
    background-color: lightblue;
    width:50%;
    height: 100%;
    position: absolute;
    right: 0;

}
#iframe iframe{
    position:absolute;
    top: 80px;
}

    </style>
  </head>
  <body>
    
	<div class="videoContainer">
		
	
        <div class="navbar">
            <ul>
                <li><a href="iframe" target ="trg" >Home</a></li>
                <li><a href="/UnizHit/UnizHit" target="trg">인기</a></li>
                <li><a href="#">추천</a></li>
                <li><a href="/search/index" target="trg">검색</a></li>
                <li><a href="/board/list" target="trg">커뮤니티</a></li>
         
                <span>
                    <ul>
                        <li><a href="/user/register" target="trg">Register</a></li>
                        <li><a href="/user/loginForm" target="trg">Login</a></li>
                        <li><a href="/user/loginForm">Logout</a></li>
                        <li><a href="#">icon</a></li>
                        <li><a href="#">icon2</a></li>

                    </ul>
                </span>
            </ul>
		
            
         </div>   
            
            
        </div>

    <div id="iframe">
        <iframe src="/maincontent" name="trg"  frameborder="0" style="width: 100%; height: 100%;"></iframe>
    </div>

  </body>
</html>