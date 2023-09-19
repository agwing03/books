package project.books.club.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ViewController {
	
	/**
	 * 초기 페이지 진입
	 */ 
	@RequestMapping("/")
	public String welcome() {return "/books";}
	
	
	/**
	 * 메인 
	 */
	@RequestMapping("/main.do")
	public String main() {return "/main";}
	
	/**
	 * 클럽
	 */
	@RequestMapping("/club.do")
	public String club() {return "/club/club";}
	
	/**
	 * 목임 
	 */
	@RequestMapping("/meeting.do")
	public String meeting() {return "/club/meeting";}
	
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
	 * 맴버 
	 */
	@RequestMapping("/admin.do")
	public String admin() {return "/club/admin";}
	
	/**
	 * test 샘플
	 */
	@RequestMapping("/sample.do")
	public String sample() {return "/sample/sample";}
}
 