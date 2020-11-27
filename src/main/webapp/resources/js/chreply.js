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
		});
		
	}

	
	function get(replySN, callback, error){
		$.get("/chreplies/" + replySN + ".json", function(result){
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
	
	function getList(param, callback, error){
		var postSN = param.postSN;
		var page = param.page || 1;
		$.getJSON("/chreplies/page/" + postSN + "/" + page + ".json",
				function(data){
			if(callback){
				console.log("aaaaaaa= " + data);
				callback(data);
				
			}
		}).fail(function(xhr, status,err){
			if(error){
				error();
			}
		});
	}
	
	return {
		add : add,
		getList : getList,
		get : get,
		displayTime : displayTime
	};
})();