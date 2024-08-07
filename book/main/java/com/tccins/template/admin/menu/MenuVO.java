package com.tccins.template.admin.menu;

import java.io.Serializable;

import lombok.Data;

@Data
public class MenuVO implements Serializable{
	
	private static final long serialVersionUID = -8274004534207618049L;
	
	private String hdZeroMenuId;
	private String hdOneMenuId;
	private String hdTwoMenuId;
	
	//최상위메뉴
	private String upperMenuId;
	private String menuId;
	private String menuNm;
	private String menuOrder;
	private String menuUrl;
	private String useYn;
	private String menuDesc;
	private String menuLevel;
	private String rootMenuId;
	
	
	
	

	
	
	
	
	
}
