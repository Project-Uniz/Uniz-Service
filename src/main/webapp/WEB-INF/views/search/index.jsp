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
    *{

        font-family: sans-serif;
       }
    
   
    .navbar{
     
      width: 100%;
      height: 20%;
      background-color: rgb(0, 0, 0);
      position: absolute;
      left: 0px;
      top: 0px;
      list-style: none;
      display: inline-block;
      
    }
    
    .mainlogo{
        font-size: 100px;
        font-weight: bold;
        color: white;
    }
    
    .navbar ul{
        display: flex;
        
    
        
        
    }
    .navbar ul li{
        list-style: none;
        padding: 5px;
        
      

  }
  .navbar ul li a{
      text-decoration: none;
      padding: 12px 16px;
      color: white;
      font-size: 20px;
      

      
    }
    /* .body > div.navbar > ul > li:nth-child(2){
      padding-left: 70px;
  } */

 span{
    	position: absolute;
    	right: 0;
    }
    span ul{
    	display: inline-block;
    }
/* navbar style */

.wrap{
    width :100%;
    height: 100%;
}
.inner-div-800{
    width: 820px;
    padding-top: 40px;
    padding-right: 100px;
   /* margin-top: 12%;  */
}
.inner-div-1200{
    width: 90%;
    height: 100%;
}

.keyword-align-center {

    position: relative;
    height: 100px;
    top:50%;
    left:40%;
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

    margin-top: 8px;
    padding-left: 20px;
}
.video-align-center img{

    width: 250px;

}
#btnGetOpt{
    position: absolute;
    left: 48%;
}
#btnSetOpt{
    position: absolute;
    left: 52%;
}
#userSN{

    position: absolute;
    left: 52%;

}
#option{
    
    position: absolute;
    left: 41%;
}
.flex{
    display: flex;

}
.navbar ul li img{
    position:absolute;
    top:0;
}
</style>
<body>
    
    
    <div class="wrap">
        <div class="">
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
    
        <div class="inner-div-1200">
            <div class="video-align-center">
                <div id='contents'>
                </div>
            </div>
        </div>
    </div>
    
    <div>
    <text id='bodyText'>
    </div>
    


    



<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Ajax -->
<script type="text/javascript" src="/resources/js/searchAjax.js"></script>
<script type="text/javascript" src="/resources/js/unizAjax.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	let searchForm = $('#searchForm');
	let contentHtml = $('#contents');

	console.log(1);

	var resultAjax;

	let searchMenuNum = 5;

	setUnitagsByPreset(searchMenuNum, 7);

	$("#btnGetOpt").on("click", function(e) {
		e.preventDefault();

		console.log(1);

		let userSN = $('#userSN').val();

		searchService.getOptions(
				{userSN : userSN},
				function(result) {
					let keys = [];
					
					for(property in result) {
						keys.push(property);
						console.log("result [" + property + "] : " + result[property]);	
					}

					$("#option").val(keys.join(','));
				}
			);
	});

	$("#btnSetOpt").on("click", function(e) {
		e.preventDefault();
		console.log(2);

		let userSN = $('#userSN').val();
		let optionStrs = $('#option').val().split(',');
		let options = [];

		for(idx in optionStrs) {
			opt = parseInt(optionStrs[idx]);
			console.log("set opt:" + opt);
			if (opt != undefined && opt != null && !isNaN(opt)) {
				options.push(opt);
			}
		}

		console.log("param options:" + options);

		searchService.setOptions(
				{userSN : userSN, options: options},
				function(result) {
					for(property in result) {
						console.log("result [" + property + "] : " + result[property]);	
					}
				}
			);
	});

	$("#btnSearch").on("click", function(e) {
		e.preventDefault();

		console.log(3);

		let keyword = $('#keyword').val();
		let userSN = $('#userSN').val();
		
		searchService.getSearchedList(
				{keyword : keyword , userSN : userSN},
				function(result) {
					let datas = result.result
					let resultHtml = "";

					for(idx in datas) {	// 배열이라 인덱스
						let data =  datas[idx];	// 단일 인덱스 개체는 인스턴스 객체
						// 인스턴스 객체는  . <- 이걸로 이름을 그대로 쓸수 있음
						console.log("result Data [" + idx + "] group("  + data.group + "), unizSN("  + data.unizSN + "), count("  + data.count + ")");
						resultHtml += makeDivGroup(data);
					}

					contentHtml.html(resultHtml);
				}
			);
	});

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
							$("#btnSearch").trigger("click");
						})
					});
				}
			);
	}

	function makeDivGroup(data) {

		let divGroup = "";
		console.log("div group("  + data.group + "), unizSN("  + data.unizSN + "), count("  + data.count + ")");
		divGroup += "<div id='group_" + data.unizSN + "' value="+ data.unizSN +" count="+ data.count + ">";
		divGroup += "<h3>#" + data.group + "</h3>";
		divGroup += makeDivVideo(data.videoList);
		divGroup += "</div>";

		return divGroup;
	}

	function makeDivVideo(videoList) {
		let divVideo = "";

		for(idx in videoList) {	// 배열이라 인덱스
			console.log("- videoList [" + idx + "]");
			vdata = videoList[idx];	// 단일 인덱스 개체는 인스턴스 객체

			divVideo += "<div id='video_" + vdata.videoSN + "'>";
			divVideo += "<a href='/video/"+ vdata.videoSN + "'>";
			divVideo += "<img src='https://i.ytimg.com/vi/"+ vdata.utbVideoID +"/maxresdefault.jpg' alt='" + vdata.title +"'></img>";
			divVideo += "</a>";
			divVideo += '</div>';		// end video div
		}

		return divVideo;
	}
});
</script>

</body>
</html>
