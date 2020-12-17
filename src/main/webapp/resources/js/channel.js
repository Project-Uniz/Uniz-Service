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
		});
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
					str += "<ul>";
					str += "<li data-channelsn='"+list[i].channelSN+"'>";
					console.log(list[i].channelSN);
					str += "<a href='/channel/board/"+list[i].channelSN+"'><i class='fab fa-youtube'></i><strong>"+list[i].channelTitle+"</strong></a></div></li></ul>";
				}
				channelList.html(str);
			}
		});
		
	}
	
	
	// 게시글 전체 목록 보여줌
	function getAllPost(param, callback , error){
		var page = param.page || 1;
		console.log(".............= " + page);
		 
		 $.getJSON("/channel/list/all/" + page + ".json",
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
	
	
	//해당 채널의 게시물 목록을 보여줌
	function getChannelPostList(param, callback , error){
		 var channelSN = param.channelSN;
		 var page = param.page;
		 
		 $.getJSON("/channel/list/"+ channelSN + "/" + page +".json",
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
	// 게시물 보여줌
	function getPost(param , callback , error){
		var str = "";
		var boardPost = $("#boardPost");
		var postSN = param.postSN;
		var userSN = param.userSN;
		var title = param.title;
		
		
		$.ajax({
			type : 'get',
			url : "/channel/" + postSN,
			dataType : 'json',
			contentType : "application/json; charset=utf-8",
			success : function(post){
				var channelSN = post.channelSN;
				
					
					str += "<div><label>글 제목</label><input name='postSN' value='"+post.title+"' readonly='readonly' /></div>";
					str += "<div><label>글 번호</label><input name='postSN' value='"+post.postSN+"' readonly='readonly' /></div>";
					str += "<div><label>작성자 </label><input name='postSN' value='"+post.nick+"' readonly='readonly' /></div>";
					str += "<div><label>글 내용</label><textarea row='3' name='postContent' readonly='readonly'>" + post.postContent +"</textarea>";
					console.log("======"+post.postSN);
					
					
					
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
					
					
				
					str += "<div><label>글 제목</label><input name='title' value='"+post.title+"'  /></div>";
					str += "<div><label>글 번호</label><input name='postSN' value='"+post.postSN+"' readonly='readonly' /></div>";
					str += "<div><label>작성자 </label><input name='nick' value='"+post.nick+"' readonly='readonly' /></div>";
					str += "<div><input type='hidden' name='channelSN' value='"+post.channelSN+"' readonly='readonly' /></div>";
					str += "<div><label>글 내용</label><textarea row='3' name='postContent' >" + post.postContent +"</textarea>";
					channelSN = post.channelSN;
				
					
					
					
				boardPost.html(str);

					
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