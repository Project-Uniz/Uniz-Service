console.log("Category");

var categoryService = (function(){
	
	function getBoardList(){
		var str = "";
		var boardList = $("#boardList");
		
		$.ajax({
			
			type : 'get',
			url : '/category/boardlist',
			dataType : 'json',
			contentType : "application/json; charset=utf-8",
			success : function(list){
				
				for(var i = 0, len = list.length || 0; i < len; i ++){
					
					str += "<li data-boardsn='" + list[i].boardSN + "'>";
					console.log("boardSN : " + list[i].boardSN);
					str += "<a href='/category/board/" + list[i].boardSN + "'><strong>" 
					+ list[i].boardComment + "<strong></a></div></li>";
					
				}
				boardList.html(str);
			}
			
		});
	}
	
	function getAllPost(param, callback , error){
		var page = param.page || 1;
		console.log(".............= " + page);
		 
		 $.getJSON("/category/list/all/" + page + ".json",
		 function(data){
			 if(callback){
				 callback(data.postCnt, data.list);
			 }
		 }).fail(function(xhr, status, err){
			 if(error){
				 error();
			 }
		 });
	}
	
	function getBoardPostList(param, callback , error){
		
		var boardSN = param.boardSN;
		var page = param.page || 1;
		
		console.log("boardSN : " + boardSN);
		console.log("page : " + page);

		$.getJSON('/category/boardlist/'+ boardSN +"/" + page + '.json',
				
				function(data){

					if(callback){
						
						callback(data.postCnt, data.list);
						
					}
				}).fail(function(xhr, status, err){
					
					if(error){
						
						error();
						
					}
				});
		}   
	
	function getImg(param){
		var str = "";
		var postSN = param;
		var imgs = $("#imgs");
		
		$.ajax({
			
			type : 'get',
			url : '/category/fileview/'+ param ,
			dataType : 'json',
			contentType : "application/json; charset=utf-8",
			success : function(img){
				
				for(var i = 0, len = img.length || 0; i < len; i ++){
					str += "<img src='/img/"+ img[i].CHANGENAME +"'/><div></div>";
				}
				
				imgs.html(str);
				
			}
		})
	}
	
	function getPost(param){
		var str = "";
		var boardPost = $("#boardPost");
		var postSN = param;
		$.ajax({
			type : 'get',
			url : '/category/'+param,
			dataType : 'json',
			contentType : "application/json; charset=utf-8",
			success : function(post){
				
				var boardSN = post.boardSN;
				
				
				
				str += "<h1>" + post.boardComment + "</h1><div></div>";
				str += "<div><label>글 제목</label><input name='postSN' value='"+post.title+"' readonly='readonly' /></div>";
				str += "<div><label>글 번호</label><input name='postSN' value='"+post.postSN+"' readonly='readonly' /></div>";
				str += "<div><label>작성자 </label><input name='postSN' value='"+post.nick+"' readonly='readonly' /></div>";
				str += "<div><label>글 내용</label><p>" + post.postContent +"</p>";
				
				$("#modify").on("click", function(){
					self.location = "/category/modify/" + postSN + "/"+ boardSN;
				});
				
				$("#list").on("click", function(){
					self.location = "/category/board/"+boardSN;
				});
				
				boardPost.html(str);
				
			}
			
		});
		
	}
	
	function modify(param){
		var str = "";
		var boardPost = $("#boardPost");
		var postSN = param.postSN;
		
		$.ajax({
			type : 'get',
			url : '/category/'+ postSN,
			dataType : 'json',
			contentType : "application/json; charset=utf-8",
			success : function(post){
					
					
				
				str += "<h1>" + post.boardComment + "</h1><div></div>";
				str += "<div><label>글 제목</label><input name='title' value='"+post.title+"'  /></div>";
				str += "<div><label>글 번호</label><input name='postSN' value='"+post.postSN+"' readonly='readonly' /></div>";
				str += "<div><label>작성자 </label><input name='nick' value='"+post.nick+"' readonly='readonly' /></div>";
				str += "<div><input type='hidden' name='boardSN' value='"+post.boardSN+"' readonly='readonly' /></div>";
				str += "<div><label>글 내용</label><textarea row='3' name='postContent' >" + post.postContent +"</textarea>";
				
					
					
				boardPost.html(str);

					
			}
		});
	}
		
	
	
	function displayTime(timeValue){
		var today = new Date();
		var gap = today.getTime() - timeValue;
		var dateObj = new Date(timeValue);
		var str = "";
		
		if(gap < (1000 * 60 * 60 * 24)) {
			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();
			
			return [ (hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0' ) + mi, ':', (ss > 9 ? '' : '0') + ss].join('');
		}else{
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth() + 1;
			var dd = dateObj.getDate();
			
			return [yy, '/' , (mm > 9 ? '' : '0') + mm, '/', (dd > 9 ? '' : '0') + dd].join('');
		}
	};
	
	return{
		getBoardList : getBoardList,
		getAllPost : getAllPost,
		getBoardPostList : getBoardPostList,
		getPost : getPost,
		getImg : getImg,
		modify : modify,
		displayTime : displayTime
	};
	
})();
