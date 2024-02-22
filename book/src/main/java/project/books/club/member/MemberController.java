package project.books.club.member;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.books.club.cmmn.SrchVO;

@RestController
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	 
	/**
	 * 맴버 목록 조회
	 * @param SrchVO
	 * @throw Exception
	 */
	@RequestMapping("/member/getMemberList.do")
	public SrchVO getMemberList(
			@RequestBody SrchVO vo
		) throws Exception {
		return memberService.selectMemberList(vo);
	}
	
	/**
	 * 맴버 저장
	 * @param MemberVO
	 * @throw Exception
	 */
	@RequestMapping("/member/saveMember.do")
	public MemberVO saveMember(
			@RequestBody MemberVO vo
		) throws Exception {
		vo = memberService.saveMember(vo);
		return vo;
	}
	
	/**
	 * 맴버 실시간 검색
	 * @param String
	 * @throw Exception
	 */
	@RequestMapping("/member/getMemberSrch.do")
	public SrchVO getMemberSrch(
			@RequestBody SrchVO vo
		) throws Exception {
		return memberService.selectMemberSrch(vo);
	}
}
 