
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
</head>
<style>
@import
	url(https://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300);

* {
	font-family: 'Source Sans Pro', sans-serif;
	box-sizing: border-box;
	padding: 0;
	border: 0;
	margin: 0;
}

body {
	width: 100%;
	height: 500px;
	/* background-color: rgba(61, 114, 52, 0.4); */
}

.box {
	display: flex;
	align-items: center;
	justify-content: center;
	margin-top: 90px;
}

.container {
	position: relative;
	width: 300px;
	height: 400px;
	border-radius: 20px;
	background: linear-gradient(to top, rgb(255, 230, 149), rgb(99, 203, 99));
	background-repeat: no-repeat;
}

.form {
	position: absolute;
	top: 90px;
	left: 15%;
	padding: 20px 10px;
	padding-right: 90px;
	border: none;
	border-radius: 15px;
	opacity: 0.8;
	outline: none;
}

::placeholder {
	color: rgb(128, 182, 90);
}

.form2 {
	position: absolute;
	top: 180px;
}

.btn {
	position: absolute;
	top: 280px;
	left: 75px;
	outline: none;
	background-color: inherit;
	color: rgb(128, 182, 90);
	font-size: 30px;
	text-align: center;
	padding: 10px 30px;
	padding-top: 5px;
	border: 2px solid white;
	border-radius: 10px;
}

.p {
	font-size: 30px;
	font-weight: 800;
	text-align: center;
	padding: 28px;
	color: white;
}

.form3 {
	font-family: inherit;
	color: green;
	position: absolute;
	top: 250px;
	left: 110px;
}

.check {
	position: absolute;
	top: 255px;
	left: 93px;
}

#box {
	cursor: pointer;
}

input[id="box"]+label {
	border: 2px solid inherit;
}
/* message css */
#message {
	/* 패스워드 칸을 클릭하면 패스워드 설정시 충족시켜야 하는 조건들이 나타나는데 조건들을 담는 컨테이너입니다.  */
	display: none;
	background: #f1f1f1;
	color: #000;
	position: relative;
	/* 안의 메세지들을 고려해서 positon: relative; 로 지정합니다.  */
	padding: 20px;
	margin-top: 10px;
}

#message p {
	/*  메세지들 스타일.  */
	padding: 10px 35px;
	font-size: 18px;
}

/* Add a green text color and a checkmark when the requirements are right */
.valid {
	color: green;
	/* 이걸 보기 전에 아래의 .valid:before를 먼저 보시는 게 좋습니다. :before는 ~이전에 ~내용이 있어
  라는 뜻으로, 해석하자면 원래 V표시가 있는데~ 조건을 충족하면(valid) V가 녹색이 되는 거야~ 느낌입니다. */
}

.valid:before {
	position: relative;
	left: -35px;
	content: "✔";
}

/* Add a red text color and an "x" when the requirements are wrong */
.invalid {
	color: red;
}

