console.log("ChReply Module.........");

var chReplyService = (function(){
	
	function add(reply, callback, error){
		
		$.ajax({
			type : 'post',
			url : '/chreplies/add' ,
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
			url : '/chreplies/delete/' + replySN,
			success : function(deleteResult, status, xhr){
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
	
	function commentList(param, callback, error){
		
		var postSN = param.postSN;
		var page = param.page || 1;
		
		$.getJSON("/chreplies/page/" + postSN + "/" + page + ".json",
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
	
	function commentUpdateProc(replySN){
		   
		var updateContent = $('[name=content_'+replySN+']').val();
	   	var updateReplySN = $('[name=replySN_'+replySN+']').val();
		var modify = {'replyContent' : updateContent, 'replySN' : updateReplySN};
	    
	    $.ajax({
	        url : '/chreplies/update/'+updateReplySN,
	        type : 'put',
	        data : JSON.stringify(modify),
			contentType : "application/json; charset=utf-8",
	        success : function(data){
	        	showList(1);
	            	 //댓글 수정후 목록 출력 
	        }
	    });
	}

	
	return{
		
		
		add : add,
		remove : remove,
		commentList : commentList,
		commentUpdateProc : commentUpdateProc,
		displayTime : displayTime
		
	};

	

})();