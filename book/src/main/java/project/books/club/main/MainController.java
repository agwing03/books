package project.books.club.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private MainService mainService;
	
	/**
	 * 메인 페이지
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping("/")
	public String test() {
		System.out.println("test");
		return "/meetingList";
	}
	
	/**
	 * 메인 페이지
	 * 
	 * @param 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/main.do")
	public String main(
		) throws Exception {
		
		System.out.println("main.do");
		//vo = mainService.selectMeetingList(vo);
		
		return "index";
	}
	
	/**
	 * 메인 페이지
	 * 
	 * @param 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/getMeetingList.do")
	public String getMeetingList(
			@RequestBody MainVO vo,
			Model model
		) throws Exception {
		
		System.out.println("getMeetingList.do");
		vo = mainService.selectMeetingList(vo);
		
		return "/main";
	}
}
 