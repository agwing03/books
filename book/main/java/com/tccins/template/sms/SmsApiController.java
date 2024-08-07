package com.tccins.template.sms;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@RestController
@RequiredArgsConstructor
public class SmsApiController{
	
	private final SmsService smsService;
	
	// coolSMS 구현 로직 연결  
	@RequestMapping("/check/sendSMS.do")
	public String sendSMS(@RequestBody Map<String,Object> commandMap) throws CoolsmsException {
		return smsService.PhoneNumberCheck(commandMap);
	}
	
}
