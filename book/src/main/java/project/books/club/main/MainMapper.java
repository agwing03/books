package project.books.club.main;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.books.club.cmmn.SrchVO;
import project.books.sys.util.CamelMap;


@Mapper
public interface MainMapper {
	
    /**
	 * 검색
	 * @param SrchVO
	 * @return list
	 * @throw Exception
     */
	List<CamelMap> selectCmmnSearch(SrchVO vo);
	
    /**
	 * 알람
	 * @param SrchVO
	 * @return list
	 * @throw Exception
     */
	List<CamelMap> selectCmmnAlarm(SrchVO vo);
}
