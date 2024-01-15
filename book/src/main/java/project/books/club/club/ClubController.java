package project.books.club.club;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.books.sys.cmmn.SrchVO;

@RestController
@RequiredArgsConstructor
public class ClubController {
	
	private final ClubService clubService;
	 
	/**
	 * 모임 목록 조회
	 * @param clubNo
	 */
	@RequestMapping("/club/getMeetingList.do")
	public SrchVO getMeetingList(
			@RequestBody SrchVO vo
		) throws Exception {
		return clubService.selectMeetingList(vo);
	}
	
	/**
	 * 모임 후기 등록
	 * @param ClubVO
	 */
	@RequestMapping("/club/saveMeetingReview.do")
	public void saveMeetingReview(
			@RequestBody ClubVO vo
		) throws Exception {
		clubService.saveMeetingReview(vo);
	}
}
 
