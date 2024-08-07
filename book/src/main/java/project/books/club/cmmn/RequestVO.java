package project.books.club.cmmn;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import project.books.sys.util.CamelMap;

@Getter
@Setter
public class RequestVO {
	//검색 필드
	private String srchWord;	//검색어
	private String srchGbn;		//검색구분
	private String srchStartDt; //검색시작일
	private String srchEndDt; 	//검색종료일
	private String srchPageGbn; //화면구분
	private String srchData; 	//데이터구분
	
	//페이징 공통
	private int totCnt = 0;
	
	//공통 필드
	private int clubNo;			// 클럽 NO
	private int meetingNo;		// 모임 NO
	private int memberNo;		// 맴버 NO
	private int regNo; 			// 생성자 NO
    private String regDt;		// 생성일자
    private int saveCnt = 0;	// 저장 건수
    
    //반환 메세지 
    private String msg;
    private int procCnt = 0;
    
    //공통
    private List<CamelMap> dataList;
    private List<Integer> intList;
    private List<String> stringList;
    private CamelMap dataMap;
}