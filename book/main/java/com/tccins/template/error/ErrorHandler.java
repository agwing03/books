/**
 * HTML 에러 OR HTML 관련 에러 발생시 핸들러
 * @author 문용진
 * @since 2022.09.16
 */

package com.tccins.template.error;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ErrorHandler implements ErrorController{
	
	@RequestMapping("/error")
	public ModelAndView handlerError(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		
		Object status = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if(! status.equals(200)) {
			log.debug("Exception : " + req.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI));
			log.debug("Exception : " + req.getAttribute(RequestDispatcher.ERROR_EXCEPTION));
			log.debug("Exception : " + status);
			log.debug("Exception : " + req.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE));
			log.debug("Exception : " + req.getAttribute(RequestDispatcher.ERROR_MESSAGE));
			
			String Msg = "";
			Object errorMsg = req.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
			
			if(status.toString().equals("404")) {
				Msg = "페이지를 찾을 수 없습니다. URL을 다시 확인해주세요.";
			}else {
				Msg = errorMsg.toString();
				if(Msg.length() > 4000) {
					Msg = Msg.substring(0, 4000);
				}
			}
			// url 구분
			String url = (String) req.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);
			if(url == ""|| url == null) {
				url = req.getRequestURI();
			}
			// 날짜
			String dateFormat = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss"));
			
			
			mv.addObject("exception", Msg);
			mv.addObject("url", url);
			mv.addObject("status", status);
			mv.addObject("timeStamp", dateFormat);
			mv.setViewName("error/error");
			
		}
		return mv;
	}
	
}
