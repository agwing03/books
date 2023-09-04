package project.books.club.club;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ClubController {
	
	private final ClubService clubService;
	 
	
	/**
	 * 모임 목록 조회
	 * @param clubNo
	 */
	@PostMapping("/club/getMeetingList.do")
	public ModelAndView getMeetingList(
			@RequestBody ClubVO vo,
			ModelAndView mv
		) throws Exception {
		
		vo = clubService.selectMeetingList(vo);
		
		//Return Data
		mv.addObject("meetingList",vo.getDataList());
		mv.addObject("totCnt",vo.getTotCnt());
		//Return View
		mv.setViewName("/club/meeting");
		return mv;
	}
}
 
