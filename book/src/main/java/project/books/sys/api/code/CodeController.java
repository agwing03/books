package project.books.sys.api.code;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CodeController {
	
	private final CodeService codeService;
	 
	/**
	 * 코드 목록 조회
	 * @param clubNo
	 */
	@RequestMapping("/code/getCmmnCodeDtlList.do")
	public CodeVO getCodeDtlList(
			@RequestBody CodeVO vo
		) throws Exception {
		return codeService.selectCmmnCodeDtlList(vo);
	}
	
	/**
	 * 활동 지역 목록 조회
	 * @param clubNo
	 */
	@RequestMapping("/code/getCodeAreaList.do")
	public CodeVO getCodeAreaList(
			@RequestBody CodeVO vo
		) throws Exception {
		return codeService.selectCodeAreaList(vo);
	}
}
 