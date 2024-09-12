package project.books.sys.api.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.books.sys.config.security.ConnectMember;


@Mapper
public interface MemberMapper {
	
	/**
	 * 접속자 정보 조회
	 * @param String
	 * @return ConnectMember
	 * @throw Exception
	 * connetId => email, phonNum, memberId
	 */
	ConnectMember selectConnetMemberInfo(String connetId);
	
	/**
	 * 접속자 - 로그인 실패 횟수 & 계정 잠금 초기화  
	 * @param String
	 * @throw Exception
	 */
	void updateResetFailCnt(int connetNo);
}
