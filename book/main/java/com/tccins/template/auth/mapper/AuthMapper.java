package com.tccins.template.auth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tccins.template.auth.AuthVO;
import com.tccins.template.common.CamelMap;
import com.tccins.template.common.dto.SearchDto;

@Mapper
public interface AuthMapper {
	
	  /**
     * 권한 리스트 조회
     * @return 권한 리스트
     */
    List<AuthVO> findAll(SearchDto params);
    
    /**
     * 권한 수 카운팅
     * @return 권한 수
     */
    int count(SearchDto params);
    
    
    /**
     * 권한ID 중복체크 
     * @return 권한 수
     */
    int checkAuthId(CamelMap camelMap);
    
    
	 /**
     * 권한 등록
     * @param params - 권한 정보
     */
    void save(CamelMap camelMap);

    /**
     * 권한 상세정보 조회
     * @param id - authId
     * @return 권한 상세정보
     */
    AuthVO findById(String authId);
    
    /**
     * 권한 수정
     * @param params - 권한 정보
     */
    void update(AuthVO params);

    /**
     * 권한 삭제
     * @param id - authId
     */
    void deleteById(CamelMap camelMap);

  
}
