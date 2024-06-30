/**
 *  화면 공통 레이아웃 생성
 */
let clubNo = 1
let memberNo = 2
let meetingNo = ''

jQuery(document).ready(function(){
	
	/* 프로필 조회 */
	cmmnProfile()
	
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
			params.srchWord = jQuery('#cmmn-search').val()
			fetchLayoutApi('/getSearch.do', 'POST', params, 'search')
        }
    })
    
	/* 공통 dropdown */
    jQuery("body").on("click", function(event) {
      	let dropdown = jQuery(event.target).closest(".dropdown")
      	let dropdownId = jQuery(dropdown).attr("id")
      	let dropdownToggle = jQuery(dropdown).find("[data-tw-toggle='dropdown']")
      	let dropdownBox = jQuery(dropdown).find(".dropdown-menu").first()
      	let activeDropdownBox = jQuery(event.target).closest(".dropdown-menu").first()
      	let dismissButton = jQuery(event.target).data("tw-dismiss")
      	//hide
      	if (!jQuery(dropdown).length && !jQuery(activeDropdownBox).length || jQuery(dropdownToggle).length && !jQuery(dropdownBox).length || dismissButton == "dropdown") {
        	hide()
        //show
      	} else if (!jQuery(activeDropdownBox).length) {
			let params = {}
			
			/* 알림 : Notifications */
			if(dropdownId === "cmmn-alarm"){
				//데이터 조회
				params.memberNo = memberNo
				fetchLayoutApi('/getAlarm.do', 'POST', params, 'alarm')
				show(dropdown)
			}else{
				show(dropdown)
			}
    	}
	})
    // ESC 드랍 팝업 닫기
    document.addEventListener("keydown", function(event) {
      if (event.code == "Escape") {
        hide()
      }
    })
})

/**
 *  사용자 프로필(화면공통)
 *  - 사용정보 및 이미지 조회
 */
function cmmnProfile() {
	let params = {}
	//데이터 조회
	params.memberNo = memberNo
	fetchLayoutApi('/getMemberProfile.do', 'POST', params, 'profile')
}
function cmmnProfileResult(data) {
	let html = `<img src="`+data.profileImg+`" alt="`+data.memberNm+`">`
	jQuery("#cmmn-profile-img").append(html)
	jQuery("#cmmn-profile-name").text(data.memberNm)
	jQuery("#cmmn-profile-intro").text(data.intro)  
}

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
	jQuery("#cmmn-layout-logo").append(html) 
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
	jQuery("#cmmn-layout-breadcrumb").append(html) 
}

/**
 *  사이드 메뉴(화면공통)
 */
function cmmnSideMenu() {
}

/**
 *  검색 결과(화면공통)
 *  - 검색결과 생성
 */
function cmmnSearchResult(data) {
	//console.log(data)
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
		                        <img class="rounded-full" src="dist/images/fakers/profile-1.jpg" alt="`+data[i].data1+`">
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
		                        <img class="rounded-full" src="dist/images/fakers/profile-1.jpg" alt="`+data[i].data1+`">
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
 *  - 알림 메세지 목록 생성
 */
function cmmnAlarmResult(data) {
	//console.log(data)
	
	//비우기
	jQuery("#cmmn-alarm-result").empty()

	//기본영역
	let html = `<div class="mb-5 font-medium">Message Box</div>`
	if(data.length){
		for(var i = 0; i < data.length; i++){
			html += `
				<div class="cursor-pointer relative flex items-center mb-5">
					<div class="image-fit relative mr-1 h-12 w-12 flex-none">
                        <img class="rounded-full" src="`+data[i].sendMemberImg+`" alt="Midone Tailwind HTML Admin Template">
                        <div class="absolute bottom-0 right-0 h-3 w-3 rounded-full border-2 border-white bg-success dark:border-darkmode-600"></div>
                    </div>
                    <div class="ml-2 overflow-hidden">
                        <div class="flex items-center">
                            <a class="mr-5 truncate font-medium w-32" href="">`+data[i].sendMemberNm+`</a>
                            <div class="ml-auto whitespace-nowrap text-xs text-slate-400">`+data[i].regDt+`</div>
                        </div>
                        <div class="mt-0.5 w-full truncate text-xs text-slate-500">`+data[i].msgCn+`</div>
                    </div>
                </div>`
		}
	}else{
		html += `<div class="cursor-pointer relative flex items-center font-medium">검색된 메세지가 없습니다.</div>`
	}
	
	//본문 삽입
	jQuery("#cmmn-alarm-result").append(html)
}


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
	
	console.log(data)
	
	if (res.ok) {
		if(data != null){
			// 화면 프로필
			if (gbn === 'profile'){
				cmmnProfileResult(data.dataMap)
				
			// 검색 공통
			} else if (gbn === 'search'){
				cmmnSearchResult(data.dataList)
				
			// 알림박스 공통
			} else if (gbn === 'alarm'){ 
				cmmnAlarmResult(data.dataList)
			}
		}
	} else {
    	throw Error(data)
	}
}

