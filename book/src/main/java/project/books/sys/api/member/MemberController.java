package project.books.sys.api.member;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	 
	/**
	 * 시스템 사용자 조회
	 * @param clubNo
	
	@RequestMapping("/user/getUserList.do")
	public MemberVO getMemberList(
			@RequestBody MemberVO vo
		) throws Exception {
		return userService.selectUserList(vo);
	}
	 */
}
 