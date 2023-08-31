package project.books.club.main;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MainController {
	
	private MainService mainService;
	
	/**
	 * 메인 페이지
	 * 
	 * @param 
	 * @return
	 */
	@PostMapping("/main.do")
	public String main() {
		System.out.println("main.do");
		
		return "/main";
	}
	
	/**
	 * 메인 페이지
	 * 
	 * @param 
	 * @return
	 * @throws Exception 
	 */
	@PostMapping("/main.do")
	public String getMeetingList(
			@RequestBody MainVO vo
		) throws Exception {
		
		System.out.println("main.do");
		vo = mainService.selectMeetingList(vo);
		
		return "/main";
	}
}
