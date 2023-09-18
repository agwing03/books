 /**
  *  layout 생성
  *  1. 메뉴생성 nav
  */
$(document).ready(function(){
	//nav
	fetchApi('/sys/getMenuList.do', 'POST', {clubNo:'1'}, 'nav')
})

/** 
 *  fetch 데이터 통신
 *  URL
 *  method  : POST, GET, PUT, DELETE
 *  body	: json
 *  gbn		: nav, aside, dataList, save
 */
async function fetchApi(url, method, body, gbn, headers = {}) {
	const options = {
		method: method,
		headers: {"Content-Type": "application/json", ...headers},
    	body: JSON.stringify(body)
    }
	const res = await fetch(url, options)
	const data = await res.json()
	if (res.ok) {
		if (gbn === 'nav'){ //side-nav html 생성
			nav(data.dataList);
		} else if (gbn === 'aside'){ //side html 생성
			aside(data.dataList);
		} else if (gbn === 'dataList'){ //list 데이터 리턴
			return data.dataList;
		} else if (gbn === 'save'){ //DB 데이터 적재
			alert('저장 되었씁니다.')
			search()
		}
	} else {
    	throw Error(data)
	}
}

/**
 *  사이드 메뉴 생성
 *  사이트 및 모바일 동시 생성 
 */
function nav(data){
	let menuUpperNo = ''
	let navHtml = ''
	/* 모바일 메뉴 */
	
	
	/* 사이드 메뉴 */
	menuUpperNo = ''
	navHtml = ''
	navHtml = 
		'<a href="" class="intro-x flex items-center pl-5 pt-4">'+ 
		'	<span class="hidden xl:block text-white text-lg ml-3"> 책과 사람 사이 </span>'+ 
        '</a>'+ 
		'<div class="side-nav__devider my-6"></div>'+ 
		'	<ul>'+ 
        '		<li>'+
        '			<a href="/main.do" class="side-menu side-menu--active">'+
		'				<div class="side-menu__icon">'+
		'						<i data-lucide="home"></i>'+
		'				</div>'+
    	'				<div class="side-menu__title">HOME'+
		'					<div class="side-menu__sub-icon>'+
		'						<i data-lucide="chevron-down"></i>'+ 
		'					</div>'+
		'				</div>'+
		'			</a>'+
		'		</li>'
	for(var i = 0; i < data.length; i++){
		//4.대메뉴 닫기
		if(i != 0 && menuUpperNo != data[i].menuUpperNo){
			navHtml +='</ul></li>'
		}
		//1.대메뉴 > 상위메뉴번호 담기
		if(data[i].menuLv == 1){ 
			menuUpperNo = data[i].menuNo
			//1.주메뉴 구분선
			if(data[i].menuLv == 1 && data[i].menuOrder == 6){
				navHtml += '<li class="side-nav__devider my-6"></li>'
			}	
		}  
		
		//2.대메뉴 생성
		if(data[i].menuLv == 1){
			navHtml += 	
				'<li>'+
		        '	<a href="javascript:void(0);" class="side-menu">'+
				'		<div class="side-menu__icon">'+
				'			<i data-lucide="'+data[i].icon+'"></i>'+
				'		</div>'+
		    	'		<div class="side-menu__title">'+data[i].menuNm+
				'			<div class="side-menu__sub-icon>'+ 
				'				<i data-lucide="chevron-down"></i>'+
				'			</div>'+
				'		</div>'+
				'	</a>'+
				'	<ul class="">'
		}
		//3.상위메뉴번호 같을 경우 소메뉴 생성
		if(data[i].menuLv == 2 && menuUpperNo == data[i].menuUpperNo){
			navHtml +=
				'	<li>'+
				'		<a href="'+data[i].menuUrl+'" class="side-menu">'+
                '			<div class="side-menu__icon">'+
                '				<i data-lucide="'+data[i].icon+'"></i>'+
                '			</div>'+
				'			<div class="side-menu__title">'+data[i].menuNm+'</div>'+
				'		</a>'+
				'	</li>' 	
		}	
	}	
	navHtml += '</ul></div>'
    $("#side-nav").append(navHtml);
    
    //디자인 코어 js 반영
    const script = document.createElement('script');
    script.src = 'dist/js/app.js';
    document.body.appendChild(script);
}

















/**
 *  공통코드 조회
 *  공통코드		: 코드ID
 *  화면 HTML ID	: 태그ID 
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

/**
 *  SEARCH 데이터 조회
 *  텍스트 구분 	: srchGbn
 *  텍스트 	: srchText 
 *  기간 시작일 	: srchStartDt 
 * 	기간 종료일 	: srchEndDt
 * 	기타 조건1	: srchParam1 
 * 	기간 조건2 	: srchParam2 
 * 	기간 조건3 	: srchParam3  
 */
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

/**
 *  SAVE 데이터 조회
 *  URL	
 *  gbn : I, U, D
 */
function save(url, gbn){
	let params = {clubNo:'1'}//클럽번호
	let data = $("#saveData tr") //엘리먼트
	let dataString = '';
	if(data.length > 0){
		for(var i = 0; i < data.length; i++){
			let dataTr = data.eq(i)
			for(var j = 0; j < dataTr.children('td').length; j++){
				let dataTd = dataTr.children('td').eq(j).find('input').val()
				if(j == 0 && gbn != 'I'){
					dataString += dataTd
				}else if(gbn != 'D'){//삭제는 key만 필요함
					dataString += ','+dataTd	
				}
			}
			dataString += '/'
		}			
	}
	params.saveGbn = gbn
	params.dataString = dataString
	//데이터 저장 조회
	fetchApi(url, 'POST', params, 'save')
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

