package com.tccins.template.log;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tccins.template.common.CamelMap;
import com.tccins.template.common.dto.SearchDto;
import com.tccins.template.common.mapper.CommonMapper;
import com.tccins.template.log.mapper.LogMapper;
import com.tccins.template.paging.Pagination;
import com.tccins.template.paging.PagingResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogService {
	
	private final LogMapper logMapper;
	
	private final CommonMapper commonMapper;
	
	/**
	 * 로그 저장
	 * @param commandMap
	 * @param req
	 * @throws Exception
	 */
	public void insertLog(CamelMap camelMap) {
			
			//여기서 
			
			// 로그 저장
			logMapper.insertLog(camelMap);
	}
	
	/**
	 * 접속 로그 리스트 조회
	 * @param LogVO
	 * @return LogVO
	 * @throws Exception
	 */
	public PagingResponse<CamelMap> selectLoginLogList(SearchDto params) throws Exception{
		
		int count = logMapper.selectLoginLogCnt(params);
		Pagination pagination = new Pagination(count, params);
		params.setPagination(pagination);
		
		List<CamelMap> list = logMapper.selectLoginLogList(params);
		
		return new PagingResponse<>(list, pagination);
	}
	
	/**
	 * 에러 로그 리스트 조회
	 * @param LogVO
	 * @return LogVO
	 * @throws Exception
	 */
	public PagingResponse<CamelMap> selectErrorLogList(SearchDto params) throws Exception{
		
		int count = logMapper.selectErrorLogCnt(params);
		Pagination pagination = new Pagination(count, params);
		params.setPagination(pagination);
		
		List<CamelMap> list = logMapper.selectErrorLogList(params);
		
		return new PagingResponse<>(list, pagination);
	}
	
	/**
	 * 로그 리스트 조회
	 * @param LogVO
	 * @return LogVO
	 * @throws Exception
	 */
	public PagingResponse<CamelMap> selectLogList(SearchDto params) throws Exception{
		
		int count = logMapper.logCount(params);
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);
        
        List<CamelMap> list = logMapper.selectLogList(params);
        
		return new PagingResponse<>(list, pagination);
	}
	
	/**
	 * 로그 상세 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public CamelMap selectLogDetail(Map<String,Object> param) throws Exception{
		return logMapper.selectLogDetail(param);
	}
	
	/**
	 * 에러 로그 저장
	 * @param commandMap
	 * @return CamelMap
	 * @throws Exception
	 */
	public void errorLogInsert(Map<String,Object> commandMap){
		logMapper.errorLogInsert(commandMap);
	}

}
