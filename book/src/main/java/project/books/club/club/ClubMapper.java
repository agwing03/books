package project.books.club.club;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.books.sys.cmmn.SrchVO;
import project.books.sys.util.CamelMap;


@Mapper
public interface ClubMapper {
	
    /**
	 * 모임 목록 조회
	 * @param clubNo
	 * @return list, integer
	 * @throw Exception
     */
	int selectMeetingListCnt(SrchVO vo);
	List<CamelMap> selectMeetingList(SrchVO vo);
	
	
	
	/**
	 * 모임 생성
	 * @param ClubVO
	 * @throw Exception
     */
	int insertMeeting(ClubVO vo);
}
