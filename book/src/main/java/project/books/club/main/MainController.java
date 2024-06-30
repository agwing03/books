package project.books.club.main;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.books.club.cmmn.SrchVO;

@RestController
@RequiredArgsConstructor
public class MainController {
	
	private final MainService mainService;
	
	/**
	 * 화면 프로필
	 * @param SrchVO
	 */
	@RequestMapping("/getMemberProfile.do")
	public SrchVO getMemberProfile(
			@RequestBody SrchVO vo
		) throws Exception {
		return mainService.selectCmmnMemberProfile(vo);
	}
	
	/**
	 * 검색
	 * @param SrchVO
	 */
	@RequestMapping("/getSearch.do")
	public SrchVO getSearch(
			@RequestBody SrchVO vo
		) throws Exception {
		return mainService.selectCmmnSearch(vo);
	}
	
	/**
	 * 알림
	 * @param SrchVO
	 */
	@RequestMapping("/getAlarm.do")
	public SrchVO getAlarm(
			@RequestBody SrchVO vo
		) throws Exception {
		return mainService.selectCmmnAlarm(vo);
	}
}
 