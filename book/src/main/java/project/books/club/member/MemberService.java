package project.books.club.member;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.books.sys.cmmn.SrchVO;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberMapper memberMapper;
	
	/**
	 * 맴버 목록 조회
	 * @param clubNo
	 * @return list
	 * @throw Exception
	 */
	public SrchVO selectMemberList(SrchVO vo) throws Exception{
		int cnt = memberMapper.selectMemberListCnt(vo);
		if(cnt > 0) {
			vo.setDataList(memberMapper.selectMemberList(vo));
		}
		return vo;
	}
	
	/**
	 * 맴버 저장
	 * @param model
	 * @return model
	 * @throw Exception
	 */
	public void saveMember(MemberVO vo) throws Exception{
		if(vo.getSaveFlag().equals("I")) {
			//member 등록
			memberMapper.insertMember(vo);
			
			//club_member 등록
			memberMapper.insertClubMember(vo);
		}else {
			memberMapper.updateMember(vo);
		}
		
	}
	
	
	/**
	 * 맴버 목록 조회
	 * @param SrchVO
	 * @return SrchVO
	 * @throw Exception
	 */
	public SrchVO selectMemberSrch(SrchVO vo) throws Exception{
		vo.setDataList(memberMapper.selectMemberSrch(vo));
		return vo;
	}
}
