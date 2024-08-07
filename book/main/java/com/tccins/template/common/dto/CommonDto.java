package com.tccins.template.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonDto {
	private String rootMenuId;  //최상위메뉴ID
	private String upperMenuId;  //상위메뉴ID
    private String menuId;      // 메뉴ID
    private String menuNm;      // 메뉴명
	private String upperMenuNm;  //상위메뉴명
    private String menuUrl;		// 메뉴URL
    private String userPk;
}
