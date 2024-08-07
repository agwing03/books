package project.books.club.book;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.books.club.cmmn.SrchVO;
import project.books.sys.util.CamelMap;

@RestController
@RequiredArgsConstructor
public class BookController {
	
	private final BookService bookService;
	
	/**
	 * 도서 목록 조회
	 * @param SrchVO
	 * @return ResponseEntity
	 */
	@RequestMapping("/selectBookList.do")
	public ResponseEntity<List<CamelMap>> selectBookList(
			@RequestBody SrchVO vo
		) throws Exception {
		vo = bookService.selectBookList(vo);
		return ResponseEntity.ok(vo.getDataList());
	}
	
	/**
	 * 도서 상세 조회
	 * @param SrchVO
	 * @return ResponseEntity
	 */
	
	/**
	 * 도서 등록
	 * @param BookVO
	 * @return ResponseEntity
	 */
	@RequestMapping("/insertBook.do")
	public ResponseEntity<?> insertBook(
			@RequestBody BookVO vo
		) throws Exception {
		vo = bookService.insertBook(vo);
		return new ResponseEntity<>(vo.getMsg(), HttpStatus.OK);
	}
	
	/**
	 * 도서 수정
	 * @param BookVO
	 * @return ResponseEntity
	 */
	@RequestMapping("/updateBook.do")
	public ResponseEntity<?> updateBook(
			@RequestBody BookVO vo
		) throws Exception {
		vo = bookService.updateBook(vo);
		return new ResponseEntity<>(vo.getMsg(), HttpStatus.OK);
	}
	
	/**
	 * 도서 삭제
	 * @param BookVO
	 * @return ResponseEntity
	 */
	@RequestMapping("/deleteBook.do")
	public ResponseEntity<?> deleteBook(
			@RequestBody BookVO vo
		) throws Exception {
		vo = bookService.deleteBook(vo);
		return new ResponseEntity<>(vo.getMsg(), HttpStatus.OK);
	}
	
	/**
	 * 도서 실시간 검색(공통모듈)
	 * @param SrchVO
	 * @return ResponseEntity
	 */
	@RequestMapping("/selectBookRealTimeSrch.do")
	public ResponseEntity<List<CamelMap>> selectBookRealTimeSrch(
			@RequestBody SrchVO vo
		) throws Exception {
		vo = bookService.selectBookRealTimeSrch(vo);
		return ResponseEntity.ok(vo.getDataList());
	}
}
