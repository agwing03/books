package project.books.club.book;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

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
	public void saveBook(BookVO vo) throws Exception{
		HashMap<String, String> param = new HashMap<>();
		//작성자
		String userNo = "2"; //차후 수정
		param.put("userNo", userNo);
		//view 데이터 셋
		String saveGbn = vo.getSaveGbn();
		String data = vo.getDataString();
		//TR 스플릿 '/'
		String[] dataArr = data.split("/");
		String[] subDataArr = null;
		for (int i = 0; i < dataArr.length; i++) {
			data = dataArr[i];
			//TD 스플릿 '/'
			subDataArr = data.split(",");
			for (int j = 0; j < subDataArr.length; j++) {
				param.put("data"+j, subDataArr[j]);	
			}
			if(saveGbn.equals("I")) {
				bookMapper.insertBook(param);
			}else if(saveGbn.equals("U")) {
				bookMapper.updateBook(param);
			}else if(saveGbn.equals("D")) {
				bookMapper.deleteBook(param);
			}
		}
	}
}
