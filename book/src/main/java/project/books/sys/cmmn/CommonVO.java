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
	private String srchKey;
	private String srchParam1;
	private String srchParam2;
	private String srchParam3;
	
	//목록 및 정보 공통
	private List<CamelMap> dataList;
	private CamelMap dataMap;
	
	//공통 필드
	private int regNo; 				// 생성자ID
    private String regDt;				// 생성일자
}
