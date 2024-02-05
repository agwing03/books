package project.books.club.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import project.books.club.cmmn.SrchVO;

@Controller
@RequiredArgsConstructor
public class ViewController {
	
	/**
	 * HOME / 초기 페이지 진입
	 */ 
	@RequestMapping("/")
	public String welcome() {return "/home";}
	
	/**
	 * HOME
	 */ 
	@RequestMapping("/home")
	public String home() {return "/main";}
		
	/**
	 * 메인 
	 */
	@RequestMapping("/main.do")
	public String main() {return "/main";}
	
	/**
	 * 관리자
	 */
	@RequestMapping("/admin/club.do")
	public String club() {return "/club/club";}
	
	/**
	 * 목임 
	 */
	@RequestMapping("/meeting.do")
	public String meeting() {return "/meeting/meeting";}
	
	@RequestMapping("/meetingReview.do")
	public String meetingReview() {return "/meeting/meetingReview";}
	
	/**
	 * 맴버 
	 */
	@RequestMapping("/member.do")
	public String mamber() {return "/club/member";}
	
	/**
	 * 도서 
	 */
	@RequestMapping("/book.do")
	public String book() {return "/club/book";}
	
	/**
	 * 커뮤니티
	 */
	@RequestMapping("/admin.do")
	public String admin() {return "/club/admin";}
	
	/**
	 * test 샘플
	 */
	@RequestMapping("/sample.do")
	public String sample() {return "/sample/sample";}
}
 