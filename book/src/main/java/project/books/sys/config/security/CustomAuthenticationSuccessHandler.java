package project.books.sys.config.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.books.sys.api.log.LogMapper;
import project.books.sys.api.log.LogVO;
import project.books.sys.api.member.MemberMapper;

@Component //Bean Configuration 파일에 Bean을 따로 등록하지 않아도 사용, 타입기반의 자동주입 어노테이션
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	//private RequestCache requestCache = new HttpSessionRequestCache();
 
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
 
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private LogMapper logMapper;
	
	/**
	 * 인증 성공 핸들러 
	 * @param HttpServletRequest, HttpServletResponse, Authentication
	 * @exception IOException
	 */
	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Authentication authentication
		) throws IOException {
		
		// 접속자 정보
		ConnectMember memberInfo = (ConnectMember)authentication.getPrincipal();
		
		// 1.탈퇴 회원 체크
		if(memberInfo.getLeaveYn().equals("Y")) {
			//계정 말료 Exception
			throw new AccountExpiredException(memberInfo.getMemberId()+"는 탈퇴한 계정입니다.");
		}
		
		// 2.계정 잠긴 체크
		if(memberInfo.getLockYn().equals("Y")) {
			if(memberInfo.getUnLockTime() > 0) {
				//계정 잠김 Exception
				throw new LockedException("계정ID : "+memberInfo.getMemberId()+"은(는) 로그인 "+memberInfo.getLoginFailCnt()+"회 실패하여 계정이 잠겼습니다." + memberInfo.getUnLockTime() +"분 후 다시 로그인하여 주시기 바랍니다.");
			}
		}
		
    	/* 실패카운트 & 계정 잠금 및 시간 초기화 */  
		memberMapper.updateResetFailCnt(memberInfo.getMemberNo());
    	
    	/* 로그 저장 */ 
		LogVO vo = new LogVO();
		vo.setLoginGbn("IN");
		vo.setLoginIp(request.getRemoteAddr()); //접속자IP
		vo.setConnMemberNo(memberInfo.getMemberNo());
		logMapper.insertSystemLoginLog(vo);
	    
		/* 로그인 처리 */
	    clearAuthenticationAttributes(request);//로그인 세션 초기화
    	setDefaultTargetUrl("/main.do");//URL 설정
	    redirectStrategy.sendRedirect(request, response, getDefaultTargetUrl());//URL 이동
	 }
}
