package project.books.club.book;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.books.club.cmmn.SrchVO;

@RestController
@RequiredArgsConstructor
public class BookController {
	
	private final BookService bookService;
	 
	/**
	 * 도서 목록 조회
	 * @param clubNo
	 */
	@RequestMapping("/book/getBookList.do")
	public SrchVO getBookList(
			@RequestBody SrchVO vo
		) throws Exception {
		return bookService.selectBookList(vo);
	}
	
	/**
	 * 도서 등록
	 * @param BookVO
	 */
	@RequestMapping("/book/saveBook.do")
	public int saveBook(
			@RequestBody BookVO vo
		) throws Exception {
		int cnt = bookService.saveBook(vo);
		return cnt;
	}
	
	/**
	 * 도서 목록 조회
	 * @param clubNo
	 */
	@RequestMapping("/search.do")
	public SrchVO getSearch(
			@RequestBody SrchVO vo
		) throws Exception {
		return bookService.selectSearchData(vo);
	}
	
	/**
	 * 도서 실시간 검색
	 * @param srchText
	 */
	@RequestMapping("/book/selectBookSrch.do")
	public SrchVO selectBookSrch(
			@RequestBody SrchVO vo
		) throws Exception {
		return bookService.selectBookSrch(vo);
	}
}
