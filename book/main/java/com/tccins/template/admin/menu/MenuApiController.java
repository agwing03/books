package com.tccins.template.admin.menu;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tccins.template.config.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MenuApiController {

	
	private final Gson gson;
	
	private final MenuService menuService;
	
	
	/**
	 * 메뉴 id 중복체크
	 * @param MenuVO
	 * @throws Exception
	 */
	@RequestMapping(value="/checkMenuId.do")
	public void checkMenuId (
			@ModelAttribute("params") MenuVO params
			, HttpServletResponse response
			, Model model
			) throws Exception {
		try{
			
			int checkMenuCount = menuService.checkMenuId(params);
			
			response.setContentType("text/html;charset=utf-8");
		    PrintWriter writer = response.getWriter();
		    writer.print(checkMenuCount);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	
	
	/**
	 * 메뉴 등록
	 * @param MenuVO
	 * @throws Exception
	 */
	@RequestMapping(value="/insertMenu.do")
	public void insertRootMenu (
			@ModelAttribute("params") MenuVO params
			,@AuthenticationPrincipal CustomUserDetails userInfo
			, HttpServletResponse response
			, Model model
			) throws Exception {
		try{
			
			String msg = menuService.insertMenu(params);
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.print(msg);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	
	
	
	/**
	 * 
	 * 메뉴 삭제
	 * @param MenuVO
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteMenu.do")
	public void deleteRootMenu (
			@ModelAttribute("params") MenuVO params
			,@AuthenticationPrincipal CustomUserDetails userInfo
			, HttpServletResponse response
			, Model model
			) throws Exception {
		try{
			
			String msg = menuService.deleteMenu(params);
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.print(msg);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	
	

}
