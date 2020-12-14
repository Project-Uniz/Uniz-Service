console.log("aaaaaaaakkkkk");

var replyService = (function(){

	function add(reply, callback, error){
 		console.log("add reply........");
 		
 		$.ajax({
 			type : 'post',
 			url : '/unizReply/new',
 			data : JSON.stringify(reply),
 			contentType : "application/json; charset=utf-8",
 			success : function(result, status,xhr){
 			
 			if(callback ){
					callback(result);
				}
				
			},
 		
 		error : function(xhr, status, er){
 			if(error) {
 				error(er);
 				}
 			}
 		})
	}
	
	function getList(param, callback, error){
	
		let videoSN = param.videoSN;
		
		let page = param.page || 1;
		
		$.getJSON("/unizReply/pages/" + videoSN + "/" +page + ".json",
			function(data) {
				if(callback) {
				callback(data);
				}
			}).fail(function(xhr, status, err){
			if(error){
				error();
				}
			});
		}
		
	function remove(replySN, callback, error){
		$.ajax({
			type : 'delete',
			url : '/unizReply/' + replySN,
			success : function(data){
		        	
		            	showList(1); //댓글 수정후 목록 출력 
		            
		        },
			error : function(xhr, status, er){
			if(error){
				error(er);
				}
			}
			});
		}
	
		
	function displayTime(timeValue){
	
		let today = new Date();
		
		let gap = today.getTime() - timeValue;
		
		let dateObj = new Date(timeValue);
		let str = "";
		
		if( gap <(1000 * 60 * 60 * 24)){
		
			let hh = dateObj.getHours();
			let mi = dateObj.getMinutes();
			let ss = dateObj.getSeconds();
			
			return [ (hh > 9 ? '' : '0') + hh, ':' (mi > 9 ? '' : '0') + mi,
				':', (ss > 9 ? '' : '0') + ss].join('');
		
		}else {
			let yy = dateObj.getFullYear();
			let mm = dateObj.getMonth() + 1;
			let dd = dateObj.getDate();
			
			return [ yy, '/', (mm > 9 ? '' : '0') + mm, '/',
					(dd > 9 ? '' : '0') + dd ].join('');
				}
			}
			;
			
		
			

	return {
		add:add,
		getList : getList,
		remove : remove,
		displayTime : displayTime
	};
})();