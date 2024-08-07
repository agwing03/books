/**
 * RestController로 선언된 컨트롤러에 모든 예외 
 * 여기서 Rest로 선언된 모든 Controller는 AJAX사용 
 * @author 문용진
 * @since 2022.09.16
 */
package com.tccins.template.error;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tccins.template.common.CamelMap;
import com.tccins.template.log.LogService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice(annotations =  RestController.class) // RestController만 잡음.
@Slf4j
@RequiredArgsConstructor
public class GlobalRestHandlerException {
	
	private final LogService logService;
	
	@ExceptionHandler(Exception.class) // Exception 된 에러
	public void handlerException(Exception e,HttpServletRequest req) {
		
		// 상태 코드를 여기서 핸들링 하고 싶은데 어찌 하는지를 모르겠삼..
		log.debug("Exception : " + req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
		log.debug("Exception : " + e.getMessage());
		log.debug("Exception : " +  req.getUserPrincipal().getName());
		
		Object url = req.getRequestURI();
		
		CamelMap dataMap = new CamelMap();
		dataMap.put("errorMenuUrl", url);
		dataMap.put("errorIpAddr", req.getRemoteAddr());
		dataMap.put("errorUserId", req.getUserPrincipal().getName());
		dataMap.put("errorLogStatus", req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
		
		logService.errorLogInsert(dataMap);
		
	}

}
