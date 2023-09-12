package project.books.club.book;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.books.sys.util.CamelMap;


@Mapper
public interface BookMapper {
	
    /**
	 * 도서 목록 조회
	 * @param clubNo
	 * @return list, integer
	 * @throw Exception
     */
	int selectBookListCnt(BookVO vo);
	List<CamelMap> selectBookList(BookVO vo);
}
