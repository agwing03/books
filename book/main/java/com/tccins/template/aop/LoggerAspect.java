package com.tccins.template.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tccins.template.common.CamelMap;
import com.tccins.template.config.CustomUserDetails;
import com.tccins.template.log.LogService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggerAspect{
	
	@Autowired
	private LogService logService;
	
	// 모든 메소드 전후로 실행됨.
    @Around("execution(* com.tccins..*Controller.*(..)) || execution(* com.tccins..*Service.*(..)) || execution(* com.tccins..*Mapper.*(..))")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {

        String name = joinPoint.getSignature().getDeclaringTypeName();
        String type = "";

        if (name.contains("Controller")) {
            type = "Controller ===> ";

        } else if (name.contains("Service")) {
            type = "ServiceImpl ===> ";

        } else if (name.contains("Mapper")) {
            type = "Mapper ===> ";
        }

        log.debug(type + name + "." + joinPoint.getSignature().getName() + "()");
        return joinPoint.proceed();
    }
    // 컨트롤러 메소드가 실행된 후 예외 , 성공 상관없이
    @After(value = "execution(* com.tccins.haein..*Controller.*(..))")
    public void AfterLog(JoinPoint joinPoint) throws RuntimeException {
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		CamelMap logMap = new CamelMap();
		
		logMap.put("ipAddr", request.getRemoteAddr());
		logMap.put("menuUrl", request.getRequestURI());
		// 아이디 값 얻어오기
		CustomUserDetails userVO = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logMap.put("userId", userVO.getUserId());
		
		logService.insertLog(logMap);
        //logging
    }

}