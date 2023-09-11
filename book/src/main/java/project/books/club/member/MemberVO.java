package project.books.club.member;

import lombok.Getter;
import lombok.Setter;
import project.books.sys.cmmn.CommonVO;

@Getter
@Setter
public class MemberVO extends CommonVO{
	
	private int memberNo;		//회원코드
	private String memberNm;	//회원이름
	private String nickNm;		//닉네임
    private int sex;			//성별
    private String area;		//지역
    private String phonNo;		//폰번호
    private String joinDt;		//가입일
    private String frstRegDt;	//생성일
    private int clubAuthorNo;	//모임직위
    
}
