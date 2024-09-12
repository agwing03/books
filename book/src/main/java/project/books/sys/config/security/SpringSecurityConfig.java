package project.books.sys.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이나 @NonNull 로 선언된 필드 자동생성
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig{
	
	// 성공 핸들러
	private final AuthenticationSuccessHandler successHandler;
	// 실패 핸들러
	private final AuthenticationFailureHandler failureHandler;
		
	/**
	 * authorizeHttpRequests()
	 * .permitAll(): 누구나 접근 가능
	 * .permitAll(): 누구나 접근 가능
	 * .authenticated(): 인증된 사용자만 접근 가능
	 * .hasRole("ROLE_NAME"): 특정 역할을 가진 사용자만 접근 가능
	 * .hasAuthority("AUTHORITY"): 특정 권한을 가진 사용자만 접근 가능
	 * 
	 * formLogin()
	 * .loginPage(String url): 커스텀 로그인 페이지 URL 설정
	 * .loginProcessingUrl(String url): 로그인 요청을 처리할 URL
	 * .defaultSuccessUrl(String url): 로그인 성공 후 이동할 기본 URL
	 * .failureUrl(String url): 로그인 실패 시 이동할 URL
	 * 
	 * logout()
	 * .logoutUrl(String url): 로그아웃 처리 URL
	 * .logoutSuccessUrl(String url): 로그아웃 성공 시 이동할 URL
	 * 
	 * httpBasic()
	 * 
	 * csrf()
	 * CSRF(Cross-Site Request Forgery) 공격 방지를 위한 설정
	 * 
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable).disable()
			.headers().frameOptions().disable()
			.and()
				.authorizeHttpRequests()
				.requestMatchers("/login/**","/login**","/test/test**","/payment/**").permitAll()
				.requestMatchers("/board/list.do").hasRole("TEST")
				.anyRequest().authenticated()
			.and()
				.formLogin()
				.loginPage("/login/login")
				.loginProcessingUrl("/login/login-proc")
				.usernameParameter("username")
				.passwordParameter("password")
				.defaultSuccessUrl("/main.do", true)
				.successHandler(successHandler) //  성공 success 핸들러 호출
                .failureHandler(failureHandler) //  실패 failure 핸들러 호출
                .permitAll()
			.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout.do")) // 로그아웃 URL
    		    .logoutSuccessUrl("/login.do") // 성공시 리턴 URL
    		    .invalidateHttpSession(true) // 인증정보를 지우하고 세션을 무효화
    		    .deleteCookies("JSESSIONID") // JSESSIONID 쿠키 삭제
    			.permitAll()
			.and()
				.userDetailsService(myUserDetailsService);
        return http.build();
    }
	  
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/css/**", "/scripts/**", "/img/**", "/plugin/**", "/fonts/**");
    }
	    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new SHA256PasswordEncoder();
    }    
	    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
