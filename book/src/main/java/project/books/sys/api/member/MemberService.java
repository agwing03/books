package project.books.sys.api.member;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.books.sys.config.security.ConnectMember;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberMapper memberMapper;
	
	/**
	 * 접속자 정보 조회
	 * @param String
	 * @return List<ConnectMember>
	 * @throw Exception
	 */
	public List<ConnectMember> selectConnetMemberInfo(String connetId) throws Exception{
		return memberMapper.selectConnetMemberInfo(connetId);
	}
	
	/**
	 * 접속자 - 로그인 실패 횟수 & 계정 잠금 초기화  
	 * @param String
	 * @throw Exception
	 */
	public void updateResetFailCnt(int connetNo) throws Exception{
		memberMapper.updateResetFailCnt(connetNo);
	}
}
