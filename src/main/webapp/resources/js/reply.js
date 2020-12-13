console.log("Reply Module.........");

var replyService = (function(){
	function add(reply, callback, error){
		console.log("add reply......");
		$.ajax({
			type : 'post',
			url : '/replies/new',
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		})
	}
	
	function remove(replySN, callback, error){
		$.ajax({
			type : 'delete',
			url : '/replies/' + replySN,
			success : function(result, status, xhr){
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
	
	function commentUpdateProc(replySN){
		   
		var updateContent = $('[name=content_'+replySN+']').val();
	   	var updateReplySN = $('[name=replySN_'+replySN+']').val();
		var modify = {'replyContent' : updateContent, 'replySN' : updateReplySN};
	    
		$.ajax({
			type : 'put',
			url : '/replies/' +updateReplySN,
			data : JSON.stringify(modify),
			contentType : "application/json; charset=utf-8",
	        success : function(data){
	            if(data == 1)
	            location.reload(true);
	            	 //댓글 수정후 목록 출력 
	            
	        }
	    });
	}
	
	function get(replySN, callback, error){
		$.get("/replies/" + replySN + ".json", function(result){
			if (callback){
				callback(result);
			}
		}).fail(function(xhr, status, err){
			if(error){
				error();
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
	
function commentList(param, callback, error){
		
		var postSN = param.postSN;
		var page = param.page || 1;
		
		$.getJSON("/replies/page/" + postSN + "/" + page + ".json",
				function(data){
			if(callback){
				callback(data.replyCnt, data.list);
			}
		}).fail(function(xhr, status, err){
			if(error){
				error();
			}
		});
	    
	}   

function showList(page){
	
	var postSN = '<c:out value="${postSN}"/>';
	var boardSN = '<c:out value="${board.boardSN}"/>';
	
	replyService.commentList({postSN : postSN, page : page || 1} ,function(replyCnt, list){
		
		if(page == -1 ){
			pageNum = Math.ceil(replyCny / 10.0);
			showList(pageNum);
			return;
		}
		
		var a = "";
		
		if(list == null || list.length == 0){
			return;
		}
		
		for (var i = 0, len = list.length || 0; i < len; i++){
			  a += '<div class="commentArea" style="border-bottom:1px solid darkgray; margin-bottom: 15px;">';
                a += '<div class="commentInfo'+list[i].replySN+'">'+'댓글번호 : '+list[i].replySN+' / 작성자 : '+list[i].nick;
                a += '<a onclick="commentUpdate('+list[i].replySN+',\''+list[i].replyContent+'\');"> 수정 </a>';
                a += '<a role="button" class="deleteBtn" onclick="remove('+list[i].replySN+');"> 삭제 </a> </div>';
                a += '<div class="commentContent'+list[i].replySN+'"> <p> 내용 : '+list[i].replyContent +'</p>';
                a += '</div></div>';
		}
		 $(".reply").html(a);
		 showReplyPage(replyCnt);
	});

}
	
	return {
		add :add,
		commentList : commentList,
		remove : remove,
		commentUpdateProc : commentUpdateProc,
		get : get,
		displayTime : displayTime,
		showList : showList
	};
})();