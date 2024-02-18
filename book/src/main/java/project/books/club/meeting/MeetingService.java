package project.books.club.meeting;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;
import lombok.RequiredArgsConstructor;
import project.books.club.book.BookMapper;
import project.books.club.book.BookVO;
import project.books.club.cmmn.SrchVO;
import project.books.sys.util.CamelMap;

@Service
@RequiredArgsConstructor
public class MeetingService {
	private static final Logger log = (Logger) LoggerFactory.getLogger(MeetingService.class);
	private final MeetingMapper meetingMapper;
	private final BookMapper bookMapper; 
	
	/**
	 * 모임 목록 조회
	 * @param SrchVO
	 * @return List, Integer
	 * @throw Exception
	 */
	public SrchVO selectMeetingList(SrchVO vo) throws Exception{
		int cnt = meetingMapper.selectMeetingListCnt(vo);
		if(cnt > 0) {
			vo.setDataList(meetingMapper.selectMeetingList(vo));
		}
		return vo;
	}
	
	
	/**
	 * 모임 및 후기 등록
	 * @param MeetingVO
	 * @return MeetingVO
	 * @throw Exception
	 */
	public MeetingVO saveMeetingReview(MeetingVO vo) throws Exception{
		int mtCnt = 0;
		int mtMemberCnt = 0;
		int mtReviewCnt = 0;
		
		//필수 데이터
		BookVO bkVO = new BookVO();
		int clubNo = vo.getClubNo();//모임번호
		int userNo = vo.getUserNo();//접속자
		
		//모임 key check
		if(vo.getSaveFlag().equals("I")) {//등록
			//모임 등록
			mtCnt = meetingMapper.insertMeeting(vo);
			
			//KEY 반환
			int meetingNo = vo.getMeetingNo();
			
			//리뷰 리스트
			List<CamelMap> reviewList = vo.getReviewList();
			for (CamelMap review : reviewList) {
				
				//data set
				int bookNo = Integer.parseInt(review.get("bookNo").toString());
				int memberNo = Integer.parseInt(review.get("memberNo").toString());
				
				//참석자 등록
				vo.setMeetingNo(meetingNo);
				vo.setMemberNo(memberNo);
				mtMemberCnt += meetingMapper.insertMeetingMember(vo);
				
				//서평 등록
				bkVO.setClubNo(clubNo);
				bkVO.setBookNo(bookNo);
				bkVO.setMeetingNo(meetingNo);
				bkVO.setMemberNo(memberNo);
				bkVO.setBookScore(review.get("bookScore").toString());
				bkVO.setBookEval(review.get("bookEval").toString());
				bkVO.setUserNo(userNo); //접속자
				mtReviewCnt += bookMapper.insertBookEval(bkVO);
			}
		}
		vo.setSaveCnt(mtCnt+mtMemberCnt+mtReviewCnt);
		log.debug("##### 모임 및 후기 등록 saveMeetingReview > FLAG:"+vo.getSaveFlag()+" / 모임등록:"+mtCnt+"건 / 참석자등록:"+mtMemberCnt+"건 / 서평등록:"+mtReviewCnt+"건 #####");
		return vo;
	}
	
	
	/**
	 * 모임 상세 조회
	 * @param SrchVO
	 * @return SrchVO.CamelMap
	 * @throw Exception
	 */
	public MeetingVO getMeetingReview(MeetingVO vo) throws Exception{
		int meetingNo = vo.getMeetingNo();
		// 모임 정보
		vo = meetingMapper.selectMeetingInfo(meetingNo);
		// 맴버별 한줄평
		vo.setReviewList(meetingMapper.selectMeetingReviewList(meetingNo));
		return vo;
	}
	
	
	/**
	 * 모임 생성
	 * @target meetingFormPopup
	 * @param MeetingVO
	 * @return MeetingVO
	 * @throw Exception
	 */
	public MeetingVO saveMeeting(MeetingVO vo) throws Exception{
		//저장 카운트
		int mtCnt = 0;
		
		//모임 key check
		if(vo.getSaveFlag().equals("I")) {//등록
			//모임 등록
			mtCnt = meetingMapper.insertMeeting(vo);
		} else { //수정
			//모임 등록
			mtCnt = meetingMapper.updateMeeting(vo);
		}
		vo.setSaveCnt(mtCnt);
		log.debug("##### 모임 생성 saveMeeting > FLAG:"+vo.getSaveFlag()+" / 모임생성:"+mtCnt+"건 #####");
		return vo;
	}
	
	/**
	 * 모임후기 대상 목록 조회 
	 * @target meetingReviewFormPopup
	 * @param SrchVO
	 * @return SrchVO.List<CamelMap>
	 */
	public SrchVO selectMeetingPopupList(SrchVO vo) throws Exception{
		vo.setDataList(meetingMapper.selectMeetingPopupList());
		return vo;
	}
}

