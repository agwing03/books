package project.books.club.club;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ClubController {
	
	private final ClubService clubService;
	
	/**
	 * 클럽 목록
	 * @param SrchVO
	 * @return SrchVO
	 */
	@RequestMapping("/getClubList.do")
	public SrchVO getClubList(
			@RequestBody SrchVO vo
		) throws Exception {
		return clubService.selectClubList(vo);
	}

	/**
	 * 클럽 상세
	 * @param SrchVO
	 * @return SrchVO
	 */
	@RequestMapping("/getClubDtl.do")
	public SrchVO getClubDtl(
			@RequestBody SrchVO vo
		) throws Exception {
		return clubService.selectClubDtl(vo);
	}
	
	/**
	 * 클럽 등록, 수정, 삭제
	 * @param ClubVO
	 * @return SrchVO
	 */
	@RequestMapping("/setClub.do")
	public SrchVO setClub(
			@RequestBody ClubVO vo,
			SrchVO rtnVo
		) throws Exception {
		if(vo.getFlag().eqauls("I")) {
			rtnVo = clubService.insertClub(vo);
		}else if(vo.getFlag().eqauls("U")) {
			rtnVo = clubService.updateClub(vo);
		}else if(vo.getFlag().eqauls("D")) {
			rtnVo = clubService.deleteClub(vo);
		}
		return rtnVo;
	}
}
 
