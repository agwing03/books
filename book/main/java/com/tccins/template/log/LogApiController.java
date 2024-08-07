package com.tccins.template.log;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LogApiController {
	
	private final LogService logService;
	
	/**
	 * 에러 로그 저장
	 * @param map
	 * @return void
	 */
	@RequestMapping("/log/errorLogInsert.do")
	public void errorLogInsert(@RequestBody Map<String,Object> commandMap) {
		//로그 저장
		logService.errorLogInsert(commandMap);
	}

}
