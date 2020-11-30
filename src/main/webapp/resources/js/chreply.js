console.log("ChReply Module.........");

var chReplyService = (function(){
	
	function add(reply, callback, error){
		
		$.ajax({
			type : 'post',
			url : '/chreplies/add' ,

			data : reply,
			//contentType : "application/json; charset=utf-8",
			success : function(data){
				if(data == 1 ){
					chReplyService.commentList(reply.replySN);
				}
			}
			
		});
	}
	
	function commentList(param){
		
		var postSN = param.postSN;
		var page = param.page || 1;
		
		
	    $.ajax({
	        url : '/chreplies/page/'+postSN+ "/" + page ,
	        type : 'get',
	        dataType : 'json',
			contentType : "application/json; charset=utf-8",
	        success : function(data){
	            var a =''; 
	            $.each(data, function(key, value){ 
	                a += '<div class="commentArea" style="border-bottom:1px solid darkgray; margin-bottom: 15px;">';
	                a += '<div class="commentInfo'+value.replySN+'">'+'댓글번호 : '+value.replySN+' / 작성자 : '+value.nick;
	                a += '<a onclick="commentUpdate('+value.replySN+',\''+value.replyContent+'\');"> 수정 </a>';
	                a += '<a onclick="commentDelete('+value.replySN+');"> 삭제 </a> </div>';
	                a += '<div class="commentContent'+value.replySN+'"> <p> 내용 : '+value.replyContent +'</p>';
	                a += '</div></div>';
	            });
	            
	            $(".reply").html(a);
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
	

//	function getList(param, callback, error){
//		var postSN = param.postSN;
//		var page = param.page || 1;
//		$.getJSON("/chreplies/page/" + postSN + "/" + page + ".json",
//				function(data){
//			if(callback){
//				console.log("aaaaaaa= " + data);
//				callback(data);
//				
//			}
//		}).fail(function(xhr, status,err){
//			if(error){
//				error();
//			}
//		});
//	}
	
	return {
		add : add,
		commentList : commentList,

		displayTime : displayTime
	};
})();