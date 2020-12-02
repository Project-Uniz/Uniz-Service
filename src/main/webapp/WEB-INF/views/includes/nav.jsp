<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
 <div class="navbar">
            <ul>
                <li><a href="/maincontent">Home</a></li>
                <li><a href="/UnizHit/UnizHit">인기</a></li>
                <li><a href="#">추천</a></li>
                <li><a href="/search/index">검색</a></li>
                <li><a href="/board/list">커뮤니티</a></li>
                <span>
                    <ul>
                    
                    	<c:if test="${user == null}">
                        	<li><a href="/user/register" >Register</a></li>
                        	<li><a href="/user/loginForm" >Login</a></li>
                        </c:if>
                        <!-- 로그인 시에만 보여줄 영역 -->
                        <c:if test="${user != null}">
	                        <li><a href="/user/logout" target="trg">Logout</a></li>
	                        <li><a href="/user/info" target="trg">Mypage</a></li>
                        
                        </c:if>
                        
                        
                        <!-- <li><a href="#">icon</a></li>
                        <li><a href="#">icon2</a></li> -->
                    </ul>
                </span>
            </ul>
         </div> 