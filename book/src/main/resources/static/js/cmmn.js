/* header, footer, aside, nav */
$(document).ready(function(){
	$('header').load('/cmmn/header.html');
	//$('footer').load('/cmmn/footer.html');
	
	//nav
	fetchApi('/sys/getMenuList.do', 'POST', {clubNo:'1'}, 'nav')
	//aside
	fetchApi('/sys/getMenuList.do', 'POST', {clubNo:'1'}, 'aside')
})

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
