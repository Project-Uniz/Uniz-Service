<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Main</title>
</head>
<style>

.wrap{
    width :100%;
    height: 100%;
}
.inner-div-800{
    width: 820px;
}
.inner-div-1200{
    width: 90%;
    height: 100%;
}

.keyword-align-center {

    position: relative;
    height: 100px;
    top:50%;
    left:50%;
    border: 1px solid black;
    padding-left: 20px;
}

.keyword-content input{
    margin-top: 20px;
    margin-bottom : 10px;
    height: 30px;
}

.keyword-content p{
    display: inline;

}
.video-align-center {

    position: relative;
    height: 100px;
    top:50%;
    left:10%;

    margin-top: 80px;
    padding-left: 20px;
}
.video-align-center img{

    width: 250px;

}
</style>
<body>

<div class="wrap">
	<div class="">
		<h1 style="text-align: center;">header</h1>
	</div>

	<div class="inner-div-800">
		<div class="keyword-align-center">
			<div class="keyword-content">
				<input type="text" id="keyword" placeholder="keyword" size ="100"><button id='btnSearch'>search</button><br>
				<div id="unitags">
				</div>
			</div>
		</div>
	</div>


	<div>
		<form id='searchForm'>
			<input type='number' id='userSN' placeholder="userSN"/>
			<input type='text' id='option' placeholder="options"/><br>
			<button id='btnGetOpt'>GetOpt</button>
			<button id='btnSetOpt'>SetOpt</button><br>
		</form>
	</div>

	<div>
		<text id='bodyText'>
	</div>

	<div class="inner-div-1200">
		<div class="video-align-center">
			<div id='contents'>
			</div>
		</div>
	</div>
</div>


<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>

<!-- Ajax -->
<script type="text/javascript" src="/resources/js/searchAjax.js"></script>
<script type="text/javascript" src="/resources/js/unizAjax.js"></script>

<!-- js Func -->
<script type="text/javascript" src="/resources/js/search.js"/></script>

</body>
</html>
