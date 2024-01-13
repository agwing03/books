package project.books.sys.cmmn;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import project.books.sys.util.CamelMap;

@Getter
@Setter
public class CommonVO {
	//페이징 공통
	private int totCnt = 0;
	
	//검색 PARAM
	private String srchCmmn; //공통 검색
	private String srchKey;
	private String srchGbn;
	private String srchText;
	private String srchStartDt;
	private String srchEndDt;
	private String srchParam1;
	private String srchParam2;
	private String srchParam3;
	
	//저장 PARAM
	private String dataString;
	private String saveFlag;
	
	
	//목록 및 정보 RETURN
	private List<CamelMap> dataList;
	private CamelMap dataMap;
	
	
	//공통 필드
	private int clubNo;			// 클럽 NO
	private int regNo; 			// 생성자ID
    private String regDt;		// 생성일자
    
    //반환 메세지 
    private String msg;
}
