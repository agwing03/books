package project.books.club.book;

import lombok.Getter;
import lombok.Setter;
import project.books.club.cmmn.CommonVO;

@Getter
@Setter
public class BookVO extends CommonVO{
	
	private int bookNo;			//도서번호
	private int memberNo;		//참석자번호
	private int meetingNo;		//모임번호
	
	//books
	private String bookTitle;	//도서명
	private String bookWriter;	//작가
	private String publicDt;	//발간일
	
	//books_eval
	private String bookScore;	//평점
	private String bookEval;	//서평
}