/** 
 *  dropdown 공통
 *  method  
 *    : show() -> 드랍 팝업 보이기
 *	  : hide() -> 드랍 팝업 숨기기
 */
async function show(dropdown) {
	let dropdownBox = jQuery(dropdown).find(".dropdown-menu").first()
  	let dropdownToggle = findVisibleDropdownToggle(
    	jQuery(dropdown).find("[data-tw-toggle='dropdown']")
  	)
  	let placement = jQuery(dropdown).data("tw-placement") ? jQuery(dropdown).data("tw-placement") : "bottom-end"
  	let randId = "_" + Math.random().toString(36).substr(2, 9)
  	hide() 
  	if (jQuery(dropdownBox).length) {
    	jQuery(dropdownToggle).attr("aria-expanded", true)
    	jQuery(dropdown).css("position") == "static" ? jQuery(dropdown).css("position", "relative") : ""
    	jQuery(dropdownBox).show()
    	jQuery(dropdownBox).css("width", jQuery(dropdownBox).css("width"))
    	jQuery('<div data-dropdown-replacer="' + randId + '"></div>').insertAfter(
      		dropdownBox
    	)
    	jQuery(dropdownBox).attr("id", randId).appendTo("body")
    	jQuery(".modal.show").each(function() {
      		if (jQuery(this).find('[data-dropdown-replacer="' + randId + '"]')) {
        		jQuery(dropdownBox).css("z-index", jQuery(this).css("z-index"))
      		}
    	})
    	Popper.createPopper(dropdownToggle[0], dropdownBox[0], {
      		placement
    	})
    	jQuery(dropdownBox).addClass("show")
    	const event = new Event("show.tw.dropdown")
    	jQuery(dropdown)[0].dispatchEvent(event)
    	setTimeout(() => {
	      	const event2 = new Event("shown.tw.dropdown")
	      	jQuery(dropdown)[0].dispatchEvent(event2)
    	}, 200)
  	}
}
function hide() {
  	jQuery(".dropdown-menu").each(async function() {
    	if (jQuery(this).attr("id") !== void 0 && jQuery('[data-dropdown-replacer="' + jQuery(this).attr("id") + '"]').length && jQuery(this).data("dropdown-programmatically") === void 0) {
      		let randId = jQuery(this).attr("id") 
      		let dropdownToggle = jQuery('[data-dropdown-replacer="' + randId + '"]').parent().find("[data-tw-toggle='dropdown']") 
      		jQuery(this).removeClass("show") 
      		const event = new Event("hide.tw.dropdown") 
      		jQuery(dropdownToggle).parent()[0].dispatchEvent(event) 
      		setTimeout(() => {
		        jQuery('[data-dropdown-replacer="' + randId + '"]').replaceWith(this) 
		        jQuery(this).removeAttr("style") 
		        jQuery(this).removeAttr("data-popper-placement") 
		        jQuery(dropdownToggle).attr("aria-expanded", false) 
		        const event2 = new Event("hidden.tw.dropdown") 
		        jQuery(dropdownToggle).parent()[0].dispatchEvent(event2) 
		    }, 200)
    	} else if (jQuery(this).attr("id") !== void 0 && !jQuery('[data-dropdown-replacer="' + jQuery(this).attr("id") + '"]').length && jQuery(this).hasClass("show") && jQuery(this).data("dropdown-programmatically") === void 0) {
      		jQuery(this).remove() 
	    } else if (jQuery(this).data("dropdown-programmatically") == "initiate") {
	      	jQuery(this).attr("data-dropdown-programmatically", "showed") 
	    } else if (jQuery(this).data("dropdown-programmatically") == "showed") {
	      	jQuery(this).removeAttr("data-dropdown-programmatically") 
	      	hide() 
	    }
  	}) 
}
function findVisibleDropdownToggle(dropdownToggle) {
  	return dropdownToggle.filter((key, dropdownToggle2) => {
    	return dropdownToggle2.offsetParent !== null
  	})
}