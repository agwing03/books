package project.books.club.meeting;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.books.club.cmmn.SrchVO;
import project.books.sys.util.CamelMap;


@Mapper
public interface MeetingMapper {
	
    /**
	 * 모임 목록 조회
	 * @param MeetingVO
	 * @return List, Integer
	 * @throw Exception
     */
	int selectMeetingListCnt(SrchVO vo);
	List<CamelMap> selectMeetingList(SrchVO vo);
	
	/**
	 * 모임 생성
	 * @param MeetingVO
	 * @return integer
	 * @throw Exception
     */
	int insertMeeting(MeetingVO vo);
	
	/**
	 * 모임 참석 맴버 등록
	 * @param MeetingVO
	 * @return integer
	 * @throw Exception
     */
	int insertMeetingMember(MeetingVO vo);
	
	
	/**
	 * 모임 상세 조회
	 * @param int meetingNo
	 * @return MeetingVO
	 * @throw Exception
	 */
	MeetingVO selectMeetingInfo(int meetingNo);
	
	/**
	 * 맴버별 한줄평
	 * @param int meetingNo
	 * @return List<CamelMap>
	 * @throw Exception
     */
	List<CamelMap> selectMeetingReviewList(int meetingNo);
}
