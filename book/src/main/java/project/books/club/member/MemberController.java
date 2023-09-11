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
	 * 모임 목록 조회
	 * @param clubNo
	 */
	@RequestMapping("/member/getMemberList.do")
	public MemberVO getMemberList(
			@RequestBody MemberVO vo
		) throws Exception {
		return memberService.selectMemberList(vo);
	}
}
 