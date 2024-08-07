package project.books.club.book;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.books.club.cmmn.SrchVO;
import project.books.sys.util.CamelMap;


@Mapper
public interface BookMapper {
	
    /**
	 * 도서 목록 조회
	 * @param SrchVO
	 * @return List<CamelMap>
     */
	List<CamelMap> selectBookList(SrchVO vo);
	
    /**
	 * 도서 등록
	 * @param MemberVO
	 * @return integer
     */
	int insertBook(BookVO vo);
	
    /**
	 * 도서 수정
	 * @param MemberVO
	 * @return integer
     */
	int updateBook(BookVO vo);
	
    /**
	 * 도서 삭제
	 * @param MemberVO
	 * @return integer
     */
	void deleteBook(BookVO vo);
	
	/**
	 * 도서 서평 등록
	 * @param BookVO
	 * @return integer
     */
	int insertBookEval(BookVO vo);
	
	/**
	 * 도서 서평 삭제
	 * @param BookVO
	 * @return integer
	 * @throw Exception
     */
	int deleteBookEval(BookVO vo);
	
	/**
	 * 도서 실시간 검색(공통모듈)
	 * @param SrchVO
	 * @return List<CamelMap>
     */
	List<CamelMap> selectBookRealTimeSrch(SrchVO vo);

}
