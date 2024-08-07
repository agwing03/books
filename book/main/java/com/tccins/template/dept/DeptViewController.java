package com.tccins.template.dept;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tccins.template.common.LayoutModule;
import com.tccins.template.common.dto.SearchDto;
import com.tccins.template.config.CustomUserDetails;
import com.tccins.template.paging.PagingResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DeptViewController extends LayoutModule{

	private final DeptService deptService;
	
	/**
	 * 부서관리 페이지
	 * @param params
	 * @param userInfo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/dept/deptList.do")
	public String deptList(@ModelAttribute("params") final SearchDto params, @AuthenticationPrincipal CustomUserDetails userInfo, Model model) throws Exception{
		//조회
		PagingResponse<DeptVO> response =  deptService.deptList(params);
		model.addAttribute("response",response);
		model.addAttribute("searchVO", params);
	
		return "dept/deptList";
	}
    
	/**
	 * 부서상세 페이지
	 * @param deptCd
	 * @param model
	 * @return 
	 * @throws Exception
	 */
    @GetMapping("/dept/deptView.do")
    public String deptView(@RequestParam final String deptCd, Model model) throws Exception {
    	DeptVO dept = deptService.findDeptByDeptCd(deptCd);
    	model.addAttribute("dept", dept);
    	
        return "dept/deptView";
    }
    
    /**
	 * 부서작성 페이지
	 * @param deptCd
	 * @param model
	 * @return 
	 * @throws Exception
	 */
    @GetMapping("/dept/deptWrite.do")
    public String deptWrite(@RequestParam(value = "deptCd", required = false) final String deptCd, @AuthenticationPrincipal CustomUserDetails userInfo, Model model) throws Exception{
    		
    	System.out.println(userInfo.getUsername());
    	System.out.println(userInfo.getAuthorities());
    	System.out.println(userInfo.getDeptCd());
    	
        if (deptCd != null) {
        	DeptVO dept = deptService.findDeptByDeptCd(deptCd);
            model.addAttribute("dept", dept);
        }
    	
        return "dept/deptWrite";
    }
    
    
}
