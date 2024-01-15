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
	 * @param BookVO
	 * @return list, integer
	 * @throw Exception
     */
	int selectBookListCnt(SrchVO vo);
	List<CamelMap> selectBookList(SrchVO vo);
	
	
	/**
	 * 도서 등록/수정/삭제
	 * @param HashMap<>
	 * @throw Exception
     */
	int insertBook(BookVO vo);
	int updateBook(CamelMap param);
	void deleteBook(HashMap<String, String> param);
	
    /**
	 * 도서 목록 조회
	 * @param BookVO
	 * @return list, integer
	 * @throw Exception
     */
	int selectSearchDataCnt(SrchVO vo);
	List<CamelMap> selectSearchData(SrchVO vo);
	
	
	/**
	 * 서평 생성
	 * @param CamelMap
	 * @throw Exception
     */
	void insertBookEval(CamelMap data);
	
	/**
	 * 도서 실시간 검색
	 * @param SrchVO
	 * @return List<CamelMap>
	 * @throw Exception
     */
	List<CamelMap> selectBookSrch(SrchVO vo);
	
}
