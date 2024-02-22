package project.books.club.member;

import lombok.Getter;
import lombok.Setter;
import project.books.club.cmmn.CommonVO;

@Getter
@Setter
public class MemberVO extends CommonVO{
	private String saveFlag;	//I,U
	//member 
    private int memberNo = 0;	//사용자번호
    private String memberId;	//사용자ID
    private String memberPwd;	//비밀번호
    private String authorId;	//권한ID
    private String nickNm;		//닉네임
    private String name;		//사용자이름
    private String sex;			//성별(공통코드)
    private String brthday;		//생년월일
    private String phonNo;		//핸드폰번호
    private String email;		//이메일
    private String addr;		//주소
    private String addrDtl;		//상세주소
    private int profileImgNo;	//프로필이미지(file_mstr)
    private String intro;		//자기소개
    private String accountNo;	//계좌번호
    private String actionAreaLv1;	//활동지역1(공통코드)
    private String actionAreaLv2;	//활동지역2(공통코드)
    private String joinClubOpenYn;	//가입클럽 공개여부
    private String leaveYn;			//탈퇴여부
    private int loginFailCnt;		//로그인 실패 카운트
    private String lockTime;		//잠김 일시
    private String unLockTime;		//잠김 해제 일시
    private String lockYn;			//계정 잠김 여부
    
    //club_member
    private String clubNickNm;	//클럽닉네임
    private String joinDt;		//모임가입일
    private String leaveDt;		//모임탈퇴일
    private String clubLeaveYn;	//모임탈퇴여부
    private String memo;		//메모
    private String rejoinYn;	//재가입여부
    private String blackYn;		//블랙리스트여부
    
}

