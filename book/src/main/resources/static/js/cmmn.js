/* header, footer, aside, nav */
$(document).ready(function(){
	$('#header').load('/cmmn/header.html');
	$('#nav').load('/cmmn/nav.html');
	$('#aside').load('/cmmn/aside.html');
	$('#footer').load('/cmmn/footer.html');
	
	const menuList = fetchApi('/sys/getMenuList.do', 'POST', '')
	console.log(menuList)
})

/* ajax 데이터 통신 */
async function fetchApi(url, method, body, headers = {}) {
	const options = {
		method: method,
		headers: {"Content-Type": "application/json", ...headers},
    	body: JSON.stringify(body)
    }
	const res = await fetch(url, options)
	const data = await res.json()
	if (res.ok) {
		return data
	} else {
    	throw Error(data)
	}
}