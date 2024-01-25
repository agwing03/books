package project.books.club.meeting;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.books.club.cmmn.SrchVO;

@RestController
@RequiredArgsConstructor
public class MeetingController {
	
	private final MeetingService meetingService;
	 
	/**
	 * 모임 목록 조회
	 * @param clubNo
	 */
	@RequestMapping("/meeting/getMeetingList.do")
	public SrchVO getMeetingList(
			@RequestBody SrchVO vo
		) throws Exception {
		vo = meetingService.selectMeetingList(vo);
		return vo;
	}
	
	/**
	 * 모임 후기 등록
	 * @param ClubVO
	 */
	@RequestMapping("/meeting/saveMeetingReview.do")
	public void saveMeetingReview(
			@RequestBody MeetingVO vo
		) throws Exception {
		meetingService.saveMeetingReview(vo);
	}
}
 
