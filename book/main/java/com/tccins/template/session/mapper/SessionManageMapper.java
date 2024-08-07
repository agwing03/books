package com.tccins.template.session.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.tccins.template.session.SessionVO;

@Mapper
public interface SessionManageMapper {
	
	/**
	 * 세션 관리 및 로그인 설정 
	 * @param commandMap
	 * @param model
	 * @return
	 * @throws Exception
	 */
	SessionVO selectSessionInfo() throws Exception;
	
	/**
	 * 세션 관리 및 로그인 설정 수정 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	void updateSession(SessionVO sessionVO) throws Exception;

}
