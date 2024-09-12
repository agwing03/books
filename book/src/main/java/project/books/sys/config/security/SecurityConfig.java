package project.books.sys.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.http.HttpSession;

@Configuration
public class SecurityConfig {
	
	// 성공 핸들러
	private AuthenticationSuccessHandler successHandler;
	
	// 실패 핸들러
	private AuthenticationFailureHandler failureHandler;
	
	// 접속자 정보 Service
	private ConnectMemberService cnnctMemberService;
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http
            //HTTP 접근 권한 설정
            .authorizeHttpRequests(requests -> requests
                //.requestMatchers("/login.do") //''로 시작하는 요청 접근 허용
            	//.hasRole("SYSADMIN") //'SYSADMIN' 권한만 접근 허용
            	//.permitAll() //모든 접근 허용
                //.anyRequest() //그 외 모든
                //.authenticated() //인증 필요
                .requestMatchers("/login.do","/join.do").permitAll()
            	.anyRequest().authenticated()
            )
            
            //로그인 설정
            .formLogin((login) -> login
        	    .loginPage("/login.do") //로그인 URL
        	    .loginProcessingUrl("/loginProc.do") //로그인 요청 처리 URL > URL Request 자동 캐치 > loadUserByUsername 실행
        	    .defaultSuccessUrl("/main.do", true) //로그인 성공 > 이동 URL
        	    .failureUrl("/main.do") //로그인 실패 > 이동 URL
        	    .successHandler(successHandler) //성공 success 핸들러 호출
                .failureHandler(failureHandler) //실패 failure 핸들러 호출
                .permitAll()
        	)
            
            //로그아웃 설정
            .logout((logout) -> logout
            	.logoutUrl("/logout.do") //로그아웃 URL
            	.addLogoutHandler((request, response, authentication) -> { //세션 무효화 처리
                    HttpSession session = request.getSession();
                    session.invalidate();
                })
            	.logoutSuccessUrl("/main.do") //로그아웃 성공 시 이동할 URL
            	.invalidateHttpSession(true) //인증정보삭제 세션 초기화
            	.deleteCookies("JSESSIONID", "remember-me")  //세션 쿠키 삭제
           	)
            
            //세션 관리 방식
            .sessionManagement((session) -> session
            	.maximumSessions(1) //동시 접속 세션 1개
            	.maxSessionsPreventsLogin(true) //동시로그인 차단, false 기존 세션 만료(default)
            )
            
            //세션 유지
            .rememberMe((remember) -> remember
            	.key("CLUB-REMEMBER-ME")
            	.rememberMeParameter("remember-me") //view 파라미터 : ex) 로그인 폼 <input type="checkbox" name="remember-me">
            	.tokenValiditySeconds(21600) //리멤버미 토큰의 유효 시간(초 단위) 설정
            	.userDetailsService(cnnctMemberService)
            );
        return http.build();
    }
}