package project.books.club.admin;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

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
