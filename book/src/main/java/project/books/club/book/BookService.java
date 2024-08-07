package project.books.club.book;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.books.club.club.ClubVO;
import project.books.club.cmmn.MsgCodes;
import project.books.club.cmmn.SrchVO;
import project.books.club.meeting.RequestBody;
import project.books.club.meeting.RequestMapping;
import project.books.club.meeting.ResponseEntity;
import project.books.sys.util.CamelMap;

@Service
@RequiredArgsConstructor
public class BookService {
	
	private final BookMapper bookMapper;
	
	/**	
	 * 도서 목록 조회
	 * @param SrchVO
	 * @return SrchVO
	 * @throw Exception
	 */
	public SrchVO selectBookList(SrchVO vo) throws Exception{
		vo.setDataList(bookMapper.selectBookList(vo));
		vo.setTotCnt(vo.getDataList().size());
		return vo;
	}
	
	/**
	 * 도서 등록
	 * @param BookVO
	 * @return BookVO
	 * @throw Exception
	 */
	public BookVO insertBook(BookVO vo) throws Exception{
		//처리 건수
		vo.setProcCnt(bookMapper.insertBook(vo));
		//메세지
		if(vo.getProcCnt() > 0) {
			vo.setMsg(MsgCodes.BOOK_INSERT);
		} else {
			vo.setMsg(MsgCodes.SYSTEM_PROCESS_FAILED);
		}
		return vo;
	}
	
	/**
	 * 도서 수정
	 * @param BookVO
	 * @return BookVO
	 * @throw Exception
	 */
	public BookVO updateBook(BookVO vo) throws Exception{
		//처리 건수
		vo.setProcCnt(bookMapper.updateBook(vo));
		//메세지
		if(vo.getProcCnt() > 0) {
			vo.setMsg(MsgCodes.BOOK_UPDATE);
		} else {
			vo.setMsg(MsgCodes.SYSTEM_PROCESS_FAILED);
		}
		return vo;
	}
	
	/**
	 * 도서 삭제
	 * @param BookVO
	 * @return BookVO
	 * @throw Exception
	 */
	public BookVO deleteBook(BookVO vo) throws Exception{
		//처리 건수
		vo.setProcCnt(bookMapper.deleteBook(vo));
		//메세지
		if(vo.getProcCnt() > 0) {
			vo.setMsg(MsgCodes.BOOK_DELETE);
		} else {
			vo.setMsg(MsgCodes.SYSTEM_PROCESS_FAILED);
		}
		return vo;
	}
	
	/**	
	 * 도서 실시간 검색(공통모듈)
	 * @param SrchVO
	 * @return SrchVO
	 * @throw Exception
	 */
	public SrchVO selectBookRealTimeSrch(SrchVO vo) throws Exception{
		vo.setDataList(bookMapper.selectBookRealTimeSrch(vo));
		vo.setTotCnt(vo.getDataList().size());
		return vo;
	}
}
