package project.books.sys.api.menu;

import lombok.Getter;
import lombok.Setter;
import project.books.sys.cmmn.CommonVO;

@Getter
@Setter
public class MenuVO extends CommonVO{
    private int menuNo;			//메뉴번호
    private String menuNm;		//메뉴명
    private int menuLv;			//메뉴레벨
    private int menuOrder;		//메뉴순서
    private int menuUpperNo;	//상위메뉴번호
    private String menuUrl;		//메뉴URL
    private String menuDc;		//메뉴설명
}