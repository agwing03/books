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
	 * 모임 정보 조회(변경 팝업)
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
	 * 모임후기 저장
	 * @param MeetingVO
	 * @return MeetingVO
	 * @throw Exception
	 */
	public MeetingVO saveMeetingReview(MeetingVO vo) throws Exception{
		int mtMeetingUCnt = 0;
		int mtReviewICnt = 0;
		int mtReviewDCnt = 0;
		int mtBookICnt = 0;
		
		//필수 데이터
		BookVO bkVO = new BookVO();
		int clubNo = vo.getClubNo();//모임번호
		int userNo = vo.getUserNo();//접속자
		int meetingNo = vo.getMeetingNo();
		String comment = vo.getComment();
		
		//리뷰 리스트
		List<CamelMap> reviewList = vo.getReviewList();
		
		//모임 코멘트 저장
		vo.setComment(comment);
		mtMeetingUCnt = meetingMapper.updateMeeting(vo);
		
		//리스트 목록
		if(reviewList.size() > 0) {
			//리뷰 삭제 및 재등록
			bkVO.setMeetingNo(meetingNo);
			mtReviewDCnt = bookMapper.deleteBookEval(bkVO);
			
			int bookNo;
			for (CamelMap review : reviewList) {
				//data set
				String bookNew = review.get("bookNew").toString();
				int memberNo = Integer.parseInt(review.get("memberNo").toString());
				String progressYn = review.get("progressYn").toString();
				
				//신규 도서 등록
				if(bookNew.equals("Y")) {
					bkVO.setBookTitle(review.get("bookTitle").toString());
					bkVO.setBookWriter(review.get("bookWriter").toString());
					mtBookICnt = bookMapper.insertBook(bkVO);
					//신규 도서번호
					bookNo = bkVO.getBookNo();
				} else {
					bookNo = Integer.parseInt(review.get("bookNo").toString());
				}
				
				//서평 등록
				bkVO.setClubNo(clubNo);
				bkVO.setBookNo(bookNo);
				bkVO.setMeetingNo(meetingNo);
				bkVO.setMemberNo(memberNo);
				if(progressYn.equals("N")) { //모임 진행자 제외
					bkVO.setBookScore(review.get("bookScore").toString());
					bkVO.setBookEval(review.get("bookEval").toString());
				}
				bkVO.setUserNo(userNo); //접속자
				mtReviewICnt += bookMapper.insertBookEval(bkVO);
			}
			vo.setProcCnt(mtMeetingUCnt+mtReviewICnt+mtReviewDCnt);
		}
		log.debug("##### 모임 및 후기 등록 saveMeetingReview > 모임U:"+mtMeetingUCnt+"건 / 도서I:"+mtBookICnt+"건 / 리뷰D:"+mtReviewDCnt+"건 / 리뷰I:"+mtReviewICnt+"건 #####");
		return vo;
	}
}

