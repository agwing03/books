package project.books.sys.config.security;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.books.sys.api.member.MemberMapper;
import project.books.sys.util.CamelMap;

@Component //Bean Configuration 파일에 Bean을 따로 등록하지 않아도 사용, 타입기반의 자동주입 어노테이션
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	@Autowired
	 private MemberMapper memberMapper;

	/**
	 * 인증 실패 핸들러 
	 * @param HttpServletRequest, HttpServletResponse, AuthenticationException
	 * @exception IOException
	 */
	@Override
	public void onAuthenticationFailure(
			HttpServletRequest request, 
			HttpServletResponse response, 
			AuthenticationException exception
	    ) throws IOException, ServletException {
		
		//Parameter 정보 
		String connetId = request.getParameter("connetId");
		
		//접속자 정보 가져오기
		ConnectMember memberInfo = memberMapper.selectConnetMemberInfo(connetId);

		// Exception 메시지
		String exMsg;
		
		//계정 잠김
		if(exception instanceof LockedException) {
			exMsg = exception.getMessage();
		
		//비밀번호 오류
	    }else if (exception instanceof BadCredentialsException) {
	    	
	    	//1.맴버정보 확인 
	    	if(memberInfo == null) {
	    		exMsg = connetId + "일치하는 맴버정보가 존재하지 않습니다.";
	    	}else {
	    		//계정 잠김 체크
	    		if(memberInfo.getLockYn().equals("Y")) {
	    			exMsg = "로그인 시도 "+memberInfo.getLoginFailCnt()+"회 실패로 계정이 잠겼습니다."+ memberInfo.getUnLockTime()+"분 후 다시로그인 하여주세요.";
	    		}else {
	    			// 위에서 가져온 데이터 +1 해야 맞음 
	    			int failCnt = memberInfo.getLoginFailCnt()+1;
		    		// 로그인 실패 횟수와 로그인 허용 실패횟수가 같아진다면 메시지 반환
		    		if(failAccesCNt == failCnt) {
		    			// 계정 잠금
		    			userService.updateUserLock(userId);
		    		    exMsg =  "로그인 시도 "+ failCnt + "회 실패로 계정이 잠겼습니다." + userInfo.getLoginFailLockTime() +"분 후 다시로그인 하여주세요.";
		    		} else if (failCnt > failAccesCNt) {
		    			//로그인 실패 횟수 1로 변경
		    			userService.updateFailCntReset(userId);
		    			exMsg = "비밀번호를 확인하여주세요. 비밀번호" + userInfo.getLoginFailCount() + "회 실패시 계정이 잠깁니다. 비밀번호를 1회 틀리셨습니다.";
		    		} else {
		    			//로그인 실패 횟수 증가
		    			userService.updateFailCnt(userInfo);
		    			exMsg = "비밀번호를 확인하여주세요. 비밀번호 " + userInfo.getLoginFailCount() + "회 실패시 계정이 잠깁니다. 비밀번호를 " + failCnt + "회 틀리셨습니다."; 
		    		}
		    	}
	    	}
	    	 }else if(exception instanceof UsernameNotFoundException) {
	    		 exMsg = userId + " 는(은) 존재하지 않는 ID입니다.";
			 // 인증 거부
	    	 }else if(exception instanceof InternalAuthenticationServiceException) {
	    		 exMsg = "인증 요청이 거부되었습니다. 관리자에게 문의하세요.";
	    	 // 접근 거부
	    	 }else if(exception instanceof AuthenticationServiceException) {
	    		 exMsg = "접근 요청이 거부되었습니다. 관리자에게 문의하세요.";
	    	 }else {
	    		 exMsg = "알 수 없는 오류로 로그인 요청을 처리할 수 없습니다. 관리자에게 문의하세요.";
	    	 }
		     /* 로그 저장 */
 			 CamelMap logMap = new CamelMap();
 			 logMap.put("loginUserId",userId);
 			 logMap.put("loginLogContents",exMsg);
 			 logMap.put("loginIpAddr",request.getRemoteAddr());
 			 logMap.put("loginGbn","F");
			 userService.userInsertLog(logMap);
			 
			 exMsg = URLEncoder.encode(exMsg, "UTF-8"); /* 한글 인코딩 깨진 문제 방지 */
		     // 파라미터로 error와 exception을 보내서 controller에서 처리하기 위함.
		     setDefaultFailureUrl("/login.do?error=true&exception=" + exMsg);
		
		     // 부모클래스의 onAuthenticationFailure로 처리를 위임하자.
		     super.onAuthenticationFailure(request, response, exception);
		}
	 }
}
