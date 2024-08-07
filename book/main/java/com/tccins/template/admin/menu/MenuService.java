package com.tccins.template.admin.menu;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tccins.template.admin.menu.mapper.MenuMapper;
import com.tccins.template.code.CodeVO;
import com.tccins.template.common.dto.SearchDto;
import com.tccins.template.paging.Pagination;
import com.tccins.template.paging.PagingResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuService {

	private final MenuMapper menuMapper;
	
	/**
	 * 메뉴 리스트 조회
	 * @param ProgramVO
	 * @return MenuVO
	 * @throws Exception
	 */
	public PagingResponse<MenuVO> selectMenuList(SearchDto params) throws Exception{
		
		int count = menuMapper.selectMenuCount(params);
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);
        
		List<MenuVO> list = menuMapper.selectMenuList(params);
		
		return new PagingResponse<>(list,pagination);
	}
	
	/**
	 * 메뉴 ajax목록 조회
	 * @param ProgramVO
	 * @return MenuVO
	 * @throws Exception
	 */
	public List<MenuVO> selectAjaxMenuList(MenuVO params) throws Exception{

		List<MenuVO> list = menuMapper.selectAjaxMenuList(params);
        return list;
		
	}
	
	/**
	 * 메뉴ID 중복 체크
	 * @param MenuVO
	 * @return int
	 * @throws Exception
	 */
	public int checkMenuId(MenuVO params) throws Exception{
		int checkMenuValue = menuMapper.checkMenuId(params);
        return checkMenuValue;
	}
	
	
	public String insertMenu(MenuVO params) throws Exception{
		
		String returnMsg="";
		try {
    		if("0".equals(params.getMenuLevel())) {
    			params.setUpperMenuId("ROOT");
    			params.setRootMenuId(params.getMenuId());
    		}
    		if("".equals(params.getHdZeroMenuId()) || "".equals(params.getHdOneMenuId()) || "".equals(params.getHdTwoMenuId())) {
	    		int returnCnt = menuMapper.insertMenu(params);
	    		if(returnCnt > 0) {
	    			returnMsg = "등록되었습니다.";
				}else {
					returnMsg = "등록실패";
				}
    		}else {
    			int updateResultCnt = menuMapper.updateMenu(params);
    			if(updateResultCnt>0) {
    				returnMsg = "수정되었습니다.";
    			}else {
    				returnMsg = "수정실패";
    			}
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
		}
		return returnMsg;
	}
	
	
	public String deleteMenu(MenuVO params) throws Exception{
		String returnMsg = "";
		try {
			int returnCnt = menuMapper.selectLowLevMenuCount(params);
			if(returnCnt>0) {
				returnMsg = "하위메뉴가 존재하여 삭제가 불가능합니다.";
			}else {
				int delResultCnt = menuMapper.deleteRootMenu(params);
				if(delResultCnt>0) {
					returnMsg = "삭제되었습니다.";
				}else {
					returnMsg = "삭제실패";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnMsg;
	}
}
