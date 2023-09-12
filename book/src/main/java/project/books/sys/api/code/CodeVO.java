package project.books.sys.api.code;

import lombok.Getter;
import lombok.Setter;
import project.books.sys.cmmn.CommonVO;

@Getter
@Setter
public class CodeVO extends CommonVO{
    private String codeId;		//그룹코드
    private String codeDtlId;	//상세코드
    private String codeDtlNm;	//상세코드명
}
