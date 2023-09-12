package project.books.sys.api.menu;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.books.sys.util.CamelMap;


@Mapper
public interface MenuMapper {
	
    /**
	 * 매뉴 목록 조회
	 * @return list
	 * @throw Exception
     */
	List<CamelMap> selectMenuList(MenuVO vo);
	
}
