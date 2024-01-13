package project.books.club.member;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.books.sys.cmmn.DataVO;

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
	public MemberVO selectMemberList(MemberVO vo) throws Exception{
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
		memberMapper.insertMember(vo);
	}
	
	
	/**
	 * 맴버 목록 조회
	 * @param DataVO
	 * @return SrchVO
	 * @throw Exception
	 */
	public DataVO selectMemberSrch(DataVO vo) throws Exception{
		vo.setDataList(memberMapper.selectMemberSrch(vo));
		return vo;
	}
}
