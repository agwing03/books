package com.tccins.template.code;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tccins.template.config.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor // final 필드나 @Nonnull이 붙은 필드에 대해 생성자를 생성해줌 , 주로 의존성 주입 편의성을 위해 사용
public class CodeApiController{
	
	 private final CodeService codeService;
	 
    /**
	 * 코드 갯수 조회 AJAX
	 * @param req
	 * @param CodeVO
	 * @param model
	 * @return int
	 * @throws Exception
	 */
	@RequestMapping(value="/selectCodeCnt.do")
	public int selectCodeCnt (@ModelAttribute("params") CodeVO params) throws Exception {
		int codeCount=0;
		
		try{
			codeCount = codeService.selectCodeCnt(params);
			
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return codeCount;
	}
	
	
	/**
	 * 코드저장 AJAX
	 * @param req
	 * @param CodeVO
	 * @param model
	 * @return String
	 * @throws Exception
	 */
    @RequestMapping(value="/saveCode.do")
	public String saveClCode (@ModelAttribute("params") CodeVO params,@AuthenticationPrincipal CustomUserDetails userInfo) throws Exception {
    	String msg="";
    	
    	try{
    		params.setRegId(userInfo.getUserId());
    		params.setUpdId(userInfo.getUserId());	
    		msg = codeService.saveCode(params);
    		
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
    	return msg;
    }
 
	/**
	 * 코드삭제 AJAX
	 * @param req
	 * @param CodeVO
	 * @param model
	 * @return String
	 * @throws Exception
	 */
   @RequestMapping(value="/code/deleteCode.do")
 	public String deleteCode (@ModelAttribute("params") CodeVO params,@AuthenticationPrincipal CustomUserDetails userInfo) throws Exception {
	   String msg="";

	   try{
	   		params.setUpdId(userInfo.getUserId());
			msg = codeService.deleteCode(params);

 		}catch(Exception e){
 			e.printStackTrace();
 			throw e;
 		}
	   return msg;
   }

}