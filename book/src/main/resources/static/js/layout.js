/**
 *  layout 생성
 */
jQuery(document).ready(function(){
	//nav
	fetchApi('/menu/getMenuList.do', 'POST', {clubNo:'1'}, 'nav')
	//공통검색
	jQuery("#top-bar").load("/cmmn/topBar.html")
	//초기페이지 페이지 이동
	menuMove('', '', '운영관리', '운영관리', '/club/admin')
})

jQuery(window).on('load', function () {
	//디자인 코어 JS 반영
    const script = document.createElement('script');
    script.src = '/dist/js/app.js';
    document.body.appendChild(script);
    
    //디자인 코어 CSS 반영
    const link = document.createElement('link');
    link.rel = 'stylesheet'
    link.href = '/dist/css/app.css';
	document.body.appendChild(link);
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
		//side-nav html 생성
		if (gbn === 'nav'){
			nav(data.menuList);
			
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
 * 레이어 팝업
 * 수정 필요 ++ 
 */
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
function layerClose(){
	jQuery("#layer_shadow").hide()
	jQuery('#layerPupup').hide();
}

/** 
 *  HTML 페이지 이동
 */			
async function pageMove(url){
	await fetchHtml(url+'.html')
}


/** 
 *  fetch HTML 삽입
 */
async function fetchHtml(url) {
    return await (await fetch(url)).text();
}


/**
 * 메뉴 페이지 이동 
 * 이벤트 디자인 적용
 */
function menuMove(menu, subMenu, menuUpperNm, menuNm, menuUrl){
	console.log(menu)
	console.log(subMenu)
	//대메뉴 클래스 제거 
	jQuery('.menuUl').removeClass('side-menu__sub-open').css('display','none')
	jQuery('.side-menu').removeClass('side-menu--active')
	
	//대메뉴 추가
	jQuery('#menu'+menu).addClass('side-menu--active')
	jQuery('#menu'+menu+' > ul').addClass('side-menu__sub-open').css('display','block')
	//소메뉴 추가
	jQuery('#menuSub'+subMenu).addClass('side-menu--active')
		
	//이동경로
	jQuery('#breadcrumb1').text(menuUpperNm)
	jQuery('#breadcrumb2').text(menuNm)		
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
		'		<img alt="Midone - HTML Admin Template" class="w-6" src="../../dist/images/logo.svg">'+
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
		'   <img alt="책과 함께하는 우리들만의 이야기" class="w-6" src="../../dist/images/logo.svg">'+ 
		'	<span class="hidden xl:block text-white text-lg ml-3"> 책과 사람 사이 </span>'+ 
        '</a>'+ 
		'<div class="side-nav__devider my-6"></div>'+ 
		'	<ul>'+ 
        '		<li>'+
        '			<a href="#" class="side-menu side-menu--active" id="menu0" onclick="menuMove(this,0,\'HOME\',\'\');">'+
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
			        '	<a href="javascript:void(0);" class="side-menu" id="menu'+menuUpperNo+'" onclick="menuMove('+menuUpperNo+',\'\',\''+menuUpperNm+'\',\'\',\'\')">'+
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
			        '	<a href="javascript:void(0);" class="side-menu" id="menu'+menuUpperNo+'" onclick="menuMove('+menuUpperNo+',\'\',\''+menuUpperNm+'\',\'\',\'\')">'+
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
				'		<a href="javascript:void(0);" class="side-menu" id="menuSub'+data[i].menuNo+'" onclick="menuMove('+menuUpperNo+','+data[i].menuNo+',\''+menuUpperNm+'\',\''+data[i].menuNm+'\',\'\')">'+
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
