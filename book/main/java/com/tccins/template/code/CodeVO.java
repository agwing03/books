package com.tccins.template.code;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeVO {
	
	private String codeType;			// 코드타입
	private String upperCode;			// 상위코드
	
	private String hdCode;				// hidden코드
    private String code;				// 코드
    private String codeNm;				// 코드이름
    private String useYn;				// 코드사용여부
    private String codeDc;				// 코드설명
 
    private String regId;				// 생성자ID
    private String regDate;				// 생성일자
    private String updId;				// 수정자ID
    private String updDate;				// 수정일자
}
