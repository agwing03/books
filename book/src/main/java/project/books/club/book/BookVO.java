package project.books.club.book;

import lombok.Getter;
import lombok.Setter;
import project.books.club.cmmn.CmmnVO;

@Getter
@Setter
public class BookVO extends CmmnVO{
	//books
	private boolean newBook;	//신규도서
	private String bookTitle;	//도서명
	private String bookWriter;	//작가
	private String publicDt;	//발간일
	private boolean progressYn;	//모임진행
	
	//books_eval
	private String bookScore;	//평점
	private String bookEval;	//서평
	
	//추가
	private String bookInfo;	//평점
	private String memberNm;	//맴버이름
}
