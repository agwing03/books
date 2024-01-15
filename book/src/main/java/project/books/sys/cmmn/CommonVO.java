package project.books.sys.cmmn;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonVO {
	//페이징 공통
	private int totCnt = 0;
	
	//공통 필드
	private int clubNo;			// 클럽 NO
	private int regNo; 			// 생성자 NO
    private String regDt;		// 생성일자
    private int saveCnt = 0;	// 저장 건수
    
    //반환 메세지 
    private String msg;
}
