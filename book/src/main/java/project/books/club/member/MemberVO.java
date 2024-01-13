package project.books.club.member;

import lombok.Getter;
import lombok.Setter;
import project.books.sys.cmmn.CommonVO;

@Getter
@Setter
public class MemberVO extends CommonVO{
	
	private int memberNo = 0;	//회원코드
	private String nickNm;		//닉네임
	private String name;		//회원이름
    private String sex;			//성별
    private String area;		//지역
    private String intro;		//소개
    private String phonNo;		//폰번호
    private String joinDt;		//가입일
    private String frstRegDt;	//생성일
    private int clubAuthorNo;	//모임직위
    
    private String accountNo;	//계좌번호
    private String email;		//이메일
    private String brthday;		//생년월일
    private String actionAreaLv1; //지역
    private String actionAreaLv2; //상세지역
}

