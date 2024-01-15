package project.books.sys.cmmn.code;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import project.books.club.cmmn.CommonVO;
import project.books.sys.util.CamelMap;

@Getter
@Setter
public class CodeVO extends CommonVO{
    private String codeId;		//그룹코드
    private String codeDtlId;	//상세코드
    private String codeDtlNm;	//상세코드명
    
    //목록 및 정보 RETURN
  	private List<CamelMap> codeList;
}
