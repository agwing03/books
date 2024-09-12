package project.books.sys.api.log;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogVO {
	//공통 
    private int clubNo; 		//클럽번호
    private int memberNo;		//맴버번호
    private int meetingNo;		//모임번호
    
    //클럽 맴버
    private String targetGbn; 	//대상구분(SYSTEM, CLUB)
    private String joinGbn;		//처리구분(IN, OUT, RESIGN)
    
    //시스템 에러
    private String methodNm;	//메소드명
	private String paramData;	//파라미터
	private String errMsg;		//에러 Exception
	
	//시스템 로그인/아웃 로그 
	private String loginGbn;	//로그인 구분(IN, OUT)
	private String loginIp;		//접속IP
	private int connMemberNo;	//접속자NO
	private String rtnMsg;		//반환메세지
	private int failCnt = 0;	//실패카운트
}