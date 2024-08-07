package com.tccins.template.userAuth;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tccins.template.auth.AuthVO;
import com.tccins.template.common.LayoutModule;
import com.tccins.template.common.dto.SearchDto;
import com.tccins.template.config.CustomUserDetails;
import com.tccins.template.paging.PagingResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserAuthViewController extends LayoutModule {

	private final UserAuthService userAuthService;

	// 유저 목록 페이지
	@RequestMapping("/userAuth/list.do")
	public String AllUserList(@AuthenticationPrincipal CustomUserDetails userInfo, @ModelAttribute("params") final SearchDto params, Model model) throws Exception {
		try {
			PagingResponse<UserAuthVO> response = userAuthService.findAllUser(params);

			List<AuthVO> authList = userAuthService.authList(params);
			model.addAttribute("response", response);
			model.addAttribute("authList", authList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "userAuth/userAuthList";
	}

	
}
