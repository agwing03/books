 /**
  *  layout 생성
  *  1. 메뉴생성 nav
  */
//화면 선 로드
jQuery(document).ready(function(){
	//nav
	fetchApi('/menu/getMenuList.do', 'POST', {clubNo:'1'}, 'nav')
	//공통검색
	jQuery("#top-bar").load("/cmmn/topBar.html")
	
	pageMove('', '', '운영관리', '운영관리', '/club/admin')
})

/**
 *  Search 데이터 조회
 *  텍스트 구분 	: srchGbn
 *  텍스트 	: srchText 
 *  기간 시작일 	: srchStartDt 
 * 	기간 종료일 	: srchEndDt
 * 	기타 조건1	: srchParam1 
 * 	기간 조건2 	: srchParam2 
 * 	기간 조건3 	: srchParam3  
 */
function srchParam(){
	let params = {}
	console.log(jQuery('#srchGbn').val())
	//검색구분
    let srchGbn = jQuery('#srchGbn').val()
    if(srchGbn != '' && srchGbn != undefined){
		params.srchGbn = srchGbn 
	}
	//검색어 
    let srchText = jQuery('#srchText').val()
    if(srchText != '' && srchText != undefined){
		params.srchText = srchText 
	}
	//검색일자
    let srchDt = jQuery('#srchDt').val().trim()
    srchDt = srchDt.split("-") 
    let srchStartDt = srchDt[0].replace( /./gi, '')
    let srchEndDt = srchDt[1].replace( /./gi, '')
    if(srchStartDt != '' && srchStartDt != undefined){
		params.srchStartDt = srchStartDt 
	}
    if(srchEndDt != '' && srchEndDt != undefined){
		params.srchEndDt = srchEndDt
	}
	//기타1
    let srchParam1 = jQuery('#srchParam1').val()
    if(srchParam1 != '' && srchParam1 != undefined){
		params.srchParam1 = srchParam1
	}
	//기타2
    let srchParam2 = jQuery('#srchParam2').val()
    if(srchParam2 != '' && srchParam2 != undefined){
		params.srchParam2 = srchParam2
	}
	
	console.log(params)
	return params
}


/**
 *  검색 조건 리셋 
 */
function srchReset(){
	jQuery('#srchText').val("")
}


/**
 *  날짜 기간 _ 1개월/3개월 
 */
function setSrchDate(month){
	let date1 = dayjs().add(month, "month").format('YYYY.MM.D')
	let date2 = dayjs().format('YYYY.MM.D');
	console.log(date1+" - " +date2)
	jQuery('#srchDt').val(date1+" - " +date2)
}


/**
 *  SAVE 데이터 조회
 *  URL	
 *  gbn : I, U, D
 */
function save(url, gbn){
	let params = {clubNo:'1'}//클럽번호
	let data = jQuery("#saveData tr") //엘리먼트
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
	jQuery("#layer_shadow").show()
	jQuery("#layerPupup").fadeIn();
 	//화면 중앙 띄우기
	let target = jQuery("#layerPupup");
	var left = ( jQuery(window).scrollLeft() + (jQuery(window).width() - target.width()) / 2 );
	var top = ( jQuery(window).scrollTop() + (jQuery(window).height() - target.height()) / 2 );
	//CSS
	target.css({'left':left,'top':top, 'position':'absolute'});
	jQuery('body').css('position','relative').append(target);
}
/* 레이어 팝업 닫기 */
function layerClose(){
	jQuery("#layer_shadow").hide()
	jQuery('#layerPupup').hide();
}


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
		//side-nav html 생성
		if (gbn === 'nav'){
			nav(data.menuList);
			
		//side html 생성
		} else if (gbn === 'aside'){ 
			aside(data.dataList);
			
		//list 데이터 리턴
		} else if (gbn === 'dataList'){ 
			return data.dataList;
			
		//save DB 데이터 적재
		} else if (gbn === 'save'){
			alert(data.msg)
			location.reload()
			
		//공통 search 결과 조회
		} else if (gbn === 'search'){ 
			return data.dataList;
		
		//공통 활동지역 결과 조회
		} else if (gbn === 'codeArea'){ 
			return data.codeList;
		}
	} else {
    	throw Error(data)
	}
}

 
/** 
 *  fetch HTML 삽입
 */
