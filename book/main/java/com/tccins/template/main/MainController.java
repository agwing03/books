package com.tccins.template.main;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tccins.template.common.LayoutModule;
import com.tccins.template.config.CustomUserDetails;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController extends LayoutModule {

	/**
	 * 로그인 페이지
	 * 
	 * @param error
	 * @param exception
	 * @param model
	 * @return
	 */
	@GetMapping("/login.do")
	public String loginView(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "exception", required = false) String exception,
			@RequestParam(value = "msg", required = false) String msg, Model model) {

		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
		model.addAttribute("msg", msg);

		return "/login";
	}

	/**
	 * 메인 페이지
	 * 
	 * @param error
	 * @param exception
	 * @param model
	 * @return
	 */
	@GetMapping("/main.do")
	public String main(HttpServletRequest request, @AuthenticationPrincipal CustomUserDetails userInfo, Model model) {

		// * 자바단에서 스프링 인증여부 및 객체 속성 가져옴 @AuthenticationPrincipal
		System.out.println(userInfo.getUsername());
		System.out.println(userInfo.getDeptCd());
		System.out.println(userInfo.getAuthorities());
		// 인증된 사용자정보 get
		System.out.println(userInfo.getUserPk());

		model.addAttribute("userInfo", userInfo);

		// *************권한 체크 권한테이블에서 ROLE_권한 방식으로 데이터존재

		// 접근자체 막을때 SecurityFilterChain > antMatchers("/board/list.do").hasRole("TEST")
		// hasRole에서 ROLE_제외한 특정권한

		// java에서 체크 시 HttpServletRequest > request.isUserInRole("ROLE_TEST")
		boolean isTest = request.isUserInRole("ROLE_TEST");
		model.addAttribute("isTest", isTest);

		if (request.isUserInRole("ROLE_TEST")) {
			/****/
		}

		// java에서 체크 시 HttpServletRequest 못받을때 권한목록 가져와서 Iterator로 체크
		boolean isAdmin = false;
		Iterator<? extends GrantedAuthority> iter = userInfo.getAuthorities().iterator();
		while (iter.hasNext()) {
			GrantedAuthority auth = iter.next();
			if (auth.getAuthority().equals("ROLE_ADMIN")) {
				isAdmin = true;
			}
		}
		if (isAdmin) {
			/****/
		}
		return "/main";
	}
    
}
