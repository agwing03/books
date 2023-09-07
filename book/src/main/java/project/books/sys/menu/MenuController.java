package project.books.sys.menu;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MenuController {
	
	private final MenuService menuService;
	 
	/**
	 * 매뉴 목록 조회
	 * @param clubNo
	 */
	@RequestMapping("/sys/getMenuList.do")
	public MenuVO getMenuList(
			@RequestBody MenuVO vo
		) throws Exception {
		vo = menuService.selectMenuList(vo);
		return vo;
	}
}
 
