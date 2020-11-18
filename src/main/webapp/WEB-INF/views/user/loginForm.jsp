<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<style>
    @import url(https://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300);

    *{

        font-family: 'Source Sans Pro', sans-serif;

        
        box-sizing: border-box;
        padding: 0;
        border: 0;
        margin: 0;
    }
    body{
        width: 100%;
        height: 500px;
        /* background-color: rgba(61, 114, 52, 0.4); */
    }   
    .box{
        display: flex;
        align-items: center;
        justify-content: center;
        margin-top: 90px;

    } 
    .container{
        position: relative;
        width: 300px;
        height: 400px;
        border-radius: 20px;
        background: linear-gradient( to top, rgb(255, 230, 149), rgb(99, 203, 99));
        background-repeat: no-repeat;
        
    }
    .form{
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
    ::placeholder{
        color: rgb(128, 182, 90);
    }
    .form2{
        position: absolute;
        top: 180px;
        

        

    }
    .btn{

        position: absolute;
        top: 280px;
        left: 75px;
        outline: none;
        background-color:inherit;
        color: rgb(128, 182, 90);
        font-size: 30px;
        text-align: center;
        padding: 10px 30px;
        padding-top: 5px;
        border: 2px solid white;
        border-radius: 10px;

    }
    .p{
        font-size: 30px;
        font-weight: 800;
        text-align: center;
        padding: 28px;
        color: white;
    }
    .form3{

        font-family: inherit;
        color: green;
        position: absolute;
        top: 250px;
        left: 110px;

    }
    .check{
        position: absolute;
        top: 255px;
        left: 93px;



    }
    #box{
        cursor: pointer;

    }
    input[id="box"] + label{
        border : 2px solid inherit;
        
    }

</style>
<body>

<%!
private String getIdFromCookie(HttpServletRequest request){

	//쿠키 이름이 id라면 값을 받아서 저장한다.

	String userId = "";

	Cookie[] cookies = request.getCookies();

	for (int i = 0; i < cookies.length; i++) {

	if (cookies[i].getName().equals("userId")) {

	userId += cookies[i].getValue();

	}   }

	return userId;

	}//end method
%>
<%
String userId = getIdFromCookie(request);//"asdf"를 반환해서 id에 저장.  
String checked = "";

if(userId!=null && ! userId.equals("")){ //아이디가 null이나 빈 문자열이 아니라면

checked= "checked"; //이게 체크박스에서 value="checked"를 의미.
} 
%>
<% 
request.setCharacterEncoding("utf-8"); //한글깨짐 방지 사전호출. 
%>
    <div class="box">
        <div class="container">
            
            <div><p class="p">WelCome!!!</p></div>
            <form action="/user/loginForm" method="post" onsubmit="return checkForm();">
                <input class="form" type="text" placeholder="userId" id="userId" name="userId" value=<%=userId%>><br>
                <input class="form form2"type="password" placeholder="password" id="password" name="password"><br>
                
                <input type="checkbox" <%=checked%> class="check" id="box" name="chk">
                <label for="box" class="form3">remember Id</label>
                
                <button class="btn"type="submit">sign in</button>



            </form>
        </div>


    </div>

</body>
</html>



 <!-- <script>
function checkForm(){
	//아이디에 대한 참조를 얻어온다. 
    let id = document.getElementById("id");
    let pwd = document.getElementById("pwd");
    
   //value가 ""빈 문자열이면 다음 페이지로 넘어가지 않는다. 
    if(id.value == ""){ 
        alert("아이디를 입력해주세요. ");
        return false;
    }
    if(pwd.value== ""){ 
        alert("비밀번호를 입력해주세요.");
        return false;
    }
    return true;
} 

</script>  -->
</body>
</html>