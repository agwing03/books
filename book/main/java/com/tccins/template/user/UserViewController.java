package com.tccins.template.user;

import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class UserViewController extends LayoutModule{
	
	private final UserService userService;

	/**
	 * 사용자 관리 페이지
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/userList.do")
	public String userList(@AuthenticationPrincipal CustomUserDetails userInfo, Model model,@ModelAttribute("params") final SearchDto params) throws Exception{
		try {
			//조회
			PagingResponse<CustomUserDetails> response = userService.userList(params); 
			model.addAttribute("response",response);
			model.addAttribute("searchVO", params);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user/userList";
	}
	
	/**
	 * 사용자 암호 수정 페이지
	 * @param 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/user/userPwList.do")
	public String userPwList(@AuthenticationPrincipal CustomUserDetails userInfo, Model model,@ModelAttribute("params") final SearchDto params) throws Exception{
		try {
			//조회
			PagingResponse<CustomUserDetails> response = userService.userList(params); 
			model.addAttribute("response",response);
			model.addAttribute("searchVO", params);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user/userPwList";
	}
	
	/**
	 * 사용자 등록 페이지
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/userSignView.do")
	public String userSignView(@AuthenticationPrincipal CustomUserDetails userInfo,Model model,@RequestParam Map<String,Object> commandMap) throws Exception{
		return "user/userSignView";
	}

}
