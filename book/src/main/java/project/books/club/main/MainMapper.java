package project.books.club.main;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.books.club.cmmn.SrchVO;
import project.books.sys.util.CamelMap;


@Mapper
public interface MainMapper {
	
    /**
	 * 화면 프로필
	 * @param SrchVO
	 * @return CamelMap
	 * @throw Exception
     */
	CamelMap selectCmmnMemberProfile(SrchVO vo);
	
    /**
	 * 검색
	 * @param SrchVO
	 * @return List
	 * @throw Exception
     */
	List<CamelMap> selectCmmnSearch(SrchVO vo);
	
    /**
	 * 알람
	 * @param SrchVO
	 * @return List
	 * @throw Exception
     */
	List<CamelMap> selectCmmnAlarm(SrchVO vo);
}
