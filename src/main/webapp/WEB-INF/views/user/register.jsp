<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Uniz-회원가입페이지</title>
	<link rel="stylesheet" href="/resources/css/RegisterLogin.css">
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
</head>
<body>

<div class="bg">
    <div class="register-box">
        <form action="/user/register" method="post">
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
       
        <div class="regTextBox">
            <p class="regHeader"><i class="fa fa-user"></i>아이디</p>
            <input type="text" id="userId" name="userId" placeholder="아이디를 입력하세요" size="70">
            <button type="button" class="duplicateBtn" id="userIdCheckBtn">중복확인</button>
        </div>
        
        <div class="regTextBox"><p class="regHeader"><i class="fa fa-lock"></i>비밀번호</p>
            <input type="password" id="password" name="password" placeholder="비밀번호를 입력하세요">
        </div>
    
        <div class="regTextBox">
            <p class="regHeader">비밀번호 확인</p>
            <input type="password" id="password2" name="password2"placeholder="">
        </div>
    
        <div class="regTextBox">
            <p class="regHeader"><i class="far fa-laugh-squint"></i>닉네임</p>
            <input type="text" id="nick" name="nick" placeholder="닉네임을 입력하세요" size="70">
            <button type="button" class="duplicateBtn" id="userNickCheckBtn">중복확인</button>
        </div>
        
        <div class="btnBox">
            <button type="button" class="logBtn regBtn" id="myBtn"onclick="">다음</button>
            <button class="logBtn" type="reset">취소</button>
        </div>
        
        
    </div> 
    
    <div id="myModal" class="modal">
        <div class="modal-content">
            <div class="chkListBox">
                <div class="chkBox">
                    <c:forEach items="${PresetList}" var="preset">
			            <input type="checkbox" name="unizSN" value="${preset.unizSN}"><label for="">${preset.unizKeyword}</label><br>
		            </c:forEach>
                </div>

            </div>
            
            <input type ="hidden" name="state" value="1">
            <div class="btnBox">
                <button class="logBtn regBtn" type="submit">완료</button>
            </div>
        </div>
      
      </div>
    </form>
</div>
	
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script>
    // Get the modal
    var modal = document.getElementById("myModal");
    
    // Get the button that opens the modal
    var btn = document.getElementById("myBtn");
    
    
    // When the user clicks the button, open the modal 
    btn.onclick = function() {
        modal.style.display = "block";        
    }
        
    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
      if (event.target == modal) {
        modal.style.display = "none";
      }
    }

    $(document).ready(function(){
		
		$('#userIdCheckBtn').click(function(){
			
			if($("#userId").val()==""){
			alert("중복체크할 아이디를 입력해 주세요");
			return ;
			}
		
		$.ajax({
			type : "POST",
			url : "/user/userIdCheck",
			data : $("#userId").val(),		
			contentType : "application/json; charset=UTF-8",
			dataType: "json",
			success : function(data){
				
				const SUCCESS = "SUCCESS";
				const DUPLICATION = "DUPLICATION";
				
				if(data.result == SUCCESS){
					alert("사용할 수 있는 아이디 입니다.")
					$("#userId").attr("readonly", "true");
					$("#userIdCheckBtn").attr("disabled", "true");
				}else if(data.result == DUPLICATION){
					alert("이미 존재하는 아이디 입니다.")	
				}else{
					alert("데이터 입력 중 오류가 발생하였습니다.\n입력한 정보를 다시 확인해 주세요.");
				}			
			}
		});
	});
	
	$('#userNickCheckBtn').click(function(){
		
		if($("#nick").val()==""){
			alert("중복체크할 닉네임를 입력해 주세요");
			return ;
		}
		console.log("중복확인버튼클릭");

		$.ajax({
			type : "POST",
			url : "/user/userNickCheck",
			data : $("#nick").val(),		
			contentType : "application/json; charset=UTF-8",
			dataType: "json",
			success : function(data){
				
				const SUCCESS = "SUCCESS";
				const DUPLICATION = "DUPLICATION";
				if(data.data == SUCCESS){
					alert("사용할 수 있는 닉네임 입니다.")
				}else if(data.data == DUPLICATION){
					alert("이미 존재하는 닉네임 입니다.")	
				}else{
					alert("데이터 입력 중 오류가 발생하였습니다.입력한 정보를 다시 확인해 주세요.");
				}			
			}
		});			
	});
});
</script>

</body>
</html>