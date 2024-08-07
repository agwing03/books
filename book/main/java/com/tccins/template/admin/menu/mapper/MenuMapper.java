package com.tccins.template.admin.menu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tccins.template.admin.menu.MenuVO;
import com.tccins.template.common.dto.SearchDto;

@Mapper
public interface MenuMapper {

	
	/**
	 * 메뉴 리스트 조회
	 * @param SearchDto
	 * @return LogVO
	 * @throws Exception
	 */
	List<MenuVO> selectMenuList(SearchDto params) throws Exception;
	
	/**
	 * 메뉴 리스트 조회
	 * @param SearchDto
	 * @return int
	 * @throws Exception
	 */
	int selectMenuCount(SearchDto params) throws Exception;
	
	
	/**
	 * 메뉴 리스트 조회
	 * @param SearchDto
	 * @return LogVO
	 * @throws Exception
	 */
	
	List<MenuVO> selectAjaxMenuList(MenuVO params) throws Exception;
	
	
	/**
	 * 메뉴 ID 중복체크
	 * @param MenuVO
	 * @return int
	 * @throws Exception
	 */
	int checkMenuId(MenuVO params)throws Exception;
	
	
	/**
	 * 최상위메뉴 등록
	 * @param MenuVO
	 * @throws Exception
	 */
	
	int insertMenu(MenuVO params) throws Exception;
	
	
	
	/**
	 * 하위메뉴 갯수 조회
	 * @param MenuVO
	 * @throws Exception
	 */
	int selectLowLevMenuCount(MenuVO params) throws Exception;
	
	
	/**
	 * 최상위메뉴 수정
	 * @param MenuVO
	 * @throws Exception
	 */
	int updateMenu(MenuVO params) throws Exception;
	
	
	/**
	 * 최상위 메뉴 삭제
	 * @param MenuVO
	 * @throws Exception
	 */
	int deleteRootMenu(MenuVO params) throws Exception;
	
	/**
	 * 하위메뉴들 삭제
	 * @param MenuVO
	 * @throws Exception
	 */
	int deleteMenu(MenuVO params) throws Exception;
	
	
}
