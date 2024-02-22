package project.books.club.meeting;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.books.club.cmmn.SrchVO;
import project.books.sys.util.CamelMap;


@Mapper
public interface MeetingMapper {
	
    /**
	 * 모임 및 모임후기 목록 조회
	 * @param SrchVO
	 * @return MeetingVO, Integer
     */
	int selectMeetingListCnt(SrchVO vo);
	List<CamelMap> selectMeetingList(SrchVO vo);
	
	/**
	 * 모임 정보 조회
	 * @param SrchVO
	 * @return CamelMap
	 */
	CamelMap selectMeeting(SrchVO vo);
	
	/**
	 * 모임 등록
	 * @target meetingFormPopup
	 * @param MeetingVO
	 * @return integer
     */
	int insertMeeting(MeetingVO vo);
	
	/**
	 * 모임 수정
	 * @param MeetingVO
	 * @return integer
     */
	int updateMeeting(MeetingVO vo);
	
	/**
	 * 모임 삭제
	 * @param MeetingVO
	 * @return integer
     */
	int deleteMeeting(MeetingVO vo);

	/**
	 * 모임 참적자 목록
	 * @param SrchVO
	 * @return List<Integer> memberNo
	 */
	List<Integer> selectMeetingMemberList(SrchVO vo);
	
	/**
	 * 모임 참석자 등록
	 * @param MeetingVO
	 * @return integer
     */
	int insertMeetingMember(MeetingVO vo);
	
	/**
	 * 모임 참석자 삭제
	 * @param MeetingVO
	 * @return integer
     */
	int deleteMeetingMember(MeetingVO vo);
	
	/**
	 * 모임 상세 조회
	 * @param int meetingNo
	 * @return MeetingVO
	 */
	MeetingVO selectMeetingInfo(int meetingNo);
	
	/**
	 * 맴버별 한줄평 목록
	 * @param int meetingNo
	 * @return List<CamelMap>
     */
	List<CamelMap> selectMeetingReviewList(int meetingNo);
	
	/**
	 * 모임후기 대상 목록 조회 
	 * @target meetingReviewFormPopup
	 * @param SrchVO
	 * @return SrchVO.List<CamelMap>
	 */
	List<CamelMap> selectMeetingPopupList();
	
	
}
