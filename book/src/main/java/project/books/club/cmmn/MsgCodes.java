package project.books.club.cmmn;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import project.books.sys.util.CamelMap;

@Getter
@Setter
public class MsgCode {
	//시스템 에러 
	public static final String SYSTEM_ERROR = "시스템 오류가 발생하였습니다. 시스템관리자에게 문의해주시기 바랍니다. Exception : ";
	public static final String SYSTEM_PROCESS_FAILED = "요청이 처리되지 못했습니다. 다시 시도하시거나 시스템관리자에게 문의해주시기 바랍니다.";
	
	//클럽 
	public static final String CLUB_INSERT = "클럽이 신규 등록되었습니다.";
	public static final String CLUB_UPDATE = "클럽 정보가 수정되었습니다.";
	public static final String CLUB_DELETE = "클럽이 휴면처리 되었습니다.";
	public static final String CLUB_OUT_UPDATE = "클럽을 탈퇴하였습니다.";
	
	//모임
	public static final String MEETING_INSERT = "새로운 모임이 등록되었습니다.";
	public static final String MEETING_UPDATE = "모임 정보가 수정되었습니다.";
	public static final String MEETING_DELETE = "모임 일정이 삭제되었습니다.";
	public static final String MEETING_ATTEND_REQUEST = "모임에 참석 요청되었습니다.";
	public static final String MEETING_ATTEND_CANCEL = "모임에 참석이 취소되었습니다.";
	public static final String MEETING_ATTEND_CONFIRMED = "님이 모임에 참석이 확정되었습니다.";
	public static final String MEETING_ATTEND_REFUSAL = "님이 모임에 참석이 거부되었습니다.";
	public static final String MEETING_REVIEW_INSERT = "모임 후기가 등록되었습니다.";
	public static final String MEETING_REVIEW_UPDATE = "모임 후기가 수정되었습니다.";
	public static final String MEETING_REVIEW_DELETE = "모임 후기가 삭제되었습니다.";
	public static final String MEETING_REVIEW_MEMBER_INSERT = "참석 맴버를 추가하였습니다.";
	public static final String MEETING_REVIEW_MEMBER_DELETE = "참석 맴버를 삭제하였습니다.";
					
	//도서
	public static final String BOOK_INSERT = "새로운 도서가 등록되었습니다.";
	public static final String BOOK_UPDATE = "도서 정보가 수정되었습니다.";
	public static final String BOOK_DELETE = "가 삭제되었습니다.";
	
	//맴버
	public static final String MEMBER_INSERT = "새로운 맴버가 등록되었습니다.";
	public static final String MEMBER_UPDATE = "맴버 정보가 수정되었습니다.";
	public static final String MEMBER_ADMIN_OUT = "님이 탈퇴 처리되었습니다.";
	public static final String MEMBER_ADMIN_RESIGN = "님이 강퇴 처리되었습니다.";
	public static final String MEMBER_ADMIN_BLACKLIST = "블랙리스트에 등록되었습니다.";
	public static final String MEMBER_PROFILE_UPDATE = "사용자 프로필이 수정되었습니다.";
	
	//게세판 
	public static final String BOARD_INSERT = "게시물이 등록되었습니다.";
	public static final String BOARD_UPDATE = "게시물이 수정되었습니다.";				
	public static final String BOARD_DELETE = "게시물이 삭제되었습니다.";
	
	//댓글
	public static final String REPLY_INSERT = "댓글이 등록되었습니다.";
	public static final String REPLY_UPDATE = "댓글이 수정되었습니다.";				
	public static final String REPLY_DELETE = "댓글이 삭제되었습니다.";
}