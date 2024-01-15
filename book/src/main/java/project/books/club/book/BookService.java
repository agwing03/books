package project.books.club.book;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.books.sys.cmmn.SrchVO;

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
	public SrchVO selectBookList(SrchVO vo) throws Exception{
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
	public int saveBook(BookVO vo) throws Exception{
		//작성자
		vo.setRegNo(2); //차후 수정
		int cnt = bookMapper.insertBook(vo);
		System.out.println(vo.getBookNo());
		System.out.println(cnt);
		return cnt;
	}
	
	/**
	 * 도서 목록 조회
	 * @param BookVO
	 * @return list
	 * @throw Exception
	 */
	public SrchVO selectSearchData(SrchVO vo) throws Exception{
		vo.setDataList(bookMapper.selectSearchData(vo));
		vo.setTotCnt(vo.getDataList().size());
		return vo;
	}
	
	/**
	 * 도서 실시간 검색
	 * @param SrchVO
	 * @return SrchVO
	 * @throw Exception
	 */
	public SrchVO selectBookSrch(SrchVO vo) throws Exception{
		vo.setDataList(bookMapper.selectBookSrch(vo));
		return vo;
	}
}
