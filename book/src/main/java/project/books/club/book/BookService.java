package project.books.club.book;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
	
	private final BookMapper bookMapper;
	
	/**
	 * 도서 목록 조회
	 * @param clubNo
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
}
