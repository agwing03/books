package project.books.club.cmmn.menu;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuService {
	
	private final MenuMapper menuMapper;
	
	/**
	 * 매뉴 목록 조회
	 * @return list
	 * @throw Exception
	 */
	public MenuVO selectMenuList(MenuVO vo) throws Exception{
		vo.setMenuList(menuMapper.selectMenuList(vo));
		return vo; 
	}
}
