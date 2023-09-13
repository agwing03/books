package project.books.club.book;

import lombok.Getter;
import lombok.Setter;
import project.books.sys.cmmn.CommonVO;

@Getter
@Setter
public class BookVO extends CommonVO{
	
	private int bookNo;			//도서번호
	private String bookTitle;	//도서명
	private String bookWriter;	//작가
}
