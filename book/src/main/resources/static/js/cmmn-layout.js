/**
 *  화면 공통 레이아웃 생성
 */
jQuery(document).ready(function(){
	/* 로고 : Logo */ 
	cmmnLayoutLogo()
	
	/* 이동경로 : Breadcrumb */ 
	cmmnLayoutBreadcrumb('모임','http://localhost:8080','모임후기','http://localhost:8080')
	
	/* 검색 : Search*/ 
    jQuery(".search").find("input").each(function() {
      	jQuery(this).on("focus", function() {
        	jQuery(".search-result").addClass("show")
      	})
      	jQuery(this).on("focusout", function() {
        	jQuery(".search-result").removeClass("show")
      	})
	})
	// 검색 조회
	jQuery("#cmmn-search").on("keyup",function(key){
        if(key.keyCode==13) {
            let params = {}
			params.srchWord = $('#cmmn-search').val()
			fetchLayoutApi('/getSearch.do', 'POST', params, 'search')
        }
    })
    
    /* 알림 : Notifications */
})


/**
 *  로고(화면공통)
 *  - 모임정보 및 이미지 조회
 */
function cmmnLayoutLogo() {
	let html = `
		<a href="" class="-intro-x hidden md:flex xl:w-[180px]">
			<img class="w-6" src="dist/images/logo.svg" alt="Enigma Tailwind HTML Admin Template">
		    <span class="ml-3 text-lg text-white hidden xl:block">책과 사람사이</span>
		</a>`
	jQuery("#cmmn-layout-logo").append(html);
}

/**
 *  이동경로(화면공통)
 *  - 메뉴 이동시 생성
 *  - param : 메뉴 대분류명 및 url, 메뉴 소분류명 및 url 
 */
 function cmmnLayoutBreadcrumb(menuNm1, menuUrl1, menuNm2, menuUrl2) {
	let html = `
		<li class="">
            <a href="`+menuUrl1+`">`+menuNm1+`</a>
        </li>
        <li class="relative ml-5 pl-0.5 before:content-[''] before:w-[14px] before:h-[14px] before:bg-chevron-white before:transform before:rotate-[-90deg] before:bg-[length:100%] before:-ml-[1.125rem] before:absolute before:my-auto before:inset-y-0 dark:before:bg-chevron-white text-white/70">
            <a href="`+menuUrl2+`">`+menuNm2+`</a>
        </li>`
	jQuery("#cmmn-layout-breadcrumb").append(html);
}

/**
 *  검색 결과(화면공통)
 *  - 검색결과 생성
 */
function cmmnSearchResult(data) {
	console.log(data)
	//비우기
	jQuery("#cmmn-search-result").empty()
	if(data != null){
		//변수
		let meetingHtml = ''
		let userHtml = ''
		let booksHtml = ''
		let meetingCnt = 0
		let userCnt = 0
		let booksCnt = 0
		//기본영역
		let html = `<div class="box w-[450px] p-5">`
		if(data.length){
			for(var i = 0; i < data.length; i++){
				//모임
				if(data[i].gbn == 'meeting'){
					meetingHtml+=` 
						<a class="mt-2 flex items-center" href="">
		                    <div class="flex h-8 w-8 items-center justify-center rounded-full bg-pending/10 text-pending">
		                        <i data-tw-merge="" data-lucide="users" class="stroke-1.5 h-4 w-4"></i>
		                    </div>
		                    <div class="ml-3">`+data[i].data1+`</div>
		                </a>`
		                meetingCnt++
				//사용자 
				}else if(data[i].gbn == 'member'){
					userHtml+=`
		                <a class="mt-2 flex items-center" href="">
		                    <div class="image-fit h-8 w-8">
		                        <img class="rounded-full" src="dist/images/fakers/profile-1.jpg" alt="Midone Tailwind HTML Admin Template">
		                    </div>
		                    <div class="ml-3">`+data[i].data1+`</div>
		                    <div class="ml-auto w-64 truncate text-right text-xs text-slate-500">`+data[i].data2+`</div>
		                </a>`
		                userCnt++
				//도서
				}else if(data[i].gbn == 'books'){
					booksHtml+=`
						<a class="mt-2 flex items-center" href="">
		                    <div class="image-fit h-8 w-8">
		                        <img class="rounded-full" src="dist/images/fakers/profile-1.jpg" alt="Midone Tailwind HTML Admin Template">
		                    </div>
		                    <div class="ml-3 w-48 truncate">`+data[i].data1+`</div>
		                    <div class="ml-auto w-48 truncate text-right text-xs text-slate-500">`+data[i].data2+`</div>
		                </a>`
		                booksCnt++
				}
			}
			if(meetingCnt > 0){
				html += `<div class="mb-2 font-medium">Meeting</div>
	           	  		 <div class="mb-5">`+meetingHtml+`</div>`	
			}
	        if(userCnt > 0){
				html += `<div class="mb-2 font-medium">Member</div>
	           	  		 <div class="mb-5">`+userHtml+`</div>`
			}
			if(booksCnt > 0){
				html += `<div class="mb-2 font-medium">Books</div>`+booksHtml
			}
	    }else{
			html += `<div class="mb-2 font-medium">검색 결과가 없습니다.</div>`
		}
		//본문 삽입
		html += `</div>`
		jQuery("#cmmn-search-result").append(html)
	}
}

/**
 * 알림 결과(화면공통)
 *  - 메세지 목록 생성
 */



/** 
 *  layout fetch 데이터 통신
 *  URL
 *  method  : POST, GET, PUT, DELETE
 *  body	: json
 *  gbn		: nav, aside, dataList, save
 */
async function fetchLayoutApi(url, method, body, gbn, headers = {}) {
	const options = {
		method: method,
		headers: {"Content-Type": "application/json", ...headers},
    	body: JSON.stringify(body)
    }
	const res = await fetch(url, options)
	const data = await res.json()
	console.log(data != null)
	if (res.ok) {
		if(data != null){
			// 공통 검색
			if (gbn === 'search'){
				cmmnSearchResult(data.dataList)
				
			//공통 search 결과 조회
			} else if (gbn === 'search1'){ 
				return data.dataList
			}
		}else{
			alert("데이터 없음")
			return false
		}
	} else {
    	throw Error(data)
	}
}