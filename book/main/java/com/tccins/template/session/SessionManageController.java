package com.tccins.template.session;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tccins.template.config.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SessionManageController {
	
	private final SessionManageService sessionService;
	
	
	/**
	 * 세션 관리 및 로그인 설정
	 * @param commandMap
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/session/manageDetail.do")
	public String sessionManage(Model model, @AuthenticationPrincipal CustomUserDetails userInfo) throws Exception{
		try {
			SessionVO resultVO = sessionService.selectSessionInfo();
			model.addAttribute("resultVO", resultVO);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "session/sessioManage";
	}
	
	/**
	 * 세션 관리 및 로그인 설정
	 * @param commandMap
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/session/registUpdate.do")
	public void sessionRegist(SessionVO sessionVO,HttpServletResponse response,@AuthenticationPrincipal CustomUserDetails userInfo) throws Exception{
		String msg;
		try {
			JSONObject jsonObj = new JSONObject();
			
			// 세션 및 로그인 관련 변경
			sessionService.updateSession(sessionVO);
			
			msg = "정상적으로 수정되었습니다.";
			jsonObj.put("msg",msg);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.print(msg);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
	}

	
	
	
}
