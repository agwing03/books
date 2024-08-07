// 로그인 페이지가 아닐 경우 실행되게 설정
var pageLength = $("#session").length;
if(pageLength > 0){	
	
	var time = 0;
	session(); // 세션 시간
	clearTime(time); // 세션 만료 적용 시간 
	setTimer();    // 문서 로드시 타이머 시작
	
	// 세션 초기화
	document.onclick = function(e) {
		clearTime(time); // 세션 만료 적용 시간 
	}; 
	
	/**
	 * 세션 받아오기
	 * @param target - 검사 대상 Element
	 * @param fieldName - 필드명
	 * @param focusTarget - 포커스 대상 Element
	 * @returns 필드 입력(선택) 여부
	 */
	 function session(){
		$.ajax({
			 url :"/adminSession.do" 
			,type:"post"
			,data: 	{}
			,dataType:"json"
			,async : false
			,success:function(data){
				time = data;
		    }	
		});
		return true;
	}
	var initMinute;  // 최초 설정할 시간(min)
	var remainSecond;  // 남은시간(sec)
	
	function clearTime(min){ // 타이머 초기화 함수
		   initMinute = min; 
		   remainSecond = min*60; 
		}
		 
	function setTimer(){ // 1초 간격으로 호출할 타이머 함수 
	 
	   // hh : mm 으로 남은시간 표기하기 위한 변수
	    var remainMinute_ = parseInt(remainSecond/60);
	    var remainSecond_ = remainSecond%60;
	   
	   if(remainSecond > 0){
		  var el = document.getElementById('timer');
		  el.innerHTML = Lpad(remainMinute_,2) + ":" + Lpad(remainSecond_,2);
	      remainSecond--;
	      setTimeout("setTimer()",1000); //1초간격으로 재귀호출!
	   }
	   else{
	      if(! sessionOut()){
	    	  return false;
	      }
	   }
	}
	// 세션 끊기
	function sessionOut(){
		
		location.href="/logout.do"; 
		
		return true;
	}
	// 세션 시간 나타내기
	function Lpad(str,len){  // hh mm형식으로 표기하기 위한 함수
		   str = str+"";
		   while(str.length<len){
		      str = "0"+str;
		   }
		   return str;
	}
}