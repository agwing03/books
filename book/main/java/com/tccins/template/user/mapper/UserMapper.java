package com.tccins.template.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.tccins.template.common.CamelMap;
import com.tccins.template.common.dto.SearchDto;
import com.tccins.template.config.CustomUserDetails;


@Mapper
public interface UserMapper {
	
    /**
     * 입력 ID로 사용자PK 조회
     * @param id - PK
     * @return 게시글 상세정보
     */
    String selectUserPk(String id);
	

    /**
     * 사용자 정보조회
     * @param id - PK
     * @return 게시글 상세정보
     */
    CustomUserDetails selectUser(String id);
    
    /**
     * 로그인 사용자 권한목록조회
     * @param id - PK
     * @return 게시글 상세정보
     */
    List<String> selectAuthorities(String id);
    
	/**
	 * 로그인 성공시 실패 횟수 초기화
	 * @param String
	 * @return
	 * @throws Exception
	 */
	void resetFailCnt(String userId);
	
	/**
	 * 실패 횟수 증가
	 * @param String
	 * @return int
	 * @throws Exception
	 */
	void updateFailCnt(CustomUserDetails userVO);
	
	/**
	 * 계정 잠김으로 업데이트
	 * @param String
	 * @return 
	 * @throws Exception
	 */
	void updateUserLock(String userId);
	
	/**
	 * 계정 리셋
	 * @param String
	 * @return 
	 * @throws Exception
	 */
	void updateFailCntReset(String userId);
	
	/**
     * 세션 시간 받아오기
     * @param
     * @return
     * @throws Exception
     */
	int selectSessionMin() throws Exception;
	
	/**
	 * 유저 리스트 조회
	 * @param 
	 * @throws Exception
	 * @return
	 */
	List<CustomUserDetails> selectUserList(SearchDto params) throws Exception;
	
	/**
	 * 유저 갯수
	 * @param 
	 * @throws Exception
	 * @return
	 */
	int count(SearchDto params) throws Exception;
	
	/**
	 * 유저 삭제하기
	 * @param map
	 * @return void
	 * @throws Exception
	 */
	void userDelete(CamelMap camelMap) throws Exception;
	
	/**
	 * 유저 권한 삭제하기
	 * @param map
	 * @return void
	 * @throws Exception
	 */
	void userAuthDelete(CamelMap camelMap) throws Exception;
	
	/**
	 * 유저 잠금 해제
	 * @param map
	 * @return void
	 * @throws Exception
	 */
	void userUnLockUpdate(CamelMap camelMap) throws Exception;
	
	/**
	 * 유저 사용여부 변경
	 * @param map
	 * @return void
	 * @throws Exception
	 */
	void userUseUpdate(CamelMap camelMap) throws Exception;
	
	/**
	 * 유저 비밀번호 변경
	 * @param map
	 * @return void
	 * @throws Exception
	 */
	void userPwUpdate(CamelMap camelMap) throws Exception;
	
	/**
	 * 부서 리스트 가져오기
	 * @param 
	 * @return camelMap
	 * @throws Exception
	 */
	 List<CamelMap> userDpetList() throws Exception;
	 
	 /**
	  * 권한 리스트 가져오기
	  * @param 
	  * @return camelMap
	  * @throws Exception
	  */
	 List<CamelMap> userAuthList() throws Exception;
	 
	/**
	 * 아이디 중복체크
	 * @param commandMap
	 * @return Integer
	 * @throws Exception
	 */
	int userIdSelect(String userId) throws Exception;
	
	/**
	 * 유저 정보 저장
	 * @param commandMap
	 * @return String
	 * @throws Exception
	 */
	void userSave(Map<String,Object> commandMap) throws Exception;
	
	/**
	 * 권한 삽입
	 * @param camelMap
	 * @return void
	 * @throws Exception
	 */
	void userAuthSave(CamelMap dataMap) throws Exception;
	
	/**
	 * 로그인 성공 실패 저장
	 * @param map
	 * @return void
	 */
	void userInsertLog(CamelMap paraMap);
}
