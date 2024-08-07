package com.tccins.template.dept;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tccins.template.config.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DeptApiController{
	
	 private final DeptService deptService;
 
    // 신규 부서 생성
    @RequestMapping("/dept/saveDept.do")
    public String saveDept(@ModelAttribute("params") final DeptVO params, @AuthenticationPrincipal CustomUserDetails userInfo, Model model) throws Exception {
    	String message = "";
    	
    	try {
    		params.setRegId(userInfo.getUserId());
	    	message = deptService.saveDept(params);
    	}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
        return message;
    }
	    
	// 부서 수정 
	@RequestMapping("/dept/updateDept.do")
	public String updateDept(@ModelAttribute("params") final DeptVO params, @AuthenticationPrincipal CustomUserDetails userInfo, Model model) throws Exception {
		String message="";
		
		try {
			params.setUpdId(userInfo.getUserId());
			message = deptService.updateDept(params);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	    return message;
	}
	
	// 부서 삭제
	@RequestMapping("/dept/deleteDept.do")
	public String deleteDept(@ModelAttribute("params") final DeptVO params, Model model) throws Exception {
		String message = "";

		try {
			String deptCd = params.getDeptCd();
			message = deptService.deleteDept(deptCd);	
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return message;
	}
    

}