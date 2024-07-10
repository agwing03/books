package project.books.club.club;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import project.books.club.cmmn.CommonVO;
import project.books.sys.util.CamelMap;

@Getter
@Setter
public class ClubVO extends SrchVO{
	private String flag = '' 	//I,U,D
    private int meetingNo = 0;	//모임번호
    private int hostNo = 0;		//맴버번호(club_member)
    private String title;		//모임명
    private String dday;		//모임일
    private String hour;		//모임시간-시
    private String minute;		//모임시간-분
    private String place;		//모임장소
    private String cost;		//모임예상비용
    private String meetingGbn;	//모임구분(A 벙개, D 지정도서, M 정기모임, S 이벤트)
    private String placeUrl;	//장소지도URL
    private String attendCnt;	//참석자수
    private String comment; 	//작성자 코멘트
    
    // 모임 후기 
	private List<CamelMap> reviewList;
}
