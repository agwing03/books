package com.tccins.template.common;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.tccins.template.common.dto.CommonDto;
import com.tccins.template.common.dto.MessageDto;
import com.tccins.template.config.CustomUserDetails;


public class LayoutModule {
	
	@Resource(name = "commonService")
	private CommonService commonService;
	
	
	/**공통 타임리프 레이아웃 정보**/
    @ModelAttribute("layout")
    public List<Object> layout(HttpServletRequest request
    						  ,@AuthenticationPrincipal CustomUserDetails userInfo) {
    	List<Object> listMap = new ArrayList<Object>();
    	if(userInfo != null) {
    		//사용자명
	    	listMap.add(userInfo.getUserNm()); //layout0
	    	//현재 URL로 현재선택한 메뉴ID, 상위메뉴ID 조회
	    	CommonDto commonDto = commonService.selectMenuInfo(request.getRequestURI());
	    	if(commonDto == null) commonDto = new CommonDto(); 
	    	listMap.add(commonDto); //layout1
	    	//상단메뉴
	    	commonDto.setUserPk(userInfo.getUserPk());
	    	List<CommonDto> topMenuList  = commonService.selectTopMenuList(commonDto);
	    	listMap.add(topMenuList); //layout2
	    	//좌측메뉴 (상위)
	    	List<CommonDto> leftMenuList = commonService.selectLeftUpperMenuList(commonDto);
	    	listMap.add(leftMenuList); //layout3
	    	//좌측메뉴 (하위)
	    	leftMenuList = commonService.selectLeftMenuList(commonDto);
	    	listMap.add(leftMenuList); //layout4
    	}
    	return listMap;
    }
    
    
    // 사용자에게 메시지를 전달하고, 페이지를 리다이렉트 한다.
    protected String showMessageAndRedirect(final MessageDto params, Model model) {
        model.addAttribute("params", params);
        return "common/messageRedirect";
    }
}
