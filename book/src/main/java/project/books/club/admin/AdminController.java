package project.books.club.admin;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.books.club.member.MemberService;
import project.books.club.member.MemberVO;
import project.books.sys.cmmn.DataVO;

@RestController
@RequiredArgsConstructor
public class AdminController {
	
	private final AdminService adminService;
	
	/**
	 * 관리자 맴버 등록
	 * @param MemberVO
	@RequestMapping("/admin/saveAdminMember.do")
	public void saveAdminMember(
			@RequestBody MemberVO vo
		) throws Exception {
		adminService.saveAdminMember(vo);
	}
	 */
}
