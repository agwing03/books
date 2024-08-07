package com.tccins.template.userAuth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tccins.template.auth.AuthVO;
import com.tccins.template.common.CamelMap;
import com.tccins.template.common.dto.SearchDto;
import com.tccins.template.userAuth.UserAuthVO;

@Mapper
public interface UserAuthMapper {
	
	/**
	 * 유저 전체 조회
	 * @param params
	 * @return
	 */
	List<UserAuthVO> findAllUser(SearchDto params);
	
	/**
	 * 유저 수 조회
	 * @param params
	 * @return
	 */
	int userCount(SearchDto params);
	
	/**
	 * 권한 전체 조회
	 * @param params
	 * @return
	 */
	List<AuthVO> authList(SearchDto params);
	
	/**
	 * 유저 권한 수정
	 * @param
	 * @return camelMap
	 * @throws Exception
	 */
	void userAuthUpdate(CamelMap camelMap);
}
