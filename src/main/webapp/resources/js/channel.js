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
	function getChannelList(callback , error){
		 $.getJSON("/channel/list" + ".json",
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
	function getPost(param , callback, error){
		var postSN = param.postSN;
		$.getJSON("/channel/" + postSN + ".json",
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
	
	function update(channel, callback, error){
		console.log("글 번호= " + channel.postSN);
		console.log("글 제목= " + channel.title);
		console.log("글 내용= " + channel.postContent);
		
		$.ajax({
			type : 'put',
			url : '/channel/' + channel.postSN + '.json',
			data : JSON.stringify(channel),
			contentType : "application/json; charset=utf-8",
			success : function(result , status, xhr){
				if(callback){
					callback(result);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		});
	}
	
	return{
		create : create,
		getChannelList : getChannelList,
		getChannelPostList : getChannelPostList,
		getPost : getPost,
		remove : remove,
		update : update
	};
	
	
})();