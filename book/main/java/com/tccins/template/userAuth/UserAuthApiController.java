package com.tccins.template.userAuth;

import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tccins.template.config.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserAuthApiController {

	private final Gson gson;
	
	private final UserAuthService userAuthService;

	// 유저 권한 수정
	@RequestMapping("/userAuth/update.do")
	public String updateUserAhthor(@RequestBody Map<String,Object> commandMap,@AuthenticationPrincipal CustomUserDetails userInfo) throws Exception{
		String result = "";
		try {
			commandMap.put("userId", userInfo.getUserId());
			
			// 권한 수정
			userAuthService.userAuthUpdate(commandMap);
			
			result = gson.toJson("권한이 변경되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
}