async function fetchHtml(url) {
    return await (await fetch(url)).text();
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
		if(data.codeList.length > 0){
			jQuery("#"+target).empty()
			//optionHtml = '<option value="">전체</option>'
			for(var i = 0; i < data.codeList.length; i++){
				optionHtml += '<option value="'+data.codeList[i].code+'">'+data.codeList[i].codeNm+'</option>'
			}
			jQuery("#"+target).append(optionHtml);
		}
	} else {
    	throw Error(data)
	}
}



/**
 * 메뉴 페이지 이동 
 * 이벤트 디자인 적용
 */
async function pageMove(subMenu, menu, menuNm, menuUpperNm, menuUrl){
	//Html 삽입
	if(menuUrl != ''){
		let html = await fetchHtml(menuUrl+'.html')
		jQuery('#importHtml').empty()
		jQuery('#importHtml').html(html)
			
		//메뉴 클래스 적용
		//jQuery('.menuUl').removeClass('side-menu__sub-open').css('display','none')
		//jQuery('.side-menu').removeClass('side-menu--active')
		//jQuery('#menu'+menu).addClass('side-menu--active')
		//jQuery(subMenu).addClass('side-menu--active')
		//jQuery('#menuUl'+menu).addClass('side-menu__sub-open').css('display','block')
			
		//이동경로
		jQuery('#breadcrumb1').text(menuUpperNm)
		jQuery('#breadcrumb2').text(menuNm)		
	}
}



/**
 *  사이드 메뉴 생성
 *  사이트 및 모바일 동시 생성 
 */
