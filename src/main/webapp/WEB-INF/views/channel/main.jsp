<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
 <link rel="stylesheet" href="/resources/css/Navbar.css">
<style>
*{ box-sizing: border-box;
   margin: 0;
   padding: 0;
   font-family: 'Courier New', Courier, monospace;
}
.navbar{
    width: 100%;
    height: 80px;
    display:flex;
    border-bottom: 2px solid rgb(202, 218, 210);
}
.navbar a{
    text-decoration: none;
}
.nav1{
    width: 80%;
    display: flex;
    align-items: center;
}
.nav1 ul li {
    list-style-type: none;
}
.nav1 ul li a{
    text-decoration: none;
    font-size: 16px;
    padding: 10px 20px;
    font-weight: 500;
    border-radius: 12px;
    /* color: rgb(71, 71, 71); */
    color: #504f5c;
}
.nav1 ul li a:first-child{
    margin-left: 15px;
}
.nav1 ul li a:hover {
    /* background-color: rgb(214, 238, 245, 0.6); */
    color: rgb(14, 0, 143);
    transition: 0.4s;
}
.nav1 ul li a:last-child{
    padding-right: 40px;
}
.logo{
    margin-left: 10px;
}
.font{
    margin-left: 8px;
}
.searchIcon{
    position: absolute;
    top: 20px;
}
.nav2{
    /* background-color: lightblue; */
    width: 20%;
    display: flex;
    justify-content: flex-end;
    align-items: center;
}
.btn{
    background-color: rgb(61, 186, 218);
    color: white;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    padding: 10px 16px;
    margin-right: 20px;
    border-radius: 9px;
    font-size: 16px;
    border: none;
    outline: none;
}
.btn:first-child{
    background-color: white;
    color:rgba(61, 69, 71, 0.6);
}
.btn:first-child:hover{
    color: rgb(59, 58, 58);
    transition: 0.4s;
}

/* 커뮤니티입니당.  */
.communityPage{
    background-color: rgb(250, 253, 255);
    width: 90%;
    height: 1100px;
    margin: 0 auto;
    display: flex;
}
.commuNavbar{
    background-color: white;
    width: 225px;
    height: 100%;
}
.BtnBox{
    /* background-color: magenta; */
    width: 100%;
    height: 300px;
    padding: 30px 0;
}
.comBtn{
    display: block;
    margin-left: 30px;
    padding: 10px 20px;
    border-radius: 20px;
    font-size: 19px;
    border: none;
    outline: none;
}
.comBtn:last-child{
    margin-top: 10px;
}
.comBtn:hover{
    background-color: rgb(223, 243, 251);
    color:rgb(0, 132, 209);
    transition: 0.3s;
}

.channelListBox{
    /* background-color: darkorange; */
    width: 85%;
    height: 100%;
    border-left: 2px solid #dbdbdb;
    border-right: 2px solid #dbdbdb;
}
.header{
    /* '채널 게시판' */

    font-size: 28px;
    padding: 35px  30px 20px 35px;
    font-weight: 500;

}
.chP{
    font-size: 22px;
    padding: 8px 10px;
    color: rgb(219, 236, 255);
    background-color: dodgerblue;
    width: 90%;
    margin: 0 auto;
}
.chP:nth-child(5){
    border-top : 40px solid rgb(250, 253, 255);
}
.Chlist ul li{
    list-style-type: none;
}
.Chlist ul li a{
    text-decoration: none;
    display: block;
    padding: 5px  20px;
    background-color: rgb(255, 255, 255);
    width: 90%;
    margin: 0 auto;
    font-size: 18px;
    border-bottom: 1px solid rgb(189, 187, 187);
    color: rgb(72, 73, 141);

}
.Chlist ul li a:last-child{
    border-bottom: none;
}
.Chlist ul li a:hover{
    background-color: rgb(245, 255, 255);
    transition: 0.3s;
    color: rgb(1, 155, 216);
    border-left: 6px solid rgb(25, 155, 231);
}
.allPostList{
    background-color: rgb(236, 246, 255);
    width: 90%;
    margin: 0 auto;
    border-collapse: none;
    border-spacing: 0;
    /* border-spacing을 0으로 줘야 정체모를 세로 공간(줄무늬 같은거)이 사라진다.  */
    cursor: pointer;
}
.allPostList th{
    padding: 6px 0;
    background-color: rgb(166, 231, 217);
    width: 100%;
}
.allPostList th, td, {
		width: 25%;
}
.allPostList tr:nth-child(even){
    background-color: rgb(247, 253, 247);
}
.allPostList td, th, tr, thead{
    border: none;
    
       
    
}
.allPostList td{
    padding: 10px 10px;
    color: rgb(41, 30, 30);
}
.allPostList tr:hover, td:hover{
    /* background-color:  rgb(216, 231, 216); */
    color: rgb(21, 9, 128);
    transition: 0.4s;
}
.postFooter{
    margin-top: 50px;
    /* background-color: hotpink; */
    width: 100%;
    height: 10px;
    display: flex;
    justify-content: center;
}
.postFooter ul li{
    list-style-type: none;
}
.postFooter ul li a{
    text-decoration: none;
    border: 1px solid rgb(196, 180, 216);
    border-collapse: none;
    padding: 10px 12px;
    font-size: 16px;
    color: dodgerblue;
}
.postFooter ul li a:hover{
    color: white;
    background-color: rgb(133, 218, 251);
    transition: 0.3s ease-in-out;
}
.postFooter ul li a:first-child{
    border-radius: 20px 0 0 20px;
}
.postFooter ul li a:last-child{
    border-radius: 0 20px 20px 0;
}
.postFooter ul li a:not(:last-child){
    border-right: none;
}
.footer{
    background-color: rgb(59, 150, 241);
    width: 100%;
    height: 200px;
    display: flex;
    
}
.footer1{
    /* background-color:yellow; */
    width: 30%;
    height: 100%;
    padding: 40px 10px;
    margin: 0 auto;
}
.footer2{
    /* background-color: yellowgreen; */
    width: 20%;
    height: 100%;
    padding: 40px;
}
.footer3{
    /* background-color: aquamarine; */
    width: 50%;
    height: 100%;
    padding: 40px 16px;
}
.footer1, .footer2, .footer3{
    color: white;
}
</style>

</head>
<body>

	<%@ include file="/WEB-INF/views/includes/nav.jsp"%>


    <div class="communityPage">
        <div class="commuNavbar">
            <div class="BtnBox">
                <button class="comBtn" id="boardPost">카테고리별 게시판</button>
                <button class="comBtn" id="createChannel">채널 게시판 신청</button>
                <button class="comBtn" id="registerCreator">크리에이터 등록</button>
            </div>
        </div>
        <!-- navbar -->

        <div class="channelListBox">
        <h1 class="header"> 채널 게시판 </h1>

        <p class="chP">채널 목록</p>
        <div id = "channelList" class="Chlist">
        </div>
        <div></div>
        <p class="chP">전체 게시글 목록</p>
        <div></div>
        <table class="allPostList"></table>
  

        <div class="postFooter">
            <!-- pagination -->
            
        </div>
    </div>
    <!-- channelListBox end -->

    </div>
    <!-- comMain end -->

    <div class="footer">
    </div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/channel.js"></script>
<script type="text/javascript" src="/resources/js/channelPaging.js"></script>

</body>
</html>