.invalid:before {
	/* 위 설명과 같습니다. 원래 X표시가 있는데 invalid시 빨간색이 됩니다.  */
	position: relative;
	left: -35px;
	content: "✖";
}
</style>
<body>
	<div class="box">
		<div class="container">

			<div>
				<p class="p">WelCome!!!</p>
			</div>
			<form action="/user/register" method="post"
				onsubmit="return checkForm();">
				<input class="form" type="text" placeholder="userId" id="userId"
					name="userId" required><br> <input class="form form2"
					type="password" placeholder="password" id="password"
					name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
					title="최소 1개 숫자 영어 대문자, 소문자, 8자리 이상 문자를 입력해야 합니다. " required><br>



				<button class="btn" type="submit" onclick="return check();">register</button>

			</form>
		</div>


	</div>

	<!-- 해야할일 : 회원가입을 무사히 마치면 alert창이 뜨도록 하는 것.  -->

	<div id="message">
		<h3>다음의 조건을 충족해주세요.</h3>
		<p id="letter" class="invalid">
			<b>영소문자 최소 1자</b>
		</p>
		<p id="capital" class="invalid">
			<b>영대문자 최소 1자</b>
		</p>
		<p id="number" class="invalid">
			<b>숫자 최소 1자</b>
		</p>
		<p id="length" class="invalid">
			<b>최소 8자로 구성</b>
		</p>
	</div>



	<script type="text/javascript">
		var myInput = document.getElementById("password");
		var letter = document.getElementById("letter");
		var capital = document.getElementById("capital");
		var number = document.getElementById("number");
		var length = document.getElementById("length");

		// <!-- 패스워드 인풋박스를 클릭하면 다음과 같은 메세지들이 하단에 나타납니다.  -->
		myInput.onfocus = function() {
			document.getElementById("message").style.display = "block";
		}

		// 사용자가 패스워드 인풋 박스의 바깥을 클릭하면 조건 메세지들을 안 보이게 감춥니다. 
		myInput.onblur = function() {
			document.getElementById("message").style.display = "none";
		}

		// 유저가 패스워드 인풋박스에 값을 입력하기 시작합니다. 
		myInput.onkeyup = function() {
			// 소문자 유효성 체크. 
			var lowerCaseLetters = /[a-z]/g;
			if (myInput.value.match(lowerCaseLetters)) {
				letter.classList.remove("invalid");
				letter.classList.add("valid");
			} else {
				letter.classList.remove("valid");
				letter.classList.add("invalid");
			}

			// Validate capital letters
			var upperCaseLetters = /[A-Z]/g;
			// 대문자 조건을 충족하면 invalid를 지웁니다. 충족못한다면 valid를 지웁니다. 
			if (myInput.value.match(upperCaseLetters)) {
				capital.classList.remove("invalid");
				capital.classList.add("valid");
			} else {
				capital.classList.remove("valid");
				capital.classList.add("invalid");
			}

			// 숫자 유효성 체크. 
			var numbers = /[0-9]/g;
			if (myInput.value.match(numbers)) {
				number.classList.remove("invalid");
				number.classList.add("valid");
			} else {
				number.classList.remove("valid");
				number.classList.add("invalid");
			}

			// Validate length
			if (myInput.value.length >= 8) {
				// 최소 문자길이가 8자여야 합니다. 
				length.classList.remove("invalid");
				length.classList.add("valid");
			} else {
				length.classList.remove("valid");
				length.classList.add("invalid");
			}
		}

		function myFunction() {
			var x = document.getElementById("password");
			if (x.type === "password") {
				x.type = "text";
			} else {
				x.type = "password";
			}
		}

		function checkForm() {
			//아이디에 대한 참조를 얻어온다. 
			let id = document.getElementById("userId");
			let pwd = document.getElementById("password");

			//value가 ""빈 문자열이면 다음 페이지로 넘어가지 않는다. 
			if (id.value == "") {
				alert("아이디를 입력해주세요. ");
				return false;
			}
			if (pwd.value == "") {
				alert("비밀번호를 입력해주세요.");
				return false;
			}
			return true;
		}

		function check() {

			var str = document.getElementById('userId');

			if (str.value == '' || str.value == null) {
				alert('값을 입력해주세요');
				return false;
			}

			var blank_pattern = /^\s+|\s+$/g;
			if (str.value.replace(blank_pattern, '') == "") {
				alert(' 공백만 입력되었습니다 ');
				return false;
			}

			//공백 금지
			//var blank_pattern = /^\s+|\s+$/g;(/\s/g
			var blank_pattern = /[\s]/g;
			if (blank_pattern.test(str.value) == true) {
				alert(' 공백은 사용할 수 없습니다. ');
				return false;
			}

			var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;

			if (special_pattern.test(str.value) == true) {
				alert('특수문자는 사용할 수 없습니다.');
				return false;
			}

			if (str.value.search(/\W|\s/g) > -1) {
				alert('특수문자 또는 공백을 입력할 수 없습니다.');
				str.focus();
				return false;
			}

			var str2 = document.getElementById('password');

			if (str2.value == '' || str2.value == null) {
				alert('값을 입력해주세요');
				return false;
			}

			var blank_pattern = /^\s+|\s+$/g;
			if (str2.value.replace(blank_pattern, '') == "") {
				alert(' 공백만 입력되었습니다 ');
				return false;
			}

			//공백 금지
			//var blank_pattern = /^\s+|\s+$/g;(/\s/g
			var blank_pattern = /[\s]/g;
			if (blank_pattern.test(str2.value) == true) {
				alert(' 공백은 사용할 수 없습니다. ');
				return false;
			}

		}
	</script>


</body>
</html>