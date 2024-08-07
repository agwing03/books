package com.tccins.template.common;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tccins.template.common.dto.CommonDto;
import com.tccins.template.common.mapper.CommonMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommonService {
	 private final CommonMapper commonMapper;
	 	/**
	     * 상단 메뉴 목록조회
	     * @return 권한 리스트
	     */
	    public List<CommonDto> selectTopMenuList(CommonDto commonDto) {
	        List<CommonDto> list = commonMapper.selectTopMenuList(commonDto);
	        return list;
	    }
	    
	 	/**
	     * 현재 URL로 현재선택한 메뉴ID, 상위메뉴ID 조회
	     * @return 권한 리스트
	     */
	    public CommonDto selectMenuInfo(String url) {
	    	return commonMapper.selectMenuInfo(url);
	    }
	 
	 	/**
	     * 좌측메뉴(상위) 목록조회
	     * @return 권한 리스트
	     */
	    public List<CommonDto> selectLeftUpperMenuList(CommonDto commonDto) {
	        List<CommonDto> list = commonMapper.selectLeftUpperMenuList(commonDto);
	        return list;
	    }
	    
	 	/**
	     * 좌측메뉴(하위) 목록조회
	     * @return 권한 리스트
	     */
	    public List<CommonDto> selectLeftMenuList(CommonDto commonDto) {
	        List<CommonDto> list = commonMapper.selectLeftMenuList(commonDto);
	        return list;
	    }
	    
	    /**
	     * 공통코드로 상세코드 리스트조회
	     * @return 공통코드 리스트
	     */
	    public List<CamelMap> selectCmCodeList(String cmCode){
	    	List<CamelMap> list = commonMapper.selectCmCodeList(cmCode);
	    	return list;
	    }
	    
	    /**
	     * 상세코드로 상세코드 이름 조회
	     * @return 상세코드 이름
	     */
	    public String selectDtCodeNm(String dtCode) {
	    	String dtCodeNm = commonMapper.selectDtCodeNm(dtCode);
	    	return dtCodeNm;
	    }
	    
	    
	    
}
