package com.tccins.template.log;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tccins.template.common.CamelMap;
import com.tccins.template.common.LayoutModule;
import com.tccins.template.common.dto.SearchDto;
import com.tccins.template.paging.PagingResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor // final 필드나 @Nonnull이 붙은 필드에 대해 생성자를 주입해줌
public class LogViewController extends LayoutModule {
	
	private final LogService logService;
	
	/**
	 * 접속 로그 리스트
	 * @param req
	 * @param commandMap
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/log/logLoginList.do")
	public String LogLoginList(HttpServletRequest req
			,@ModelAttribute("params") SearchDto params
			,Model model) throws Exception {
		//조회
		PagingResponse<CamelMap> logList = logService.selectLoginLogList(params);
		model.addAttribute("response",logList);
		model.addAttribute("searchVO",params);
		return "log/logLoginList";
	}
	
	/**
	 * 에러 로그 리스트
	 * @param req
	 * @param commandMap
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/log/errorLogList.do")
	public String errorLogList(HttpServletRequest req
			,@ModelAttribute("params") SearchDto params
			,Model model) throws Exception {
		//조회
		PagingResponse<CamelMap> logList = logService.selectErrorLogList(params);
		model.addAttribute("response",logList);
		model.addAttribute("searchVO",params);
		return "log/errorLogList";
	}
	
	/**
	 * 로그 리스트
	 * @param req
	 * @param commandMap
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/log/logList.do")
	public String LogList(HttpServletRequest req
			,@ModelAttribute("params") SearchDto params
			,Model model) throws Exception {
		params.setType("logList");
		//조회
		PagingResponse<CamelMap> logList = logService.selectLogList(params);
		model.addAttribute("response",logList);
		model.addAttribute("searchVO",params);
		return "log/logList";
	}
	
	/**
	 * 로그 상세정보
	 * @param logVO
	 * @return logVO
	 * @throws Exception
	 */
	@RequestMapping("/log/logDetail.do")
	public String LogDetail(HttpServletRequest req
			,@RequestParam Map<String,Object> param
			,Model model) throws Exception {
		
		//상세 조회
		CamelMap result = logService.selectLogDetail(param);
		model.addAttribute("resultVO",result);
		model.addAttribute("param",param);
		
		return "log/logDetail";
	}
	
	/**
	 * 파일첨부테스트
	 * @param logVO
	 * @return logVO
	 * @throws Exception
	 */
	/*
	@RequestMapping("/log/fileTest.do")
	public String fileTest(HttpServletRequest req
			,Map<String,Object> commandMap
			,Model model) throws Exception {
		
		return "log/fileTest";
	}
	*/
}
