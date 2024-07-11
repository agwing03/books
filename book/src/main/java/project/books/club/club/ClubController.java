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
	@RequestMapping("/selectClubList.do")
	public SrchVO selectClubList(
			@RequestBody SrchVO vo
		) throws Exception {
		return clubService.selectClubList(vo);
	}

	/**
	 * 클럽 상세
	 * @param SrchVO
	 * @return SrchVO
	 */
	@RequestMapping("/selectClubDtl.do")
	public SrchVO selectClubDtl(
			@RequestBody SrchVO vo
		) throws Exception {
		return clubService.selectClubDtl(vo);
	}
	
	/**
	 * 클럽 등록
	 * @param ClubVO
	 * @return SrchVO
	 */
	@RequestMapping("/insertClub.do")
	public SrchVO insertClub(
			@RequestBody ClubVO vo,
			SrchVO rtnVo
		) throws Exception {
		vo = clubService.insertClub(vo);
		
		//리턴 데이터 셋 
		rtnVo.setProcCnt(vo.getProcCnt())
		rtnVo.setMsg(vo.getMsg())
		return rtnVo;
	}
	
	/**
	 * 클럽 수정
	 * @param ClubVO
	 * @return SrchVO
	 */
	@RequestMapping("/updateClub.do")
	public SrchVO updateClub(
			@RequestBody ClubVO vo,
			SrchVO rtnVo
		) throws Exception {
		vo = clubService.updateClub(vo);

		//리턴 데이터 셋 
		rtnVo.setProcCnt(vo.getProcCnt())
		rtnVo.setMsg(vo.getMsg())
		return rtnVo;
	}
	
	/**
	 * 클럽 삭제
	 * @param ClubVO
	 * @return SrchVO
	 */
	@RequestMapping("/deleteClub.do")
	public SrchVO deleteClub(
			@RequestBody ClubVO vo,
			SrchVO rtnVo
		) throws Exception {
		vo = clubService.deleteClub(vo);
		
		//리턴 데이터 셋 
		rtnVo.setProcCnt(vo.getProcCnt())
		rtnVo.setMsg(vo.getMsg())
		return rtnVo;
	}
	
	/**
	 * 클럽 탈퇴
	 * @param SrchVO
	 * @return SrchVO
	 */
	@RequestMapping("/updateClubOut.do")
	public SrchVO updateClubOut(
			@RequestBody SrchVO vo
		) throws Exception {
		return clubService.updateClubOut(vo);;
	}
}
 
