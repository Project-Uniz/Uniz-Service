<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../includes/header.jsp" %>
           
           
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">게시판</h1>
                    <button class='btn btn-default' id="channelBoard">채널 게시판으로 이동</button>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
           
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading"> 게시판 목록</div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
<table  class="table table-striped table-bordered table-hover" >
	<thead>
	    <tr>
	        <th>글 번호</th>
	        <th>제목</th>
	        <th>작성자</th>
	        <th>작성일</th>
	    </tr>
	</thead>
                                
	<c:forEach items="${list}" var="board">
		<tr>
			<td>
				<c:out value="${board.postSN}" />
			</td>
			<td>
				<a class='move' href='<c:out value="${board.postSN}"/>'>
				<c:out value="${board.title}" /></a>
			</td>
			<td>
				<c:out value="${board.nick}" />
			</td>
   			<td>
   			<fmt:formatDate pattern ="yyyy-MM-dd" value="${board.createDateTime}"/>
   			</td>
		</tr>
	</c:forEach>	
</table>
<div class='row'>
	<div class="col-lg-12">
		<form id='searchForm' action="/board/list" method='get'>
			<select name='type'>
				<option value=""
				<c:out value="${pageMaker.cri.type == null ? 'selected' :''}"/>>--</option>
				<option value="T"
				<c:out value="${pageMaker.cri.type == 'T' ? 'selected' :''}"/>>제목</option>
				<option value="C"
				<c:out value="${pageMaker.cri.type == 'C' ? 'selected' :''}"/>>내용</option>
				<option value="W"
				<c:out value="${pageMaker.cri.type == 'W' ? 'selected' :''}"/>>작성자</option>
				<option value="TC"
				<c:out value="${pageMaker.cri.type == 'TC' ? 'selected' :''}"/>> 제목 or 내용 </option>
				<option value="TW"
				<c:out value="${pageMaker.cri.type == 'TW' ? 'selected' :''}"/>> 제목 or 작성자 </option>
				<option value="TWC"
				<c:out value="${pageMaker.cri.type == 'TWC' ? 'selected' :''}"/>> 제목 or 내용 or 작성자 </option>
			</select>
				<input type='text' name='keyword' value='<c:out value="${pageMaker.cri.keyword}"/>'  />
				<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
				<input type='hidden' name='amount' value='${pageMaker.cri.amount}'>
				<button class='btn btn-default'>검색</button>
		</form>
	</div>
</div>		


		<div>
			<c:if test="${sessionScope.userId !=null }">
			<button id="createBtn" type="button" class="btn btn-xs pull-right">게시글 작성</button>
			</c:if>
		</div>
        
        <div class='pull-right'>
			<ul class="pagination">
				<c:if test="${pageMaker.prev}">
				<li class="paginate_button prev" ><a href="${pageMaker.startPage-1}">Pre</a>
				</li>
				</c:if>
				
				<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage }">
				<li class="paginate_button" ${pageMaker.cri.pageNum == num ? "active" : "" }"><a href="${num}">${num}</a></li>
				</c:forEach>
				
				<c:if test="${pageMaker.next}">
				<li class="paginate_button next"><a href="${pageMaker.endPage +1 }">next</a></li>
				</c:if>
			</ul>
		</div>
		
		<form id='actionForm' action="/board/list" method='get'>
			<input type='hidden' name='pageNum' value = '${pageMaker.cri.pageNum}'>
			<input type='hidden' name='amount' value = '${pageMaker.cri.amount}'>
			<input type='hidden' name='type' value ='<c:out value="${pageMaker.cri.type}"/>'>
			<input type='hidden' name='keyword' value ='<c:out value="${pageMaker.cri.keyword}"/>'>
		</form>
                              	
                              	
                              <!-- Modal 추가 -->
                              <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
											<h4 class="modal-title" id="myModalLabel">Modal title</h4>
										</div>
									<div class="modal-body">처리가 완료되었습니다.</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
											<button type="button" class="btn btn-primary">Save changes</button>
										</div>
									</div>
								</div>
							 </div>
                         
                     	</div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-6 -->
            </div>
            <!-- /.row -->

<%@include file="../includes/footer.jsp" %>

 
<script type="text/javascript">

$(document).ready(function(){
	
	var result = '<c:out value="${result}"/>';
	history.replaceState( {}, null, null);
	
	$("#channelBoard").on("click", function(){
		self.location ="/channel/ch";
	});
			
	$("#createBtn").on("click",function(){
		self.location ="/board/register";
	});
	
	var actionForm = $("#actionForm");
	
	$(".paginate_button a").on("click" , function(e){
		
		e.preventDefault();
		
		console.log('click');
		
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		actionForm.submit();
	});
	
	$(".move").on("click", function(e){
		
		e.preventDefault();
		
		actionForm.append("<input type='hidden' name='postSN' value='"+$(this).attr("href")+"'>");
		
		actionForm.attr("action","/board/get");
		
		actionForm.submit();
		
	}); 
	
	var searchForm = $("#searchForm");
	
	$("#searchForm button").on("click", function(e){
		
		if(!searchForm.find("option:selected").val()){
			alert("검색 종류를 선택하세요");
			return false;
		}
		
		if(!searchForm.find("input[name='keyword']").val()){
			alert("키워드를 입력하세요");
			return false;
		}
		
		searchForm.find("input[name='pageNum']").val("1");
		e.preventDefault();
		
		searchForm.submit();
		
	});

		
});

</script>
