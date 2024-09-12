package project.books.club.admin;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.books.club.member.MemberMapper;
import project.books.club.member.MemberVO;

@Service
@RequiredArgsConstructor
public class AdminService {
	
	private final AdminMapper adminMapper;
	private final MemberMapper memberMapper;
	
	/**
	 * 관리자 맴버 저장
	 * @param SrchVO
	 * @throw Exception
	 */
	public void saveAdminMember(MemberVO vo) throws Exception{
		
		// 시스템 맴버 등록 
		//int memberNo = userMapper.insertUser(vo);
		
		// 클럽 맴버 등록 
		//vo.setMemberNo(memberNo);
		//memberMapper.insertMember(vo);
	}
}
