package project.books.club.club;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.books.club.book.BookMapper;
import project.books.sys.cmmn.SrchVO;
import project.books.sys.util.CamelMap;

@Service
@RequiredArgsConstructor
public class ClubService {
	
	private final ClubMapper clubMapper;
	private final BookMapper bookMapper; 
	
	/**
	 * 모임 목록 조회
	 * @param clubNo
	 * @return list
	 * @throw Exception
	 */
	public SrchVO selectMeetingList(SrchVO vo) throws Exception{
		int cnt = clubMapper.selectMeetingListCnt(vo);
		if(cnt > 0) {
			vo.setDataList(clubMapper.selectMeetingList(vo));
		}
		return vo;
	}
	
	/**
	 * 모임 후기 등록
	 * @param clubNo
	 * @throw Exception
	 */
	public void saveMeetingReview(ClubVO vo) throws Exception{
		//데이터 셋 
		List<CamelMap> reviewList = vo.getReviewList();
		
		//모임 생성
		if(vo.getMeetingNo() > 0) {
			clubMapper.insertMeeting(vo);
		}
		
		//서평 생성
		for (int i = 0; i < reviewList.size();) {
			CamelMap review = reviewList.get(i);
			review.put("clubNo", vo.getClubNo());
			review.put("meetingNo", vo.getMeetingNo());
			bookMapper.insertBookEval(review);
		}
	}
}
