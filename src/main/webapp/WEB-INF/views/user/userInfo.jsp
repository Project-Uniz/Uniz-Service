<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:formatDate var="user_createDateTime" value="${user.createDateTime}"
	pattern="yyyy-MM-dd" />
<fmt:formatDate var="user_updateDateTime" value="${user.updateDateTime}"
	pattern="yyyy-MM-dd" />
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/includes/header.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/includes/nav.jsp"%>
	<div class="mainPage">

		<div class="emptyBox">
			<h1>내 프로필</h1>
		</div>
		<div class="myTotalbox">
			<div class="myPageBanner">
				<div class="profileBox">
					<div class="profileImg">
						<img src="profile.jpg" alt="">
					</div>
				</div>
				<div class="myinfoBox">
					<h6>${user.nick}님의마이페이지</h6>
					<p>닉네임 : ${user.nick}</p>
					<p>아이디 : ${user.userId }</p>
					<c:if test="${user.userType eq 1}">
						<p>일반회원</p>
					</c:if>
					<c:if test="${user.userType eq 2}">
						<p>크리에이터</p>
					</c:if>
					<c:if test="${user.userType eq 99}">
						<p>관리자</p>
					</c:if>
					<p>가입일 : ${user_createDateTime}</p>

					<div class="myButtonBox">
						<button class="myModify" id="myBtn"
							onclick="location.href='/user/modify'">수정하기</button>
						<button class="myDelete" id="removeBtn">탈퇴하기</button>
					</div>
				</div>
			</div>

			<div class="MyPageunizPointBox">
				<div class="line2"></div>
				<div class="myUnizPoints">
					<div class="myPointsClassHeaderBox">
					<div class="pointttt">
						<p class="myPointsHeader">내 유니즈 포인트 리스트</p>
						<p class="myPointsHeader">내 포인트 이력</p>
					</div>
					</div>

					 <div class="boxboxbox">
					<div class="myPointsBox">
						<c:forEach items="${myUnizPoint}" var="myUnizPoint">
							<div class="myPoint">
								<div class="pointUp">
									<p>${myUnizPoint.unizKeyword}</p>
								</div>
								<div class="pointDown">
									<p>${myUnizPoint.point}</p>
								</div>
							</div>

						</c:forEach>

						<!-- 계속 추가하면 된다.  -->
					</div>
					
					<div class="pointHistory">
                      <table class="ui table" style="width: 100%; all:none;" id="logTable">
							<thead class="thead-light">
								<tr>
									<th>유니즈키워드</th>
									<th>변동사항</th>
									<th>변경사유</th>
									<th>변경일</th>
								</tr>
							</thead>
						</table>
                    </div>
                    <!-- end pointHistory -->
					</div>
				</div>
			</div>
		</div>
		
	</div>
	
	
	<%@ include file="/WEB-INF/views/includes/script.jsp"%>
	<script>
		$(document).ready(function() {
			$("#removeBtn").click(function() {
				if (confirm("정말로 탈퇴하시겠습니까??") == true) {
					location.href = '/user/delete';
				}
			});
			
			$('#logTable').DataTable({
				"dom": '<"bottom"lp>lt<"bottom"if>',
				processing: true,
				serverSide: false,
				paging: false,
				pagingType: "simple_numbers",
				order: false,
				ordering: false,
				info: true,
				filter: true,
				
				language: {
					"zeroRecords": "데이터가 없습니다.",
					"lengthMenu": "_MENU_ 개씩 보기",
					"search": "검색:",
					"info": "_PAGE_ / _PAGES_",
					"infoFiltered": "(전체 _MAX_개의 데이터 중 검색결과)",
					"paginate": {
						"previous": "이전",
						"next": "다음"
					} 
				},
				ajax:{
					"url": "/user/getMyPointHistory",
					"type" :"GET",
					"dataType" : "json"
				},
				columns: [
					{data: "unizKeyword"},
					{data: "point"},
					{data: "type"},
					{data: "createDateTime"}
				]
			
		});
	});
	</script>
</body>
</html>