package project.books.club.cmmn;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import project.books.sys.util.CamelMap;

@Getter
@Setter
public class CommonVO {
	//페이징 공통
	private int totCnt = 0;
	
	//공통 필드
	private int clubNo = 1; 	// 클럽 NO
	private int userNo = 2;		// 생성자 NO
    private int saveCnt = 0;	// 저장 건수
    private String saveFlag;	// I,U,D
    private String resultCode;	// 200:성공, 500:실패
    private int frstRegNo; 		// 최초 등록자 번호
    private String frstRegNm; 	// 최초 등록자 이름 
    private String frstRegDt; 	// 최초 등록일 
    private int lastUpdNo; 		// 최종 변경자 번호
    private String lastUpdNm; 	// 최종 변경자 이름
    private String lastUpdDt;	// 최종 변경일
    private int regNo; 			// 등록자 
    private String regDt; 		// 등록일
    
    //반환 메세지 
    private String msg;
    
    //MSG 셋팅
    public String getMsg(){
    	if(this.saveCnt > 0) {
    		resultCode = "200";
    		if(this.saveFlag.equals("I") || this.saveFlag.equals("U")) {
    			msg = "저장 되었습니다.";
    		}else if(this.saveFlag.equals("D")) {
    			msg = "삭제 되었습니다.";
    		}
    	}else {
    		resultCode = "500";
    		msg = "정상적으로 처리되지 않았습니다. 관리자에게 문의해 주시기 바랍니다.";
    	}
    	return msg;
    }
}
