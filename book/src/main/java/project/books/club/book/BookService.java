package project.books.club.book;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.books.sys.cmmn.DataVO;
import project.books.sys.util.CamelMap;

@Service
@RequiredArgsConstructor
public class BookService {
	
	private final BookMapper bookMapper;
	
	/**
	 * 도서 목록 조회
	 * @param BookVO
	 * @return list
	 * @throw Exception
	 */
	public BookVO selectBookList(BookVO vo) throws Exception{
		int cnt = bookMapper.selectBookListCnt(vo);
		if(cnt > 0) {
			vo.setDataList(bookMapper.selectBookList(vo));
		}
		return vo;
	}
	
	/**
	 * 도서 등록/수정/삭제
	 * @param BookVO
	 * @throw Exception
	 */
	public void saveBook(CamelMap param) throws Exception{
		//작성자
		param.put("regNo", "2"); //차후 수정
		bookMapper.insertBook(param);
		System.out.println(param);
	}
	
	/**
	 * 도서 목록 조회
	 * @param BookVO
	 * @return list
	 * @throw Exception
	 */
	public BookVO selectSearchData(BookVO vo) throws Exception{
		vo.setDataList(bookMapper.selectSearchData(vo));
		vo.setTotCnt(vo.getDataList().size());
		return vo;
	}
	
	/**
	 * 도서 실시간 검색
	 * @param DataVO
	 * @return SrchVO
	 * @throw Exception
	 */
	public DataVO selectBookSrch(DataVO vo) throws Exception{
		vo.setDataList(bookMapper.selectBookSrch(vo));
		return vo;
	}
}
