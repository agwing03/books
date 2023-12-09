package project.books.club.member;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	 
	/**
	 * 맴버 목록 조회
	 * @param clubNo
	 */
	@RequestMapping("/member/getMemberList.do")
	public MemberVO getMemberList(
			@RequestBody MemberVO vo
		) throws Exception {
		return memberService.selectMemberList(vo);
	}
	
	
		
	/**
	 * 맴버 저장
	 * @param clubNo
	 */
	@RequestMapping("/member/saveMember.do")
	public void saveMember(
			@RequestBody MemberVO vo
		) throws Exception {
		memberService.saveMember(vo);
	}
}
 