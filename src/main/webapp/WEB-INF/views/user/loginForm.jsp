<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="/resources/css/RegisterLogin.css">
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<title>Uniz로그인페이지</title>
</head>
<body>	

<div class="bg">
    <div class="login-box">
        <form action="/user/loginForm" method="post" onsubmit="return checkForm();">
        <h1 class="loginHeader">
            <svg xmlns="http://www.w3.org/2000/svg" width="119" height="90" viewBox="0 0 1319 554">
                <defs>
                  <style>
                    .cls-1 {
                      writing-mode: tb;
                      glyph-orientation-vertical: 0;
                      font-size: 729.167px;
                      fill: #00a6f3;
                      font-family: Futura;
                      font-weight: 500;
                    }
                  </style>
                </defs>
                <text id="uniz" class="cls-1" transform="translate(-50.515 268.411) rotate(-90)">uniz</text>
              </svg>
              <p class="regIntro">취향저격 유튜브 서비스</p>
        </h1>
        <div class="textBox">
            <i class="fa fa-user"></i>
            <input type="text" id="userId" name="userId" placeholder="아이디"><br>
        </div>

        <div class="textBox">
            <i class="fa fa-lock"></i>
            <input type="password" id="password" name="password" placeholder="비밀번호">
        </div>

        <div class="cookieBox">
            <input type="checkbox" id="chk" name="chk">
            <label for="chk">아이디 기억하기</label>
        </div>
        <div class="regLink">
        
        </div>

        <div class="btnBox">
            <button class="logBtn"type="submit">로그인</button>
            <button type="button" class="logBtn regBtn" onclick="location.href='/user/register'">회원가입</button>
        </div>
        
        </form>
    </div>
</div>
	
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script type="text/javascript">
	//테스트용 아이디 비밀번호 고정
		$("#userId").val("test0003");
		$("#password").val("1234");
		
		
	</script>
	</body>
</html>