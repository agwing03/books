/**
 * RestController로 선언된 컨트롤러 제외 한 컨트롤러에서의 모든 예외 작업을 가져와서 Error로그 쌓고 Error 페이지로 리턴
 * @author 문용진
 * @since 2022.09.16
 */
package com.tccins.template.error;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.tccins.template.common.CamelMap;
import com.tccins.template.log.LogService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice //RestController를 제외한 Controller에서의 모든 예외
@Slf4j
@RequiredArgsConstructor
public class GlobalHandlerException {
	
	private final LogService logService;
	
	@ExceptionHandler(Exception.class) // Exception 된 에러
	public ModelAndView handlerException(Exception e,HttpServletRequest req) {
		
		// 상태 코드를 여기서 핸들링 하고 싶은데 어찌 하는지를 모르겠삼..
		log.debug("Exception : " + req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
		log.debug("Exception : " + e.getMessage());
		log.debug("Exception : " +  req.getUserPrincipal().getName());
		
		//에러 로그 저장
		Object url = req.getRequestURI();
		String Message = e.getMessage();
		if(Message.length() > 4000) {
			Message.substring(0, 4000);
		}
		// 데이터 쌓기 위한 Map 선언
		CamelMap dataMap = new CamelMap();
		dataMap.put("errorMenuUrl", url);
		dataMap.put("errorIpAddr", req.getRemoteAddr());
		dataMap.put("errorUserId", req.getUserPrincipal().getName());
		dataMap.put("errorLogContents", Message);
		
		logService.errorLogInsert(dataMap);
		
		// 날짜
		String dateFormat = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss"));
		
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", Message);
		mv.addObject("url", req.getRequestURI());
		mv.addObject("timeStamp", dateFormat);
		mv.setViewName("error/error");
		
		return mv;
	}

}
