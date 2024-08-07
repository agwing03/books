package com.tccins.template.admin;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tccins.template.user.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {
	
	private final UserService userService;
	
	@GetMapping("/")
	public String firstPage() {
		return "redirect:/main.do";
	}
	
    /**
     * 세션 시간 받아오기
     * @param
     * @return
     * @throws Exception
     */
	@RequestMapping("/adminSession.do")
	public void adminSession(@RequestParam Map<String, Object> commandMap, ModelMap model,
			HttpServletResponse response) throws Exception {

		try {
			/**
	         * 세션 시간 받아오기
	         */
	        int sessionMin = userService.selectSessionMin();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.print(sessionMin);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
