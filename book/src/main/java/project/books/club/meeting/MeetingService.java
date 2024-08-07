package project.books.club.meeting;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;
import lombok.RequiredArgsConstructor;
import project.books.club.book.BookMapper;
import project.books.club.book.BookVO;
import project.books.club.cmmn.MsgCodes;
import project.books.club.cmmn.SrchVO;
import project.books.sys.util.CamelMap;

@Service
@RequiredArgsConstructor
public class MeetingService {
	private static final Logger log = (Logger) LoggerFactory.getLogger(MeetingService.class);
	private final MeetingMapper meetingMapper;
	private final BookMapper bookMapper; 
	
	/**	
	 * 모임 및 모임후기 목록 조회
	 * @param SrchVO
	 * @return SrchVO
	 * @throw Exception
	 */
	public SrchVO selectMeetingList(SrchVO vo) throws Exception{
		vo.setDataList(meetingMapper.selectMeetingList(vo));
		vo.setTotCnt(vo.getDataList().size());
		return vo;
	}
	
	/**
	 * 모임 및 모임후기 상세 조회
	 * @param MeetingVO
	 * @return MeetingVO
	 * @throw Exception
	 */
	public MeetingVO selectMeetingDtl(MeetingVO vo) throws Exception{
		int meetingNo = vo.getMeetingNo();
		// 모임 정보
		vo = meetingMapper.selectMeetingDtl(meetingNo);
		//참석자 및 한줄평 목록
		vo.setReviewList(meetingMapper.selectMeetingMemberReviewList(meetingNo));
		return vo;
	}
	
	/**
	 * 모임 정보 조회(등록, 수정 팝업)
	 * @param MeetingVO
	 * @return MeetingVO
	 * @throw Exception
	 */
	public MeetingVO selectMeetingInfo(MeetingVO vo) throws Exception{
		vo = meetingMapper.selectMeetingInfo(vo.getMeetingNo());
		return vo;
	}
	
	/**
	 * 모임 등록
	 * @param MeetingVO
	 * @return MeetingVO
	 * @throw Exception
	 */
	public MeetingVO insertMeeting(MeetingVO vo) throws Exception{
		//뱐수 선언
		int mtCnt = 0, mbCnt = 0;
		List<Integer> memberNoList = vo.getMemberNoArr();
		
		//모임 등록(VO 등록된 모임번호 담김)
		mtCnt = meetingMapper.insertMeeting(vo);
		
		if(mtCnt > 0) {
			//참석자 존재 여부
			if(!memberNoList.isEmpty() && memberNoList.size() > 0) {
				//참석자 등록
				for (int i = 0; i < memberNoList.size(); i++) {
					vo.setMemberNo(memberNoList.get(i));
					mbCnt += meetingMapper.insertMeetingMember(vo);
				}
			}
			log.debug("##### 모임 생성 insertMeeting / 모임생성:"+mtCnt+"건 / 맴버등록:"+mbCnt+"건 #####");
			
			//모임 등록 메세지
			vo.setMsg(MsgCodes.MEETING_INSERT);
		} else {
			vo.setMsg(MsgCodes.SYSTEM_PROCESS_FAILED);
		}
		return vo;
	}
	
	/**
	 * 모임 수정
	 * @param MeetingVO
	 * @return MeetingVO
	 * @throw Exception
	 */
	public MeetingVO updateMeeting(MeetingVO vo) throws Exception{
		//뱐수 선언
		int mtCnt = 0, mbCnt = 0;
		List<Integer> memberNoList = vo.getMemberNoArr();
		
		//모임 등록(VO 등록된 모임번호 담김)
		mtCnt = meetingMapper.updateMeeting(vo);
		
		if(mtCnt > 0) {
			//참석자 존재 여부
			if(!memberNoList.isEmpty() && memberNoList.size() > 0) {
				//참석자 삭제 후 재등록
				mtCnt = meetingMapper.deleteMeetingMember(vo);
				
				//참석자 재등록
				for (int i = 0; i < memberNoList.size(); i++) {
					vo.setMemberNo(memberNoList.get(i));
					mbCnt += meetingMapper.insertMeetingMember(vo);
				}
			}
			log.debug("##### 모임 수정 updateMeeting / 모임수정:"+mtCnt+"건 / 맴버재등록:"+mbCnt+"건 #####");
			
			//모임 등록 메세지
			vo.setMsg(MsgCodes.MEETING_UPDATE);
		} else {
			vo.setMsg(MsgCodes.SYSTEM_PROCESS_FAILED);
		}
		return vo;
	}
	
	/**
	 * 모임 삭제
	 * @param MeetingVO
	 * @return MeetingVO
	 * @throw Exception
	 */
	public MeetingVO deleteMeeting(MeetingVO vo) throws Exception{
		//수행 건수
		vo.setProcCnt(meetingMapper.deleteMeeting(vo));
		if(vo.getProcCnt() > 0) {
			vo.setMsg(MsgCodes.MEETING_DELETE);
		} else {
			vo.setMsg(MsgCodes.SYSTEM_PROCESS_FAILED);
		}
		return vo;
	}
	
	/**
	 * 모임후기 저장(등록, 수정)
	 * @param MeetingVO
	 * @return MeetingVO
	 * @throw Exception
	 */
	public MeetingVO saveMeetingReview(MeetingVO vo) throws Exception{
		//변수
		int mtMtUCnt = 0;
		int mtRvICnt = 0;
		int mtRvDCnt = 0;
		int mtBkICnt = 0;
		BookVO bkVO = new BookVO();
		
		//필수 데이터
		int clubNo = vo.getClubNo();		//클럽번호
		int meetingNo = vo.getMeetingNo();	//모임번호
		int userNo = vo.getUserNo();		//접속자
		String comment = vo.getComment();
		
		//리뷰 리스트
		List<BookVO> reviewList = vo.getReviewList();
		
		//모임 코멘트 저장
		vo.setComment(comment);
		vo.setReviewYn(true);
		vo.setUserNo(userNo);
		mtMtUCnt = meetingMapper.updateMeeting(vo);
		
		//리스트 목록
		if(reviewList.size() > 0) {
			//리뷰 삭제 
			bkVO.setMeetingNo(meetingNo);
			mtRvDCnt = bookMapper.deleteBookEval(bkVO);
			
			//리뷰 등록
			for (BookVO review : reviewList) {
				int bookNo; //도서번호
				
				//신규 도서 등록
				if(review.getNewBook()) {
					mtBkICnt += bookMapper.insertBook(review);
				}
				
				//서평 등록
				review.setClubNo(clubNo);
				review.setMeetingNo(meetingNo);
				review.setUserNo(userNo); //접속자
				if(!review.getProgressYn()) { //모임 진행자 제외
					mtRvICnt += bookMapper.insertBookEval(review);
				}
			}
			vo.setProcCnt(mtMtUCnt+mtBkICnt+mtRvDCnt+mtRvICnt);
		}
		
		log.debug("##### 모임 및 후기 등록 saveMeetingReview > 모임코멘트:"+mtMtUCnt+"건 / 도서등록:"+mtBkICnt+"건 / 이전리뷰삭제:"+mtRvDCnt+"건 / 리뷰등록:"+mtRvICnt+"건 #####");
		
		if(vo.getProcCnt() > 0) {
			vo.setMsg(MsgCodes.MEETING_REVIEW_SAVE);
		} else {
			vo.setMsg(MsgCodes.SYSTEM_PROCESS_FAILED);
		}
		return vo;
	}
}

