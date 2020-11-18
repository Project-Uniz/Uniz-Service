console.log("Channel!!");


var channelService = (function(){
	
	//채널 게시물 생성
	function create(post, callback, error){
		console.log("create post---------");
		
		$.ajax({
			
			type : 'post',
			url : '/channel/new/',
			data : JSON.stringify(post),
			contentType : "application/json; charset=utf-8",
			success : function(result , status , xhr){
				if(callback){
					callback(result);
				}
			},
			error : function(xhr, status , er){
				if(error){
					error(er);
				}
			}
		})
	}
	
	// 채널 목록 보여줌
	function getChannelList(){
		
		var str ="";
		var channelList = $("#channelList");
		
		$.ajax({
			type : 'get',
			url : "/channel/list",
			dataType : 'json',
			contentType : "application/json; charset=utf-8",
			success : function(list){
				for(var i = 0, len = list.length || 0; i < len; i++){
					let cData = list[i];
					for(prop in cData) {
						console.log(prop + ":" + cData[prop] );
					}
					
					str += "<li data-channelsn='"+list[i].channelSN+"'>";
					console.log(list[i].channelSN);
					str += "<a href='/channel/board/"+list[i].channelSN+"'><strong>"+list[i].channelTitle+"</strong></a></div></li>";
				}
				channelList.html(str);
			}
		});
		
	}
	
	
	// 게시글 전체 목록 보여줌
	function getAllPost(){
		
		var str = "";
		var allPostList = $("#allPostList");
		$.ajax({
			type : 'get',
			url : "/channel/list/all",
			dataType : 'json',
			contentType : "application/json;  charset=utf-8",
			success : function(list){
				
				str += "<thead><tr><th>채널명</th><th>글 제목</th><th>작성자</th><th>작성 일</th></tr></thead>"
				
					for (var i = 0, len = list.length || 0; i < len; i++){
					
					str += "<thead><tr><th>"+list[i].channelTitle + "</th>";
					str += "<th><a  href='/channel/get/"+list[i].postSN+"'>"+list[i].title+"</a></th>";
					str += "<th>"+list[i].nick + "</th>";
					str += "<th>"+displayTime(list[i].createDateTime) +"</th></tr></thead>";
					
				}
				allPostList.html(str);
			}
		});
		
	}
	
	
	//해당 채널의 게시물 목록을 보여줌
	function getChannelPostList(param, callback , error){
		 var channelSN = param.channelSN;
		 
		 $.getJSON("/channel/list/"+ channelSN + ".json",
				 function(data){
			 if(callback){
				 callback(data);
			 }
		 }).fail(function(xhr, status, err){
			 if(error){
				 error();
			 }
		 });
	}
	// 게시물 보여줌
	function getPost(param , callback , error){
		var str = "";
		var boardPost = $("#boardPost");
		var postSN = param.postSN;
		$.ajax({
			type : 'get',
			url : "/channel/" + postSN,
			dataType : 'json',
			contentType : "application/json; charset=utf-8",
			success : function(post){
				var channelSN = "";
					
				for ( var i = 0, len = post.length || 0; i < len; i++){
					str += "<div><label>글 제목</label><input name='postSN' value='"+post[i].title+"' readonly='readonly' /></div>";
					str += "<div><label>글 번호</label><input name='postSN' value='"+post[i].postSN+"' readonly='readonly' /></div>";
					str += "<div><label>작성자 </label><input name='postSN' value='"+post[i].nick+"' readonly='readonly' /></div>";
					str += "<div><label>글 내용</label><textarea row='3' name='postContent' readonly='readonly'>" + post[i].postContent +"</textarea>";
					
					channelSN = post[i].channelSN;
				}
				
				$("#modify").on("click", function(){
					self.location = "/channel/modify/" + postSN + "/"+ channelSN;
				});
				
				$("#list").on("click", function(){
					self.location = "/channel/board/"+channelSN;
				});
				
				boardPost.html(str);
			}
		});
	}
	
	function modify(param , callback , error){
		var str = "";
		var boardPost = $("#boardPost");
		var postSN = param.postSN;
		
		$.ajax({
			type : 'get',
			url : "/channel/" + postSN,
			dataType : 'json',
			contentType : "application/json; charset=utf-8",
			success : function(post){
					
					
				for ( var i = 0, len = post.length || 0; i < len; i++){
					str += "<div><label>글 제목</label><input name='title' value='"+post[i].title+"'  /></div>";
					str += "<div><label>글 번호</label><input name='postSN' value='"+post[i].postSN+"' readonly='readonly' /></div>";
					str += "<div><label>작성자 </label><input name='nick' value='"+post[i].nick+"' readonly='readonly' /></div>";
					str += "<div><input type='hidden' name='channelSN' value='"+post[i].channelSN+"' readonly='readonly' /></div>";
					str += "<div><label>글 내용</label><textarea row='3' name='postContent' >" + post[i].postContent +"</textarea>";
					channelSN = post[i].channelSN;
				}
					
					
					
					boardPost.html(str);

					//var formObj = $("form");
					//console.log("channelSN: " + channelSN);
					//$('button').on("click", function(e){
					//	e.preventDefault();
					//	var channel = param.channelSN;
					//	var operation = $(this).data("oper");
					//	
					//	if(operation === 'remove'){
					//		formObj.attr("action" , "/channel/remove");
					//	}else if (operation === 'list'){
					//		self.location="/channel/board/" + channelSN;
					//		return;
					//	}
					//	formObj.submit();
					//});
			}
		});
	}
	
	
	function remove(postSN, callback, error){
		$.ajax({
			type : 'delete',
			url : '/channel/' + postSN,
			success : function(deleteResult, status, error){
				if(callback){
					callback(deleteResult);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
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
		create : create,
		getAllPost : getAllPost,
		getChannelList : getChannelList,
		getChannelPostList : getChannelPostList,
		getPost : getPost,
		remove : remove,
		displayTime : displayTime,
		modify : modify
		
	};
	
	
})();