package project.books.club.cmmn;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmmnVO {
	//공통 필드
	private int clubNo = 1; 	// 클럽 NO
    private int procCnt = 0;	// C.U.D 처리 건수
    private int userNo = 2; 	// 접속자NO
    private String msg; 		// return 메세지
}
	