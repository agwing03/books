package project.books.club.book;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

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
	 * @param bookNo
	 */
	@RequestMapping("/book/setBook.do")
	public void setBook(
			@RequestBody BookVO vo
		) throws Exception {
		bookService.saveBook(vo);
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
}
