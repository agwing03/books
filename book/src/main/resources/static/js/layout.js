/**
 *  layout 생성
 */
jQuery(document).ready(function(){
	//nav
	fetchApi('/menu/getMenuList.do', 'POST', {clubNo:'1'}, 'nav')
	
	//공통검색
	jQuery("#top-bar").load("/cmmn/topBar.html")
	
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
		//네비 생성
		if (gbn === 'nav'){
			nav(data.menuList);
			
		//공통 search 결과 조회
		} else if (gbn === 'search'){ 
			return data.dataList;
			
		//목록 데이터 리턴
		} else if (gbn === 'dataList'){ 
			return data.dataList;
			
		//상세 데이터 리턴
		} else if (gbn === 'dataMap'){ 
			return data;
			
		//save DB 데이터 적재
		} else if (gbn === 'save'){
			alert(data.msg)
			location.reload()
			
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
	//메뉴 CSS 추가
	setTimeout(function(){
		if(subMenu !== '' || subMenu === 'NOT'){
			jQuery('#menu'+menu+' > .side-menu').addClass('side-menu--active')
			//이동경로
			jQuery('#breadcrumb1').text(menuUpperNm)
			jQuery('#breadcrumb2').text(menuNm)		
		}
		jQuery('#menuUl'+menu).addClass('side-menu__sub-open').css('display','block')
		jQuery('#menuSub'+subMenu).addClass('side-menu--active')
	}, 500);
	
	if(menuUrl){
		movePage(menuUrl, '')
	}
}

/** 
 *  HTML 페이지 이동
 */
function movePage(url, params) {
	let pageUrl = url
	if(params){
		pageUrl = pageUrl+'?'+params
	}
	location.href = pageUrl
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
	html =`
		<a href="/" class="intro-x flex items-center pl-5 pt-4">
		   <img alt="책과 함께하는 우리들만의 이야기" class="w-6" src="../../dist/images/logo.svg"> 
			<span class="hidden xl:block text-white text-lg ml-3"> 책과 사람 사이 </span> 
        </a> 
		<div class="side-nav__devider my-6"></div> 
			<ul>
        		<li id="menu0" onclick="menuMove('0','','HOME','','/home.html')">
        			<a href="#" class="side-menu">
						<div class="side-menu__icon"><i data-lucide="home"></i></div>
    					<div class="side-menu__title">HOME
							<div class="side-menu__sub-icon><i data-lucide="chevron-down"></i></div>
						</div>
					</a>
				</li>`
		
	for(var i = 0; i < data.length; i++){
		//4.대메뉴 닫기
		if(i != 0 && menuUpperNo != data[i].menuUpperNo){
			html +='</ul></li>'
		}
		//1.대메뉴 > 상위메뉴번호 담기
		if(data[i].menuLv == 1){ 
			menuUpperNo = data[i].menuNo
			menuUpperNm = data[i].menuNm
			menuUpperUrl = data[i].menuUrl
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
					'<li id="menu'+menuUpperNo+'" onclick="menuMove('+menuUpperNo+',\'\',\''+menuUpperNm+'\',\'\',\'\')">'+
			        '	<a href="javascript:void(0);" class="side-menu" >'+
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
					'<li id="menu'+menuUpperNo+'" onclick="menuMove('+menuUpperNo+',\'\',\''+menuUpperNm+'\',\'\',\''+menuUpperUrl+'\')">'+
			        '	<a href="javascript:void(0);" class="side-menu" >'+
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
				'	<li onclick="menuMove('+menuUpperNo+','+data[i].menuNo+',\''+menuUpperNm+'\',\''+data[i].menuNm+'\',\''+data[i].menuUrl+'\')">'+  
				'		<a href="javascript:void(0);" class="menuSub side-menu" id="menuSub'+data[i].menuNo+'">'+
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
    	'<i data-lucide="bookmark" class="mr-2"></i>'+ 
		'<ol class="breadcrumb">'+
		'	<li class="breadcrumb-item active" aria-current="page" id="breadcrumb1"></li>'+
		'	<li class="breadcrumb-item" id="breadcrumb2"></li>'+
		'</ol>'
	jQuery('#breadcrumb').append(html)
}
