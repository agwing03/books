/* header, footer, aside, nav */
$(document).ready(function(){
    document.getElementById("header").innerHTML='<object type="text/html" data="/cmmn/header.html"></object>';
    document.getElementById("nav").innerHTML='<object type="text/html" data="/cmmn/nav.html"></object>';
    document.getElementById("aside").innerHTML='<object type="text/html" data="/cmmn/aside.html"></object>';
    document.getElementById("footer").innerHTML='<object type="text/html" data="/cmmn/footer.html"></object>';
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