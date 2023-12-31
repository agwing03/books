package project.books.club.club;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ClubController {
	
	private final ClubService clubService;
	 
	/**
	 * 모임 목록 조회
	 * @param clubNo
	 */
	@RequestMapping("/club/getMeetingList.do")
	public ClubVO getMeetingList(
			@RequestBody ClubVO vo
		) throws Exception {
		return clubService.selectMeetingList(vo);
	}
}
 
