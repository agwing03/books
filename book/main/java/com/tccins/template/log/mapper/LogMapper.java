package com.tccins.template.log.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.tccins.template.common.CamelMap;
import com.tccins.template.common.dto.SearchDto;

@Mapper
public interface LogMapper {

	/**
	 * 로그 저장
	 * @param commandMap
	 */
	void insertLog(CamelMap camelMap);
	
	/**
	 * 로그 조회
	 * @param String
	 * @throws Exception
	 * @return Map
	 */
	CamelMap selectMenuCd(String menuUrl) throws Exception;
	
	/**
	 * 접속 로그 리스트 조회
	 * @param LogVO
	 * @return LogVO
	 * @throws Exception
	 */
	List<CamelMap> selectLoginLogList(SearchDto params) throws Exception;
	
	/**
	 * 접속 로그 리스트 카운트
	 * @param SearchDto
	 * @return SearchDto
	 * @throws Exception
	 */
	int selectLoginLogCnt(SearchDto params) throws Exception;
	
	/**
	 * 에러 로그 리스트 조회
	 * @param LogVO
	 * @return LogVO
	 * @throws Exception
	 */
	List<CamelMap> selectErrorLogList(SearchDto params) throws Exception;
	
	/**
	 * 에러 로그 리스트 카운트
	 * @param SearchDto
	 * @return SearchDto
	 * @throws Exception
	 */
	int selectErrorLogCnt(SearchDto params) throws Exception;
	
	/**
	 * 로그 리스트 조회
	 * @param LogVO
	 * @return LogVO
	 * @throws Exception
	 */
	List<CamelMap> selectLogList(SearchDto params) throws Exception;
	
	/**
	 * 로그 리스트 카운트
	 * @param SearchDto
	 * @return SearchDto
	 * @throws Exception
	 */
	int logCount(SearchDto params) throws Exception;
	
	/**
	 * 로그 상세 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	CamelMap selectLogDetail(Map<String,Object> param) throws Exception;
	
	/**
	 * 에러 로그 저장
	 * @param commandMap
	 * @return CamelMap
	 * @throws Exception
	 */
	void errorLogInsert(Map<String,Object> commandMap);
}
