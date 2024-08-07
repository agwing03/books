package com.tccins.template.code;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tccins.template.common.LayoutModule;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor // final 필드나 @Nonnull이 붙은 필드에 대해 생성자를 생성해줌 , 주로 의존성 주입 편의성을 위해 사용
public class CodeViewController extends LayoutModule{
	
	 private final CodeService codeService;
	 
	/**
	 * 코드리스트 화면
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/code/codeList.do")
	public String codeList(HttpServletRequest req, Model model) throws Exception {

		return "code/codeList";
	}
	
	/**
	 * 코드목록 조회 AJAX
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/code/selectCodeList.do")
	@ResponseBody
	public List<CodeVO> selectCodeList(@ModelAttribute("params") CodeVO params) throws Exception {
		List<CodeVO> codeList = null;
		
		try{
			codeList = codeService.selectCodeList(params);
			
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return codeList;
	}
	
}