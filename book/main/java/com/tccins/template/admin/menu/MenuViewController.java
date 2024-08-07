package com.tccins.template.admin.menu;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tccins.template.code.CodeVO;
import com.tccins.template.common.LayoutModule;
import com.tccins.template.common.dto.SearchDto;
import com.tccins.template.paging.PagingResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor // final 필드나 @Nonnull이 붙은 필드에 대해 생성자를 생성해줌 , 주로 의존성 주입 편의성을 위해 사용
public class MenuViewController extends LayoutModule {
	
	private final MenuService menuService;
	
	/**
	 * 메뉴 등록 페이지
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/admin/menu/menuView.do")
	public String menuView(Model model,@RequestParam Map<String,Object> commandMap) throws Exception{
		return "menu/menuView";
	}
	
	
	/**
	 * 메뉴 목록조회 AJAX
	 * @param searchVO
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/menu/selectMenuList.do")
	@ResponseBody
	public List<MenuVO> selectRootMenuList(
			@ModelAttribute("params") MenuVO params,
			HttpServletResponse response,
			Model model 
		) throws Exception {
		List<MenuVO> rootMenuList = null;
		try{
			rootMenuList = menuService.selectAjaxMenuList(params);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return rootMenuList;
	}
	
	
	
	 	
}
