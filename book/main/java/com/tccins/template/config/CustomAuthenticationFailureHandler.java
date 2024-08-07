package com.tccins.template.config;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.tccins.template.common.CamelMap;
import com.tccins.template.user.UserService;

//인증 실패 핸들러 : CustomAuthenticationFailureHandler
@Component // Bean Cofig에 따로 등록하지 않아도 빈 등록자체를 빈 클래스 자체에다가 할 수 있음.
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	@Autowired
	 private UserService userService;

	/**
	 * 실패 핸들러
	 * SimpleUrlAuthenticationFailureHandler
	 * 메소드 오버라이드
	 */
	@Override
	 public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		try {
			 // 아이디 값 가져오기
			 String userId = request.getParameter("userId");
			 // PK 값 가져오기
			 String userPk = userService.selectUserPk(userId);
			 // 기본 정보 가져오기
			 CustomUserDetails userInfo = userService.selectUser(userPk);
			 // 기본 예외 메시지
		     String errorMessage;
		     // 계정 잠김 체크 (계정 잠김부터 체크 해야 잠김 계정 비밀번호 틀렸을 경우 실패횟수 증가 안시킴
		     if(exception instanceof LockedException) {
	    		 errorMessage = exception.getMessage();
			 // 비밀번호 틀렸을 경우
	    	 }else if (exception instanceof BadCredentialsException) {
	    		 // 계정 체크 
	    		 if(userInfo == null) {
	    			 errorMessage = userId + "는(은) 존재하지 않는 ID 입니다.";
	    		 }else {
		    		 //계정 잠김 여부부터 확인 하고 잠겨 있으면 비밀번호 실패 횟수 증가 시키지 않고 메시지 반환
		    		 if(userInfo.getIsLock().equals("Y")) {
		    			errorMessage =  "로그인 시도 "+ userInfo.getFailCount() + "회 실패로 계정이 잠겼습니다." + userInfo.getUnLockTime() +"분 후 다시로그인 하여주세요.";
		    		 }else {
		    			 // 위에서 가져온 데이터 +1 해야 맞음 
		    			 int failCnt = userInfo.getFailCount()+1;
		    			 int failAccesCNt = userInfo.getLoginFailCount();
		    			 // 로그인 실패 횟수와 로그인 허용 실패횟수가 같아진다면 메시지 반환
		    			 if(failAccesCNt == failCnt) {
		    				 // 계정 잠금
		    		    	 userService.updateUserLock(userId);
		    				 errorMessage =  "로그인 시도 "+ failCnt + "회 실패로 계정이 잠겼습니다." + userInfo.getLoginFailLockTime() +"분 후 다시로그인 하여주세요.";
		    			 }else if(failCnt > failAccesCNt) {
		    				 //로그인 실패 횟수 1로 변경
		    				 userService.updateFailCntReset(userId);
		    				 errorMessage = "비밀번호를 확인하여주세요. 비밀번호" + userInfo.getLoginFailCount() + "회 실패시 계정이 잠깁니다. 비밀번호를 1회 틀리셨습니다.";
		    			 }else {
		    				 //로그인 실패 횟수 증가
		    				 userService.updateFailCnt(userInfo);
		    				 errorMessage = "비밀번호를 확인하여주세요. 비밀번호 " + userInfo.getLoginFailCount() + "회 실패시 계정이 잠깁니다. 비밀번호를 " + failCnt + "회 틀리셨습니다."; 
		    			 }
		    		 }
	    		 }
	    	 }else if(exception instanceof UsernameNotFoundException) {
	    		 errorMessage = userId + " 는(은) 존재하지 않는 ID입니다.";
			 // 인증 거부
	    	 }else if(exception instanceof InternalAuthenticationServiceException) {
	    		 errorMessage = "인증 요청이 거부되었습니다. 관리자에게 문의하세요.";
	    	 // 접근 거부
	    	 }else if(exception instanceof AuthenticationServiceException) {
	    		 errorMessage = "접근 요청이 거부되었습니다. 관리자에게 문의하세요.";
	    	 }else {
	    		 errorMessage = "알 수 없는 오류로 로그인 요청을 처리할 수 없습니다. 관리자에게 문의하세요.";
	    	 }
		     /* 로그 저장 */
 			 CamelMap logMap = new CamelMap();
 			 logMap.put("loginUserId",userId);
 			 logMap.put("loginLogContents",errorMessage);
 			 logMap.put("loginIpAddr",request.getRemoteAddr());
 			 logMap.put("loginGbn","F");
			 userService.userInsertLog(logMap);
			 
		     errorMessage = URLEncoder.encode(errorMessage, "UTF-8"); /* 한글 인코딩 깨진 문제 방지 */
		     // 파라미터로 error와 exception을 보내서 controller에서 처리하기 위함.
		     setDefaultFailureUrl("/login.do?error=true&exception=" + errorMessage);
		
		     // 부모클래스의 onAuthenticationFailure로 처리를 위임하자.
		     super.onAuthenticationFailure(request, response, exception);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	 }
}
