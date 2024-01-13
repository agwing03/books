package project.books.user;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	 
	/**
	 * 시스템 사용자 조회
	 * @param clubNo
	 */
	@RequestMapping("/user/getUserList.do")
	public UserVO getMemberList(
			@RequestBody UserVO vo
		) throws Exception {
		return userService.selectUserList(vo);
	}
}
 