package project.books.sys.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor{
	
	// View(request) > DispatcherServlet > preHandle > Controller
	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler
		) throws Exception {
		
        log.debug("==================== BEGIN ====================");
        log.debug("Request URI ===> " + request.getRequestURI());
        return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	// Controller > preHandle > DispatcherServlet > View(response) 
	@Override
	public void postHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler,
			ModelAndView modelAndView
		) throws Exception {
		
	    log.debug("==================== END ======================");
	    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

}
