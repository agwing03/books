package project.books.club.book;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.books.sys.cmmn.DataVO;
import project.books.sys.util.CamelMap;

@RestController
@RequiredArgsConstructor
public class BookController {
	
	private final BookService bookService;
	 
	/**
	 * 도서 목록 조회
	 * @param clubNo
	 */
	@RequestMapping("/book/getBookList.do")
	public BookVO getBookList(
			@RequestBody BookVO vo
		) throws Exception {
		return bookService.selectBookList(vo);
	}
	
	/**
	 * 도서 등록
	 * @param BookVO
	 */
	@RequestMapping("/book/saveBook.do")
	public void saveBook(
			@RequestBody CamelMap param
		) throws Exception {
		bookService.saveBook(param);
	}
	
	/**
	 * 도서 목록 조회
	 * @param clubNo
	 */
	@RequestMapping("/search.do")
	public BookVO getSearch(
			@RequestBody BookVO vo
		) throws Exception {
		return bookService.selectSearchData(vo);
	}
	
	/**
	 * 도서 실시간 검색
	 * @param srchText
	 */
	@RequestMapping("/book/selectBookSrch.do")
	public DataVO selectBookSrch(
			@RequestBody DataVO vo
		) throws Exception {
		return bookService.selectBookSrch(vo);
	}
}
