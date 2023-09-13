package project.books.club.book;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.books.sys.util.CamelMap;


@Mapper
public interface BookMapper {
	
    /**
	 * 도서 목록 조회
	 * @param BookVO
	 * @return list, integer
	 * @throw Exception
     */
	int selectBookListCnt(BookVO vo);
	List<CamelMap> selectBookList(BookVO vo);
	
	
	/**
	 * 도서 등록/수정/삭제
	 * @param HashMap<>
	 * @throw Exception
     */
	void insertBook(HashMap<String, String> param);
	void updateBook(HashMap<String, String> param);
	void deleteBook(HashMap<String, String> param);
}
