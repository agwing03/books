package project.books.club.club;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.books.sys.util.CamelMap;


@Mapper
public interface ClubMapper {
	
    /**
	 * 모임 목록 조회
	 * @param clubNo
	 * @return list, integer
	 * @throw Exception
     */
	int selectMeetingListCnt(ClubVO vo);
	List<CamelMap> selectMeetingList(ClubVO vo);
	
}
