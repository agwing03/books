package com.tccins.template.auth;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tccins.template.auth.mapper.AuthMapper;
import com.tccins.template.common.CamelMap;
import com.tccins.template.common.dto.SearchDto;
import com.tccins.template.paging.Pagination;
import com.tccins.template.paging.PagingResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	 private final AuthMapper authMapper ;
	 
	 /**
     * 권한 리스트 조회
     * @param params
     * @return 권한 리스트
     */
    public PagingResponse<AuthVO> findAllAuth(final SearchDto params) throws Exception {
    	int count = authMapper.count(params);
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        List<AuthVO> list = authMapper.findAll(params);
        return new PagingResponse<>(list, pagination);
    }
    
    /**
     * 권한ID 중복 체크
     * @param commandMap
     * @return int
     */
    public int checkAuthId(Map<String,Object> commandMap) throws Exception {
    	
    	CamelMap chkMap = new CamelMap();
    	
    	chkMap.put("authId", commandMap.get("authId"));
    	
    	int checkId = authMapper.checkAuthId(chkMap);
    	return checkId;
    }
    
    /**
     * 권한 상세정보 조회
     * @param authId
     * @return 권한 상세정보
     */
    public AuthVO findAuthById(final String authId) {
        return authMapper.findById(authId);
    }
    
    /**
     * 권한 등록
     * @param commandMap
     * @return void
     */
    @Transactional
    public void saveAuth(Map<String,Object> commandMap) throws Exception {
    	
    	CamelMap saveMap = new CamelMap();
    	
    	// 데이터 쌓기
    	saveMap.put("authId", commandMap.get("authId"));
    	saveMap.put("authNm", commandMap.get("authNm"));
    	
    	// 권한 등록
    	authMapper.save(saveMap);
    }
    
    /**
     * 권한 수정
     * @param AuthVO
     * @return authId
     */
    @Transactional
    public String updateAuth(final AuthVO param) {
    	authMapper.update(param);
    	return param.getAuthId();
    }
    
    /**
     * 권한 삭제
     * @param commandMap
     * @return void
     */
    public void deleteAuth(Map<String,Object> commandMap) {
    	
    	CamelMap deleteMap = new CamelMap();
    	
    	//데이터 쌓기
    	deleteMap.put("authId", commandMap.get("authId"));
    	
    	// 권한 삭제
    	authMapper.deleteById(deleteMap);
    }
    
}
