package project.books.user;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import project.books.sys.cmmn.CommonVO;
import project.books.sys.util.CamelMap;

@Getter
@Setter
public class UserVO extends CommonVO{
	
	private int memberNo = 0;	//회원코드
	private String memberNm;	//회원이름
	private String nickNm;		//닉네임
	private String name;		//성별
    private String sex;			//성별
    private String area;		//지역
    private String intro;		//소개
    private String phonNo;		//폰번호
    private String joinDt;		//가입일
    private String frstRegDt;	//생성일
    private int clubAuthorNo;	//모임직위
    private String saveGbn;		//I(등록), U(수정)
    private String accountNo;	//계좌번호
    private String email;		//이메일
    private String brthday;		//생년월일
    
    private String title;
    private String meetingGbn;
    private List<CamelMap> member;
    
}
