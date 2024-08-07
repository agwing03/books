package project.books.club.cmmn;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmmnVO {
	//key
	private int clubNo;			// 클럽번호
	private int meetingNo;		// 모임번호
	private int memberNo;		// 참석자번호
	private int bookNo;			// 도서번호
	
	//공통 필드
    private int procCnt = 0;	// C.U.D 처리 건수
    private int userNo = 2; 	// 접속자NO
    private String msg; 		// return 메세지
}
	