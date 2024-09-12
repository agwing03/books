package project.books.sys.api.code;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodeService {
	
	private final CodeMapper codeMapper;
	
	/**
	 * 상세 코드 목록 조회
	 * @param codeId
	 * @return list
	 * @throw Exception
	 */
	public CodeVO selectCmmnCodeDtlList(CodeVO vo) throws Exception{
		vo.setCodeList(codeMapper.selectCmmnCodeDtlList(vo));
		return vo;
	}
	
	/**
	 * 활동 지역 목록 조회
	 * @param codeId
	 * @return list
	 * @throw Exception
	 */
	public CodeVO selectCodeAreaList(CodeVO vo) throws Exception{
		vo.setCodeList(codeMapper.selectCodeAreaList());
		return vo;
	} 
}
