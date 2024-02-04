jQuery(document).ready(function(){
	
});


/**
 *  파라미터 셋팅 
 */
function srchParam(){
	var srchStartDt = jQuery("#srchStartDt").val().replaceAll('.','').trim()
	var srchEndDt = jQuery("#srchEndDt").val().replaceAll('.','').trim()
	let param = {
		'srchGbn': jQuery("#srchGbn").val(),
		'srchText': jQuery("#srchGbn").val(),
		'srchStartDt': srchStartDt,
		'srchEndDt': srchEndDt
	}
	return param				
}


/**
 *  검색 조건 리셋 
 */
function srchReset(){
	jQuery("#srchGbn").val("")
	jQuery('#srchText').val("")
	setSrchDate('-6')
}


/**
 *  날짜 기간 _ 6개월/12개월 
 */
function setSrchDate(month){
	let date1 = dayjs().add(month, "month").format('YYYY.MM.D')
	let date2 = dayjs().format('YYYY.MM.D')
	jQuery('#srchStartDt').val(date1)
	jQuery('#srchEndDt').val(date2)
}


/**
 *  SAVE 데이터 조회
 *  URL	
 *  gbn : I, U, D
 *  수정 필요 +++
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


/**
 *  공통코드 조회
 *  공통코드		: 코드ID
 *  화면 HTML ID	: 태그ID 
 */
async function cmmnCode(codeId, target, addEmpty, headers = {}) {
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
			//옵션 초기화
			jQuery("#"+target).empty()
			//옵션 전체, 선택
			if(addEmpty === 'ALL'){
				optionHtml = '<option value="">전체</option>'				
			}else if (addEmpty === 'SELECT'){
				optionHtml = '<option value="">선택</option>'			
			}
			//옵션 콩통코드
			for(var i = 0; i < data.codeList.length; i++){
				optionHtml += '<option value="'+data.codeList[i].code+'">'+data.codeList[i].codeNm+'</option>'
			}
			jQuery("#"+target).append(optionHtml);
		}
	} else {
    	throw Error(data)
	}
}