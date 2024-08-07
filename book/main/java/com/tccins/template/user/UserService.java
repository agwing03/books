package com.tccins.template.user;

import java.security.MessageDigest;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tccins.template.common.CamelMap;
import com.tccins.template.common.dto.SearchDto;
import com.tccins.template.config.CustomUserDetails;
import com.tccins.template.paging.Pagination;
import com.tccins.template.paging.PagingResponse;
import com.tccins.template.user.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserMapper userMapper;
	
	/**
	 * userPk값 구해오기
	 * @param String
	 * @return String
	 */
	public String selectUserPk(String userId) {
		return userMapper.selectUserPk(userId);
	}
	/**
	 * 유저정보 구하기
	 * @param String
	 * @return CustomDetail
	 */
	public CustomUserDetails selectUser(String userId) {
		return userMapper.selectUser(userId);
	}
	
	/**
	 * 로그인 성공시 실패 횟수 초기화
	 * @param String
	 * @return
	 * @throws Exception
	 */
	public void resetFailCnt(String userId) {
		userMapper.resetFailCnt(userId);
	}
	
	/**
	 * 실패 횟수 증가
	 * @param String
	 * @return int
	 * @throws Exception
	 */
	public void updateFailCnt(CustomUserDetails userVO) {
		userMapper.updateFailCnt(userVO);
	}
	
	/**
	 * 계정 잠김으로 업데이트
	 * @param String
	 * @return 
	 * @throws Exception
	 */
	public void updateUserLock(String userId) {
		userMapper.updateUserLock(userId);
	}
	
	/**
	 * 계정 리셋
	 * @param String
	 * @return 
	 * @throws Exception
	 */
	public void updateFailCntReset(String userId) {
		userMapper.updateFailCntReset(userId);
	}    
	
	/**
     * 세션 시간 받아오기
     * @param
     * @return
     * @throws Exception
     */
	public int selectSessionMin() throws Exception{
		return userMapper.selectSessionMin();
	}
	
	/**
	 * 유저 리스트 조회
	 * @param 
	 * @throws Exception
	 * @return
	 */
	public PagingResponse<CustomUserDetails> userList(SearchDto params) throws Exception{
		
		int count = userMapper.count(params);
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        List<CustomUserDetails> list =  userMapper.selectUserList(params);
        
        return new PagingResponse<>(list, pagination);
	}
	
	/**
	 * 유저 리스트 삭제
	 * @param camelMap
	 * @return void
	 * @throws Exception
	 */
	@Transactional
	public void userDelete(Map<String,Object> commandMap) throws Exception{
		
		String userPkArr[] = commandMap.get("userPkArr").toString().split(",");
		
		CamelMap deleteMap = new CamelMap();
		
		for(int i=0; i < userPkArr.length; i++) {
			// 데이터 쌓기
			deleteMap.put("userPk", userPkArr[i]);
			// USER 테이블 삭제
			userMapper.userDelete(deleteMap);
			// USER_AUTH 테이블 삭제
			userMapper.userAuthDelete(deleteMap);
		}
		
	}
	
	/**
	 * 유저 잠금 해제
	 * @param camelMap
	 * @return void
	 * @throws Exception
	 */
	@Transactional // 메소드 종료시 커밋,예외 발생시 롤백 시켜줌
	//@Transactional(propagation = , isolation = ,noRollbackFor = ,readOnly = ,rollbackFor = ,timeout = )
	public void userUnLockUpdate(Map<String,Object> commandMap) throws Exception{
		
		String userPkArr[] = commandMap.get("userPkArr").toString().split(",");
		
		CamelMap updateMap = new CamelMap();
		
		for(int i=0; i < userPkArr.length; i++) {
			// 데이터 쌓기
			updateMap.put("userPk", userPkArr[i]);
			updateMap.put("userId", commandMap.get("userId"));
			// 잠금 해제
			userMapper.userUnLockUpdate(updateMap);
		}
		
	}
	
	/**
	 * 유저 사용여부 변경
	 * @param comandMap
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public void userUseUpdate(Map<String,Object> commandMap) throws Exception{
		String userPkArr[] = commandMap.get("userPkArr").toString().split(","); 
		String useYnArr[] = commandMap.get("useYnArr").toString().split(","); 
		
		CamelMap updateMap = new CamelMap();
		
		for(int i=0; i < userPkArr.length; i++) {
			// 데이터 쌓기
			updateMap.put("userPk", userPkArr[i]);
			updateMap.put("useYn", useYnArr[i]);
			updateMap.put("userId", commandMap.get("userId"));
			// 잠금 해제
			userMapper.userUseUpdate(updateMap);
		}
	}
	
	/**
	 * 유저 비밀번호 변경
	 * @param camelMap
	 * @return void
	 * @throws Exception
	 */
	@Transactional
	public void userPwUpdate(Map<String,Object> commandMap) throws Exception{
		
		String userPkArr[] = commandMap.get("userPkArr").toString().split(",");
		String userPwArr[] = commandMap.get("userPwArr").toString().split(",");
		
		CamelMap updateMap = new CamelMap();
		
		for(int i=0; i < userPkArr.length; i++) {
			//비밀번호 가져오기
			String password = getHtmlStrCnvr(userPwArr[i]);
			//PASSWORD SHA256 암호화
			String sha = "";
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			System.out.println(password);
			sh.update(password.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			for(int j=0; j < byteData.length; j++) {
				sb.append(Integer.toString((byteData[j]&0xff)+ 0x100, 16).substring(1));
			}
			sha = sb.toString();
			// 데이터 쌓기
			updateMap.put("userPk", userPkArr[i]);
			updateMap.put("userPw", sha);
			updateMap.put("userId", commandMap.get("userId"));
			// 잠금 해제
			userMapper.userPwUpdate(updateMap);
		}
		
	}
	
	/**
	 * html의 특수문자를 표현하기 위해
	 *
	 * @param srcString
	 * @return String
	 * @exception Exception
	 * @see
	 */
	public static String getHtmlStrCnvr(String srcString) {

		String tmpString = srcString;

		tmpString = tmpString.replaceAll("&lt;", "<");
		tmpString = tmpString.replaceAll("&gt;", ">");
		tmpString = tmpString.replaceAll("&amp;", "&");
		tmpString = tmpString.replaceAll("&nbsp;", " ");
		tmpString = tmpString.replaceAll("&apos;", "\'");
		tmpString = tmpString.replaceAll("&quot;", "\"");

		return tmpString;

	}
	
	/**
	 * 부서 리스트 가져오기
	 * @param 
	 * @return camelMap
	 * @throws Exception
	 */
	public List<CamelMap> userDpetList() throws Exception{
		return userMapper.userDpetList();
	}
	
	/**
	 * 권한 리스트 가져오기
	 * @param 
	 * @return camelMap
	 * @throws Exception
	 */
	public List<CamelMap> userAuthList() throws Exception{
		return userMapper.userAuthList();
	}
	
	/**
	 * 아이디 중복체크
	 * @param commandMap
	 * @return Integer
	 * @throws Exception
	 */
	public int userIdSelect(String userId) throws Exception{
		return userMapper.userIdSelect(userId);
	}
	
	/**
	 * 유저 정보 저장
	 * @param commandMap
	 * @return String
	 * @throws Exception
	 */
	@Transactional
	public String userSave(Map<String,Object> commandMap) throws Exception{
		
		//비밀번호 가져오기
		String password = getHtmlStrCnvr(commandMap.get("userPw").toString());
		//PASSWORD SHA256 암호화
		String sha = "";
		MessageDigest sh = MessageDigest.getInstance("SHA-256");
		System.out.println(password);
		sh.update(password.getBytes());
		byte byteData[] = sh.digest();
		StringBuffer sb = new StringBuffer();
		for(int j=0; j < byteData.length; j++) {
			sb.append(Integer.toString((byteData[j]&0xff)+ 0x100, 16).substring(1));
		}
		sha = sb.toString();
		
		commandMap.put("userPassword", sha);
		
		userMapper.userSave(commandMap);
		
		String userPk = commandMap.get("userPk").toString(); 
		
		CamelMap dataMap = new CamelMap();
		dataMap.put("userId",commandMap.get("userId"));
		dataMap.put("userPk",commandMap.get("userPk"));
		dataMap.put("authId",commandMap.get("authorCode"));
		// 권한 테이블에 권한 및 아이디 삽입
		userMapper.userAuthSave(dataMap);
		
		return userPk;
	}
	
	/**
	 * 로그인 성공 실패 저장
	 * @param map
	 * @return void
	 */
	public void userInsertLog(CamelMap paraMap) {
		userMapper.userInsertLog(paraMap);
	}
	

}