function nav(data){
	let menuUpperNo = ''
	let menuUpperNm = ''
	
	/* 모바일 메뉴 */
	let html = 
		'<div class="mobile-menu-bar">'+
        '	<a href="/" class="flex mr-auto">'+
		'		<img alt="Midone - HTML Admin Template" class="w-6" src="dist/images/logo.svg">'+
		'	</a>'+
		'	<a href="javascript:void(0);" class="mobile-menu-toggler">'+ 
		'		<i data-lucide="bar-chart-2" class="w-8 h-8 text-white transform -rotate-90"></i>'+ 
		' 	</a>'+
		'</div>'+
		'<div class="scrollable">'+
		'	<a href="javascript:void(0);" class="mobile-menu-toggler">'+
		'		<i data-lucide="x-circle" class="w-8 h-8 text-white transform -rotate-90"></i>'+
		'	</a>'+
		'	<ul class="scrollable__content py-2">'+
		'		<li>'+
		'			<a href="javascript:;" class="menu">'+
		'				<div class="menu__icon"><i data-lucide="box"></i></div>'+
		'				<div class="menu__title"> Menu'+ 
		'					<i data-lucide="chevron-down" class="menu__sub-icon "></i>'+
		'				</div>'+
		'			</a>'+
		'	<ul class="side-menu__sub-open">'
	for(var i = 0; i < data.length; i++){
		//4.대메뉴 닫기
		if(i != 0 && menuUpperNo != data[i].menuUpperNo){
			html +='</ul></li>'
		}
		//1.대메뉴 > 상위메뉴번호 담기
		if(data[i].menuLv == 1){ 
			menuUpperNo = data[i].menuNo
			//5.주메뉴 구분선
			if(data[i].menuLv == 1 && data[i].menuOrder == 6){
				html += '<li class="side-nav__devider my-6"></li>'
			}	
		}  
		//2.대메뉴 생성
		if(data[i].menuLv == 1){
			html += 	
				'<li>'+
				'	<a href="javascript:void(0);" class="menu">'+
				'		<div class="menu__icon"><i data-lucide="'+data[i].icon+'"></i></div>'+
				'		<div class="menu__title">'+data[i].menuNm+
				'			<i data-lucide="chevron-down" class="menu__sub-icon "></i>'+
				'		</div>'+
				'	</a>'+
				'	<ul class="">'
		}
		//3.상위메뉴번호 같을 경우 소메뉴 생성
		if(data[i].menuLv == 2 && menuUpperNo == data[i].menuUpperNo){
			html +=
				'<li>'+
				'	<a href="'+data[i].menuUrl+'" class="menu">'+
				'		<div class="menu__icon"><i data-lucide="'+data[i].icon+'"></i></div>'+
				'		<div class="menu__title">'+data[i].menuNm+'</div>'+
				'	</a>'+
				'</li>'
		}
	}
	html += '</ul></div>'
	jQuery("#mobile-menu").append(html);
		
	/* 사이드 메뉴 */
	menuUpperNo = ''
	menuUpperNm = ''
	html = 
		'<a href="/" class="intro-x flex items-center pl-5 pt-4">'+
		'   <img alt="책과 함께하는 우리들만의 이야기" class="w-6" src="dist/images/logo.svg">'+ 
		'	<span class="hidden xl:block text-white text-lg ml-3"> 책과 사람 사이 </span>'+ 
        '</a>'+ 
		'<div class="side-nav__devider my-6"></div>'+ 
		'	<ul>'+ 
        '		<li>'+
        '			<a href="#" class="side-menu side-menu--active" id="menu0" onclick="pageMove(this,0,\'HOME\',\'\');">'+
		'				<div class="side-menu__icon"><i data-lucide="home"></i></div>'+
    	'				<div class="side-menu__title">HOME'+
		'					<div class="side-menu__sub-icon><i data-lucide="chevron-down"></i></div>'+
		'				</div>'+
		'			</a>'+
		'		</li>'
	for(var i = 0; i < data.length; i++){
		//4.대메뉴 닫기
		if(i != 0 && menuUpperNo != data[i].menuUpperNo){
			html +='</ul></li>'
		}
		//1.대메뉴 > 상위메뉴번호 담기
		if(data[i].menuLv == 1){ 
			menuUpperNo = data[i].menuNo
			menuUpperNm = data[i].menuNm
			//5.주메뉴 구분선
			if(data[i].menuLv == 1 && data[i].menuOrder == 6){
				html += '<li class="side-nav__devider my-6"></li>'
			}	
		}  
		//2.대메뉴 생성
		if(data[i].menuLv == 1){
			//하위 메뉴 존재여부			
			if(data[i].subMenuCnt > 0){ 
				html += 	
					'<li>'+
			        '	<a href="javascript:void(0);" class="side-menu" id="menu'+menuUpperNo+'" onclick="pageMove(this,'+menuUpperNo+',\''+data[i].menuNm+'\',\'\',\''+data[i].menuUrl+'\');">'+
					'		<div class="side-menu__icon">'+
					'			<i data-lucide="'+data[i].icon+'"></i>'+
					'		</div>'+
			    	'		<div class="side-menu__title">'+data[i].menuNm+
					'			<div class="side-menu__sub-icon"><i data-lucide="chevron-down"></i></div>'+
					'		</div>'+
					'	</a>'+
					'	<ul id="menuUl'+menuUpperNo+'" class="menuUl">'
			}else{
				html += 	
					'<li>'+
			        '	<a href="javascript:void(0);" class="side-menu" id="menu'+menuUpperNo+'" onclick="pageMove(this,'+menuUpperNo+',\''+data[i].menuNm+'\',\'\',\''+data[i].menuUrl+'\');">'+
					'		<div class="side-menu__icon">'+
					'			<i data-lucide="'+data[i].icon+'"></i>'+
					'		</div>'+
			    	'		<div class="side-menu__title">'+data[i].menuNm+'</div>'+
					'	</a>'+
					'	<ul id="menuUl'+menuUpperNo+'" class="menuUl">'
			}
			
		}
		//3.상위메뉴번호 같을 경우 소메뉴 생성
		if(data[i].menuLv == 2 && menuUpperNo == data[i].menuUpperNo){
			html +=
				'	<li>'+
				'		<a href="javascript:void(0);" class="side-menu" onclick="pageMove(this,'+menuUpperNo+',\''+data[i].menuNm+'\',\''+menuUpperNm+'\',\''+data[i].menuUrl+'\');">'+
                '			<div class="side-menu__icon"><i data-lucide="'+data[i].icon+'"></i></div>'+
				'			<div class="side-menu__title">'+data[i].menuNm+'</div>'+
				'		</a>'+
				'	</li>' 	
		}	
	}	
	html += '</ul></div>'
    jQuery("#side-nav").append(html);
    
    /* 이동경로 */
    html = 
		'<ol class="breadcrumb">'+
		'	<li class="breadcrumb-item active" aria-current="page" id="breadcrumb1"></li>'+
		'	<li class="breadcrumb-item" id="breadcrumb2">HOME</li>'+
		'</ol>'
	jQuery('#breadcrumb').append(html)
}