package com.tccins.template.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tccins.template.common.CamelMap;
import com.tccins.template.common.dto.CommonDto;

@Mapper
public interface CommonMapper {
    /**
     * 상단메뉴 리스트 조회
     * @return 
     */
    List<CommonDto> selectTopMenuList(CommonDto commonDto);
    
    /**
     * 현재 URL로 현재선택한 메뉴ID, 상위메뉴ID 조회
     * @return 
     */    
    CommonDto selectMenuInfo(String url);
	
    /**
     * 좌측메뉴(상위) 리스트 조회
     * @return 
     */
    List<CommonDto> selectLeftUpperMenuList(CommonDto commonDto);
    
    /**
     * 좌측메뉴(하위) 리스트 조회
     * @return 
     */
    List<CommonDto> selectLeftMenuList(CommonDto commonDto);
    
    /**
     * 공통코드로 상세코드 리스트조회
     * @return 
     */
    List<CamelMap> selectCmCodeList(String cmCode);
    
    /**
     * 상세코드로 상세코드 이름 조회
     * @return 상세코드 이름
     */
    String selectDtCodeNm(String dtCode);
}
