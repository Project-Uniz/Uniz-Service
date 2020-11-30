$(document).ready(function() {
	let contentHtml = $('#contents');
	let presetUnitag = [];

	const searchMenuNum	= 5;
	const expireDay		= 3;
	const maxUnitagSize	= 7;

	let tempHistory = getSearchHistories();

	initUnitag(searchMenuNum, maxUnitagSize);

	$("#keyword").keydown(function(key) {

		// 엔터키 처리
		if (key.keyCode == 13) {
			const keyword = $(this).val();
		if (keyword != null && keyword != undefined && keyword.trim().length > 0) {
				$("#btnSearch").trigger("click");
			}
		}
	});

	$("#btnGetOpt").on("click", function(e) {
		e.preventDefault();

		let userSN = $('#userSN').val();

		searchService.getOptions(
				{userSN : userSN},
				function(result) {
					let keys = [];

					for(property in result) {
						keys.push(property);
					}

					$("#option").val(keys.join(','));
				}
			);
	});

	$("#btnSetOpt").on("click", function(e) {
		e.preventDefault();

		const userSN = $('#userSN').val();
		const optionStrs = $('#option').val().split(',');
		const options = [];

		for(const idx in optionStrs) {
			const opt = parseInt(optionStrs[idx]);
			if (opt != undefined && opt != null && !isNaN(opt)) {
				options.push(opt);
			}
		}

		console.log("param options:" + options);

		searchService.setOptions(
				{userSN : userSN, options: options},
				function(result) {
					console.log("options : " + JSON.stringify(result));
				}
			);
	});

	$("#btnSearch").on("click", function(e) {
		e.preventDefault();

		const keyword = $('#keyword').val().split(/\s*\s/);
		const userSN = $('#userSN').val();

		console.log(keyword);

		$("#keyword").blur();
		
		searchService.getSearchedList(
				{keyword : keyword , userSN : userSN},
				function(result) {

					setSearchKeywordCookie(keyword.join(" "), expireDay, maxUnitagSize);

					setUnitag(
							makeUnitagKeywordList(),
							maxUnitagSize
							);

					const datas = result.result
					let resultHtml = "";

					for(idx in datas) {	// 배열이라 인덱스
						let data =  datas[idx];	// 단일 인덱스 개체는 인스턴스 객체
						// 인스턴스 객체는  . <- 이걸로 이름을 그대로 쓸수 있음
						console.log("result Data [" + idx + "] group("  + data.group + "), unizSN("  + data.unizSN + "), count("  + data.count + ")");
						resultHtml += makeDivGroup(data);
					}

					contentHtml.html(resultHtml);
				}
			);
	});

	function initUnitag(menu, limit) {

		$("#keyword").focus();
		
		unizService.getPreset(
				{menu : menu},
				function(result) {

					// 유니태그 프리셋 세팅
					for(let idx = 0 ; idx<limit ; idx++) {
						presetUnitag.push(result[idx].unizKeyword);
					}

					console.log("presetUnitag : " + JSON.stringify(presetUnitag));
					
					setUnitag(
							makeUnitagKeywordList(),
							limit
						);
				}
			);
	}

	function makeUnitagKeywordList() {

		let subPreset = [];
		for(idx in presetUnitag) {
			if(tempHistory.indexOf(presetUnitag[idx]) == -1) {
				subPreset.push(presetUnitag[idx]);
			}
		}

		return tempHistory.concat(subPreset);
	}

	function getSearchHistories() {
		const cookieHistory = $.cookie("histories");
		return cookieHistory == undefined ? [] : cookieHistory.split(',');
	}

	function setUnitag(keywords, limit) {
		let tagsHTML = "";

		//tagsHTML += "<p><button id='tag' name='unitags' value='야구'>야구</button></p>"
		for(let idx = 0; idx < limit; idx++) {
			tagsHTML += "<p><button id='tag"+ idx +"' name='unitags' value='" + keywords[idx] + "'" +
					"style='{border: 2, outline: 2}'" +
			
					">"  + keywords[idx] + "</button></p>"
		}

		$("#unitags").html(tagsHTML);

		$("button[name='unitags']").each(function(i){
			$(this).click(function(e){
				e.preventDefault();
				$("#keyword").val($(this).val());
				$("#btnSearch").trigger("click");
			})
		});

	}

	function setSearchKeywordCookie(keyword, size, exfireDays) {

		tempHistory = getSearchHistories();
		console.log(tempHistory);

		const findIdx = tempHistory.indexOf(keyword)
		if ( findIdx > -1) {
			tempHistory.splice(findIdx, 1)
		}

		if(tempHistory.length > size) {
			tempHistory.pop();
		}

		tempHistory.unshift(keyword);

		$.cookie("histories", tempHistory, {expires: exfireDays, path: '/'});
	}

	function makeDivGroup(data) {

		let divGroup = "";
		console.log("div group("  + data.group + "), unizSN("  + data.unizSN + "), count("  + data.count + ")");
		divGroup += "<div id='group_" + data.unizSN + "' value="+ data.unizSN +" count="+ data.count + ">";
		divGroup += "<h3>#" + data.group + "</h3>";
		divGroup += makeDivVideo(data.videoList);
		divGroup += "</div>";

		return divGroup;
	}

	function makeDivVideo(videoList) {
		let divVideo = "";

		for(idx in videoList) {	// 배열이라 인덱스
			console.log("- videoList [" + idx + "]");
			vdata = videoList[idx];	// 단일 인덱스 개체는 인스턴스 객체

			//divVideo += "<div id='video_" + vdata.videoSN + "'>"; //클래스로 바꿔야한다.
			divVideo += "<a href='/video/"+ vdata.videoSN + "'>";
			divVideo += "<img src='https://i.ytimg.com/vi/"+ vdata.utbVideoID +"/maxresdefault.jpg' alt='" + vdata.title +"'></img>";
			divVideo += "</a>";
			//divVideo += '</div>';		// end video div
		}

		return divVideo;
	}
});