package com.tccins.template.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import com.tccins.template.common.CamelMap;
import com.tccins.template.user.UserService;

//인증 성공 핸들러 : CustomAuthenticationSuccessHandler
@Component // Bean Cofig에 따로 등록하지 않아도 빈 등록자체를 빈 클래스 자체에다가 할 수 있음.
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private RequestCache requestCache = new HttpSessionRequestCache();
 
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
 
	 @Autowired
	 private UserService userService;

	 /**
	  * 성공 핸들러 
	  * SimpleUrlAuthenticationSuccessHandler
	  * 메소드 오버라이드
	  */
	 @Override
	 public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		     //아이디 파라미터 확인
		     String userId = request.getParameter("userId");
		     //계정이 잠겨 있는지 체크
		     CustomUserDetails memberLogin = (CustomUserDetails)authentication.getPrincipal();
		     // 계정 잠김 시간 체크
		     if(memberLogin.getUnLockTime() > 0) {
			     // 해당 계정이 잠겨 있으면  
			     if(memberLogin.getIsLock().equals("Y")) {
			    	 //계정정지 예외 발생 -> 실패 핸들러로 리턴 시킴
			    	 throw new LockedException("로그인 시도 "+ memberLogin.getFailCount() + "회 실패로 계정이 잠겼습니다." + memberLogin.getUnLockTime() +"분 후 다시로그인 하여주세요.");
			     }
		     }
		     // 사용하지 않는 계정일 경우
		     if(memberLogin.getUseYn().equals("N")) {
		    	 throw new LockedException(userId + "는(은) 접근이 거부된 ID입니다. 관리자에게 문의해 주세요.");
		     }
		     // 성공시 이동 할 URL 설정
	    	 setDefaultTargetUrl("/main.do");
	    	 //로그인 성공시 실패 횟수 및 락 타임 초기화
	    	 userService.resetFailCnt(userId);
	    	 /* 로그 저장 */
 			 CamelMap logMap = new CamelMap();
 			 logMap.put("loginUserId",userId);
 			 logMap.put("loginLogContents","로그인에 성공하셨습니다.");
 			 logMap.put("loginIpAddr",request.getRemoteAddr());
 			 logMap.put("loginGbn","S");
			 userService.userInsertLog(logMap);
		     //로그인 세션 지우기
		     clearAuthenticationAttributes(request);
		     // url 이동
		     redirectStrategy.sendRedirect(request, response, getDefaultTargetUrl());
	 }
}
