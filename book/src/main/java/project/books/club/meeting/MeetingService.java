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
	
	int mtCnt = 0;
	
	/**
	 * 모임 및 모임후기 목록 조회
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
	 * 모임 정보 조회
	 * @param SrchVO
	 * @return SrchVO.CamelMap
	 * @throw Exception
	 */
	public SrchVO selectMeeting(SrchVO vo) throws Exception{
		//모임 정보
		vo.setDataMap(meetingMapper.selectMeeting(vo));
		//참석자 목록
		vo.setIntList(meetingMapper.selectMeetingMemberList(vo));
		return vo;
	}
	
	
	/**
	 * 모임후기 상세 조회
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
	 * 모임 저장
	 * @target meetingFormPopup
	 * @param MeetingVO
	 * @return MeetingVO
	 * @throw Exception
	 */
	public MeetingVO saveMeeting(MeetingVO vo) throws Exception{
		//모임 참석자 추가된 경우
		List<Integer> memberList = vo.getMemberNoArr();
				
		//모임 key check
		if(vo.getSaveFlag().equals("I")) {//등록
			//모임 등록
			mtCnt = meetingMapper.insertMeeting(vo);
		} else { //수정
			//모임 등록
			mtCnt = meetingMapper.updateMeeting(vo);
		}
		
		//참석자 존재 여부
		if(memberList.size() > 0) {
			if(vo.getSaveFlag().equals("U")) {
				mtCnt = meetingMapper.deleteMeetingMember(vo);
			}
			//맴버 등록
			for (int i = 0; i < memberList.size(); i++) {
				vo.setMemberNo(memberList.get(i));
				mtCnt = meetingMapper.insertMeetingMember(vo);
			}
		}
		
		vo.setSaveCnt(mtCnt);
		log.debug("##### 모임 생성 saveMeeting > FLAG:"+vo.getSaveFlag()+" / 모임생성:"+mtCnt+"건 #####");
		return vo;
	}
	
	/**
	 * 모임 삭제
	 * @target meetingFormPopup
	 * @param MeetingVO
	 * @return MeetingVO
	 * @throw Exception
	 */
	public MeetingVO deleteMeeting(MeetingVO vo) throws Exception{
		vo.setSaveCnt(meetingMapper.deleteMeeting(vo));
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
			vo.setSaveCnt(mtMeetingUCnt+mtReviewICnt+mtReviewDCnt);
		}
		log.debug("##### 모임 및 후기 등록 saveMeetingReview > 모임U:"+mtMeetingUCnt+"건 / 도서I:"+mtBookICnt+"건 / 리뷰D:"+mtReviewDCnt+"건 / 리뷰I:"+mtReviewICnt+"건 #####");
		return vo;
	}
}

