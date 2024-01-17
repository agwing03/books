package project.books.club.member;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.books.club.cmmn.SrchVO;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberMapper memberMapper;
	
	/**
	 * 맴버 목록 조회
	 * @param SrchVO
	 * @return List<CamelMap>
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
	 * @param MemberVO
	 * @throw Exception
	 */
	public MemberVO saveMember(MemberVO vo) throws Exception{
		int setCnt = 0;
		if(vo.getSaveFlag().equals("I")) {
			//member 등록
			setCnt += memberMapper.insertMember(vo);
			
			//club_member 등록
			setCnt += memberMapper.insertClubMember(vo);
		}else {
			setCnt += memberMapper.updateMember(vo);
		}
		vo.setSaveCnt(setCnt);
		return vo;
	}
	
	
	/**
	 * 맴버 실시간 검색
	 * @param String
	 * @throw Exception
	 */
	public SrchVO selectMemberSrch(SrchVO vo) throws Exception{
		vo.setDataList(memberMapper.selectMemberSrch(vo));
		return vo;
	}
}
