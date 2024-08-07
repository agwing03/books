package project.books.club.meeting;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import project.books.club.book.BookVO;
import project.books.club.cmmn.CmmnVO;
import project.books.sys.util.CamelMap;

@Getter
@Setter
public class MeetingVO extends CmmnVO{
	//meeting
    private int hostNo = 0;		//맴버번호(club_member)
    private String hostNm;		//맴버이름
    private String title;		//모임명
    private String dday;		//모임일
    private String time;		//시간
    private String hour;		//모임시간-시
    private String minute;		//모임시간-분
    private String place;		//모임장소
    private String cost;		//모임예상비용
    private String meetingGbn;	//모임구분(A 벙개, D 지정도서, M 정기모임, S 이벤트)
    private String placeUrl;	//장소지도URL
    private String attendCnt;	//참석자수
    private String comment; 	//작성자 코멘트
    private String state;		//활성화상태
    private String account;		//계좌번호
    private String bankCd;		//은행코드
    private boolean reviewYn;	//후기작성여부
    
    //meeting_member
    private int attendNo;		//모임 참석 번호
    private String attendYn;	//참석여부
    private String afterPartyYn;//뒤풀이참석여부
    private String divAmount;	//분할금액
    private String calculYn;	//정산(결재) 여부
    private List<Integer> memberNoArr; //참석자 번호(in)
    private List<CamelMap> memberList; //참석자 목록(out)
    
    
    // 모임 후기 한줄평 
	private List<BookVO> reviewList;
}
