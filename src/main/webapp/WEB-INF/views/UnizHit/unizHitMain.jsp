<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
</head>
<style>

.navbar {
	width: 100%;
	height: 20%;
	background-color: black;
	position: absolute;
	left: 0px;
	top: 0px;
	list-style: none;
	display: inline-block;
}
.mainlogo{
	font-size : 100px;
	font-weight: bold;
	color : white;
}

.navbar ul {
	display: flex;
}

.navbar ul li {
	list-style: none;
	padding: 15px;
}

.navbar ul li a {
	text-decoration: none;
	padding: 12px 16px;
	color: white;
	font-size: 20px;
	
	/* background-color: rosybrown; */
}

span {
	position: absolute;
	right: 0;
}

span ul {
	display: inline-block;
}

.wrap {
	width: 100%;
	height: 100%;
	
}

.inner-div-800 {
	width: 820px;
	
}

.inner-div-1200 {
	width: 90%;
	height: 100%;
	
	
}

.keyword-align-center {
	position: relative;
	height: 100px;
	top: 50%;
	left: 50%;
	border: 1px solid black;
	padding-left: 20px;
}

.keyword-content input {
	margin-top: 20px;
	margin-bottom: 10px;
	height: 30px;
}

.keyword-content p {
	display: inline;
}

.video-align-center {
	position: relative;
	height: 100px;
	top: 50%;
	left: 10%;
	margin-top: 80px;
	padding-left: 20px;
}

.video-align-center img {
	width: 250px;
}
</style>

<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<div class="wrap">

	<div class="inner-div-800">

		<div class="keyword-align-center">
			<div class="keyword-content">
				<input type="text" id="keyword" placeholder="keyword" size ="100" readonly><br>
				<div id="unitags"></div>
			</div>


		</div>
	</div>

	<div class="inner-div-1200">
		<div class="video-align-center">
			<br>

			<!--리스트 5개 반복되는 구조 -->
			<div id="hit"></div>

			<!--end -->


		</div>
	</div>
</div>
	<script src="/resources/js/unizHit.js"></script>
	<script src="/resources/js/unizAjax.js"></script>

	<script>
		$(document).ready(function() {
			getList();

		});
	</script>
	
<script type="text/javascript">
$(document).ready(function() {
	
	let searchMenuNum = 5;

	setUnitagsByPreset(searchMenuNum, 13);
	
	function setUnitagsByPreset(menu, limit) {
		unizService.getPreset(
				{menu : menu},
				function(result) {
					let keys = [];

					let tagsHTML = ""; 

					//tagsHTML += "<p><button id='tag' name='unitags' value='야구'>야구</button></p>"
					for(let idx = 0 ; idx<limit ; idx++) {
						let uniz = result[idx];
						tagsHTML += "<p><button id='tag"+ idx +"' name='unitags' value='" + uniz.unizKeyword + "'>"  + uniz.unizKeyword + "</button></p>"
						keys.push(uniz.unizKeyword);
					}

					$("#unitags").html(tagsHTML);

					$("button[name='unitags']").each(function(i){
						$(this).click(function(e){
							e.preventDefault();
							$("#keyword").val($(this).val());
						})
					});
				}
			);
	}
});
</script>
</body>
</html>
