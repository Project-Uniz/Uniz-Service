
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>

<!-- Ajax -->
<script type="text/javascript" src="/resources/js/searchAjax.js"></script>
<script type="text/javascript" src="/resources/js/unizAjax.js"></script>

<!-- js Func -->
<script type="text/javascript" src="/resources/js/search.js"/></script>

</body>
</html>
