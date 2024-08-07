package com.tccins.template.auth;

import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tccins.template.config.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthApiController {
	
	private final Gson gson;
	
	private final AuthService authService;
	
    
	/**
	 * 권한 생성
	 * @param commandMap
	 * @return String
	 * @throws Exception
	 */
    @RequestMapping("/auth/save.do")
    public String saveAuth(@AuthenticationPrincipal CustomUserDetails userInfo, @RequestBody Map<String,Object> commandMap, Model model) throws Exception {
    	String result;
    	try {
    		// 권한 여부 확인 조회
        	int resultNum = authService.checkAuthId(commandMap);
        	
        	// 권한 여부 확인
        	if(resultNum>0) {
        		result = gson.toJson("중복되는 권한이 있습니다.");
        	}else {
        		// 신규 권한 등록
        		authService.saveAuth(commandMap);
        		result = gson.toJson("신규 권한이 생성되었습니다.");
        	}
        	
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw e;
    	}
        return result;
    }
    
    
    // 권한 수정 
    @RequestMapping("/auth/update.do")
    public String updateAuth(@ModelAttribute("params") final AuthVO params, Model model) throws Exception {
    	
    	authService.updateAuth(params);
    	
    	String message = "권한 수정이 완료되었습니다.";
    	
        return message;
    }
    
	/**
	 * 권한 삭제
	 * @param commandMap
	 * @return String
	 * @throws Exception
	 */
    @RequestMapping("/auth/delete.do")
    public String deleteAuth(@ModelAttribute("params") final AuthVO params, @RequestBody Map<String,Object> commandMap, Model model) throws Exception {
    	String result;
    	try {
    		
    		// 권한 삭제
    		authService.deleteAuth(commandMap);
    		
    		result = gson.toJson("권한이 삭제되었습니다.");
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    		throw e;
    	}
    	return result;
    }
}
