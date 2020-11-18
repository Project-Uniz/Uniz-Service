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
	background-color: orange;
	position: absolute;
	left: 0px;
	top: 0px;
	list-style: none;
	display: inline-block;
}
.main{
	font-size : 100px;
	font-weight: bold;
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
	/* color: white; */
	color: white;
	font-size: 30px;
	
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
	top: 30px;
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
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	

	<div class="inner-div-800">

		<div class="keyword-align-center">
			<div class="keyword-content">
				<input type="text" id="" name="" size="100">
				<button>옵션</button>
				<br>
				<p>
					<button>키워드1</button>
					<button>X</button>
				</p>
				<p>
					<button>키워드1</button>
					<button>X</button>
				</p>
				<p>
					<button>키워드1</button>
					<button>X</button>
				</p>
				<p>
					<button>키워드1</button>
					<button>X</button>
				</p>
				<p>
					<button>키워드1</button>
					<button>X</button>
				</p>
				<p>
					<button>키워드1</button>
					<button>X</button>
				</p>

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



	<script src="/resources/unizHit.js"></script>

	<script>
		$(document).ready(function() {
			getList();

		});
	</script>


</body>
</html>
