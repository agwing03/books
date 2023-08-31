package project.books.club.main;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import project.books.sys.cmmn.CommonVO;

@Getter
@Setter
@Data
public class MainVO extends CommonVO{
	
	private String comCd;				// 회사코드

	private String deptCd;				// 부서코드
	private String deptNm;				// 부서이름
    private String upperDeptCd;			// 상위부서코드
    private String deptLevel;			// 부서레벨
    private String useYn;				// 사용여부
 
    private String regId;				// 생성자ID
    private String regDate;				// 생성일자
    private String updId;				// 수정자ID
    private String updDate;				// 수정일자
}
