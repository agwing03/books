$(function () {
	// 클로즈 버튼 눌렀을 경우 자동 닫기
    $(".modal_close").on("click", function () {
        action_popup.close(this);
    });
});

function fn_close(){
	$(".modal_close").click();
}


/** 사용방법 action_popup.confirm("내용",함수); */
/**
 *  alert, confirm 대용 팝업 메소드 정의 <br/>
 *  timer : 애니메이션 동작 속도 <br/>
 *  alert : 경고창 <br/>
 *  confirm : 확인창 <br/>
 *  open : 팝업 열기 <br/>
 *  close : 팝업 닫기 <br/>
 */ 
                                       
var typeMessage = "";    
var message = new Map();
message.set("C","정상적으로 등록되었습니다.");
message.set("U","정상적으로 수정되었습니다.");
message.set("D","정상적으로 삭제되었습니다.");
message.set("E","에러가 발생하였습니다. \n자세한 사항은 관리자에게 문의 바랍니다.");
message.set("CC","입력한 정보를 등록하시겠습니까?");
message.set("CU","변경된 정보를 수정하시겠습니까?");
message.set("CD","선택한 정보를 삭제하시겠습니까?");

var action_popup = {
    timer: 500,
    // 컨펌
    confirm: function (txt, callback, param) {
		// 메시지 타입 확인
		// 메시지 타입 확인
		if(message.has(txt)){
			typeMessage = message.get(txt);
		}else{
			typeMessage = txt;
		}
	
        $(".type-confirm .btn_ok").on("click", function () {
            $(this).unbind("click");
            
            
            callback(param);
            action_popup.close(this);
        });
        
        
        
        this.open("type-confirm", typeMessage);
    },
	// alert
    alert: function (txt) {
		// 메시지 타입 확인
		if(message.has(txt)){
			typeMessage = message.get(txt);
		}else{
			typeMessage = txt;
		}
	
        this.open("type-alert", typeMessage);
    },
    //toast
    toast : function(txt,time){
		// 메시지 타입 확인
		if(message.has(txt)){
			typeMessage = message.get(txt);
		}else{
			typeMessage = txt;
		}
	
		this.open("type-alert",typeMessage);
		// 5초 뒤 실행 time 입력
		setTimeout(fn_close , time*1000); 
	}
    ,
	// 오픈 
    open: function (type, txt) {
        var popup = $("." + type);
        popup.find(".menu_msg").text(txt);
        $("body").append("<div class='dimLayer'></div>");
        $(".dimLayer").css('height', $(document).height()).attr("target", type);
        popup.fadeIn(this.timer);
    },
	// 닫기
    close: function (target) {
        var modal = $(target).closest(".modal-section");
        var dimLayer;
        if (modal.hasClass("type-confirm")) {
            dimLayer = $(".dimLayer[target=type-confirm]");
            $(".type-confirm .btn_ok").unbind("click");
        } else if (modal.hasClass("type-alert")) {
            dimLayer = $(".dimLayer[target=type-alert]")
        } else {
            console.warn("close unknown target.")
            return;
        }
        modal.fadeOut(this.timer);
        setTimeout(function () {
            dimLayer != null ? dimLayer.remove() : "";
        }, this.timer);
    }
}