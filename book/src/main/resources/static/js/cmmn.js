/* header, footer, aside, nav */
$(document).ready(function(){
	$('header').load('/cmmn/header.html');
	//$('footer').load('/cmmn/footer.html');
	
	//nav
	fetchApi('/sys/getMenuList.do', 'POST', {clubNo:'1'}, 'nav')
	//aside
	fetchApi('/sys/getMenuList.do', 'POST', {clubNo:'1'}, 'aside')
})

/* 전역변수 */
let list = null;

/* fetch 데이터 통신 */
async function fetchApi(url, method, body, gbn, headers = {}) {
	const options = {
		method: method,
		headers: {"Content-Type": "application/json", ...headers},
    	body: JSON.stringify(body)
    }
	const res = await fetch(url, options)
	const data = await res.json()
	if (res.ok) {
		if (gbn === 'nav'){
			nav(data.dataList);
		} else if (gbn === 'aside'){
			aside(data.dataList);
		} else if (gbn === 'dataList'){
			return data.dataList;
		}
	} else {
    	throw Error(data)
	}
}

/* 네비 메뉴 생성 */
function nav(data){
	let menuUpperNo = ''
	let navHtml = 
		'<ul>'+
		'	<li class="home"><a href="#">HOME</a></li>'
	for(var i = 0; i < data.length; i++){
		//4.대메뉴 닫기
		if(i != 0 && menuUpperNo != data[i].menuUpperNo){
			navHtml += '</div></li>'
		}
		//1.대메뉴 > 상위메뉴번호 담기
		if(data[i].menuLv == 1){ 
			menuUpperNo = data[i].menuNo  
		}
		//2.대메뉴 생성
		if(data[i].menuLv == 1){
			navHtml += 	
				'	<li class="dropdown">'+
				'		<div class="dropdown-menu">'+data[i].menuNm+'</div>'+
				'		<div class="dropdown-content">'	
		}
		//3.상위메뉴번호 같을 경우 소메뉴 생성
		if(data[i].menuLv == 2 && menuUpperNo == data[i].menuUpperNo){
			navHtml += '<a href="'+data[i].menuUrl+'">'+data[i].menuNm+'</a>'
		}	
	}	
	navHtml += '<li class="alarm"><a href="#">알림</a></li></ul>'
    $("nav").append(navHtml);
}

/* 사이드 메뉴 생성 */
function aside(data){
	let menuUpperNo = ''
	let navHtml = 
		'<div class="side-bar">'+
 		'	<div class="side-bar-icon-box">'+
    	'		<div class="side-bar-icon">'+
      	'			<div></div>'+
      	'			<div></div>'+
      	'			<div></div>'+
    	'		</div>'+
  		'	</div>'+
  		'	<ul>'
	for(var i = 0; i < data.length; i++){
		//4.대메뉴 닫기
		if(i != 0 && menuUpperNo != data[i].menuUpperNo){
			navHtml +='</ul></li>'
		}
		//1.대메뉴 > 상위메뉴번호 담기
		if(data[i].menuLv == 1){ 
			menuUpperNo = data[i].menuNo  
		}
		//2.대메뉴 생성
		if(data[i].menuLv == 1){
			navHtml += 	
				'	<li>'+
      			'		<a href="#"><i class="fa-solid fa-cat"></i>'+data[i].menuNm+'</a>'+
      			'		<ul>'	
		}
		//3.상위메뉴번호 같을 경우 소메뉴 생성
		if(data[i].menuLv == 2 && menuUpperNo == data[i].menuUpperNo){
			navHtml +='<li><a href="'+data[i].menuUrl+'">'+data[i].menuNm+'</a></li>' 	
		}	
	}	
	navHtml += '</ul></div>'
    $("aside").append(navHtml);
}

/**
 *  공통코드 조회
 *  필수 param : 코드ID, option 삽입할 태그ID 
 */
async function cmmnCode(codeId, target, headers = {}) {
	const param = {codeId:codeId}
	let optionHtml = null 
	const options = {
		method: 'POST',
		headers: {"Content-Type": "application/json", ...headers},
    	body: JSON.stringify(param)
    }
	const res = await fetch('/code/getCmmnCodeDtlList.do', options)
	const data = await res.json()
	if (res.ok) {
		if(data.dataList.length > 0){
			optionHtml = '<option value="">전체</option>'
			for(var i = 0; i < data.dataList.length; i++){
				optionHtml += '<option value="'+data.dataList[i].code+'">'+data.dataList[i].codeNm+'</option>'
			}
			$("#"+target).append(optionHtml);
		}
	} else {
    	throw Error(data)
	}
}

// 목록 데이터 조회
function search(){
	let params = {clubNo:'1'}
	
    let srchGbn = $('#srchGbn').val()
    if(srchGbn != '' && srchGbn != undefined){
		params.srchGbn = srchGbn 
	}
    let srchText = $('#srchText').val()
    if(srchText != '' && srchText != undefined){
		params.srchText = srchText 
	}
    let srchStartDt = $('#srchStartDt').val()
    if(srchStartDt != '' && srchStartDt != undefined){
		params.srchStartDt = srchStartDt 
	}
    let srchEndDt = $('#srchEndDt').val()
    if(srchEndDt != '' && srchEndDt != undefined){
		params.srchEndDt = srchEndDt
	}
    let srchParam1 = $('#srchParam1').val()
    if(srchParam1 != '' && srchParam1 != undefined){
		params.srchParam1 = srchParam1
	}
    let srchParam2 = $('#srchParam2').val()
    if(srchParam2 != '' && srchParam2 != undefined){
		params.srchParam2 = srchParam2
	}
	//화면 목록 조회
	srchList(params)
}

/* 레이어 팝업 */
function layerPopup(){
	$("#layer_shadow").show()
	$("#layerPupup").fadeIn();
 	//화면 중앙 띄우기
	let target = $("#layerPupup");
	var left = ( $(window).scrollLeft() + ($(window).width() - target.width()) / 2 );
	var top = ( $(window).scrollTop() + ($(window).height() - target.height()) / 2 );
	//CSS
	target.css({'left':left,'top':top, 'position':'absolute'});
	$('body').css('position','relative').append(target);
}
/* 레이어 팝업 닫기 */
function layerClose(){
	$("#layer_shadow").hide()
	$('#layerPupup').hide();
}