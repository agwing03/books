package com.tccins.template.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tccins.template.common.CamelMap;
import com.tccins.template.config.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserApiController {
	
	private final Gson gson;
	
	private final UserService userService;
	
	/**
	 * 사용자 삭제 
	 * @param commandMap
	 * @return void
	 * @throws Exception
	 */
	@RequestMapping("/user/userDelete.do")
	public String userDelete(@RequestBody Map<String,Object> commandMap,@AuthenticationPrincipal CustomUserDetails userInfo) throws Exception{
		String result;
		try {
			commandMap.put("userId", userInfo.getUserId());
			// 사용자 삭제
			userService.userDelete(commandMap);
			
			result = gson.toJson("C");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	/**
	 * 사용자 잠금해제 
	 * @param commandMap
	 * @return void
	 * @throws Exception
	 */
	@RequestMapping("/user/userUnLockUpdate.do")
	public String userUnLockUpdate(@RequestBody Map<String,Object> commandMap,@AuthenticationPrincipal CustomUserDetails userInfo) throws Exception{
		String result;
		try {
			commandMap.put("userId", userInfo.getUserId());
			// 잠금 해제
			userService.userUnLockUpdate(commandMap);
			
			result = gson.toJson("정상적으로 잠금이 해제되었습니다.");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	/**
	 * 사용자 사용여부 변경
	 * @param commandMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/userUseUpdate.do")
	public String userUseUpdate(@RequestBody Map<String,Object> commandMap,@AuthenticationPrincipal CustomUserDetails userInfo) throws Exception{
		String result;
		try {
			commandMap.put("userId", userInfo.getUserId());
			// 잠금 해제
			userService.userUseUpdate(commandMap);
			
			result = gson.toJson("사용자 정보가 변경되었습니다.");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	/**
	 * 사용자 비밀번호 변경 
	 * @param commandMap
	 * @return void
	 * @throws Exception
	 */
	@RequestMapping("/user/userPwUpdate.do")
	public String userPwUpdate(@RequestBody Map<String,Object> commandMap,@AuthenticationPrincipal CustomUserDetails userInfo) throws Exception{
		String result;
		try {
			commandMap.put("userId", userInfo.getUserId());
			// 비밀번호 변경
			userService.userPwUpdate(commandMap);
			
			result = gson.toJson("비밀번호가 변경되었습니다.");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	/**
	 * 부서 조회
	 * @param commandMap
	 * @return void
	 * @throws Exception
	 */
	@RequestMapping("/user/userDpetList.do")
	public List<CamelMap> userDpetList() throws Exception{
		List<CamelMap> deptList = new ArrayList<CamelMap>();
		try {
			// 부서 코드 조회
			deptList = userService.userDpetList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		return deptList;
	}
	
	/**
	 * 권한 조회
	 * @param commandMap
	 * @return void
	 * @throws Exception
	 */
	@RequestMapping("/user/userAuthList.do")
	public List<CamelMap> userAuthList() throws Exception{
		List<CamelMap> list = new ArrayList<CamelMap>();
		try {
			// 부서 코드 조회
			list = userService.userAuthList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	
	/**
	 * 아이디 중복 체크
	 * @param commandMap
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping("/user/userIdSelect.do")
	public String userIdSelect(@RequestBody String userId) throws Exception{
		String result;
		try {
			// 아이디 중복 체크
			int idCnt = userService.userIdSelect(userId);
			if(idCnt > 0) {
				result = gson.toJson("N");
			}else {
				result = gson.toJson("Y");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	/**
	 * 유저 등록
	 * @param commandMap
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping("/user/userSave.do")
	public String userSave(@RequestBody Map<String,Object> commandMap,@AuthenticationPrincipal CustomUserDetails userInfo) throws Exception{
		String result;
		try {
			
			commandMap.put("regId", userInfo.getUserId());
			// 유저 정보 저장
			String userPk = userService.userSave(commandMap);
			if(userPk != null) {
				result = gson.toJson("Y");
			}else {
				result = gson.toJson("N");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

}
