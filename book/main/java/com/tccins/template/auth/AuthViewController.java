package com.tccins.template.auth;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tccins.template.common.LayoutModule;
import com.tccins.template.common.dto.MessageDto;
import com.tccins.template.common.dto.SearchDto;
import com.tccins.template.config.CustomUserDetails;
import com.tccins.template.paging.PagingResponse;
import com.tccins.template.userAuth.UserAuthVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthViewController extends LayoutModule {
	
	private final AuthService authService;
	
	// 권한 목록 페이지
	@RequestMapping("/auth/list.do")
    public String openAuthList(@AuthenticationPrincipal CustomUserDetails userInfo, @ModelAttribute("params") final SearchDto params,Model model) throws Exception {
    	try {
    		PagingResponse<AuthVO> response= authService.findAllAuth(params);
        	model.addAttribute("response", response);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
        return "auth/authList";
    }
    
	// 권한 등록페이지 이동
    @RequestMapping("/auth/write.do")
    public String openAuthWrite(@AuthenticationPrincipal CustomUserDetails userInfo, @RequestParam(value = "authId", required = false) final String authId,Model model) throws Exception {
    	try {
    		if (authId != null) {
        		AuthVO auth = authService.findAuthById(authId);
                model.addAttribute("auth", auth);
            }
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return "auth/authWrite";
    }
    
    // 권한 상세 페이지 이동
    @RequestMapping("/auth/view.do")
    public String openAuthView(@AuthenticationPrincipal CustomUserDetails userInfo, @RequestParam final String authId, Model model) throws Exception {
    	try {
    		AuthVO auth = authService.findAuthById(authId);
        	model.addAttribute("auth",auth);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return "auth/authView";
    }
    
}
