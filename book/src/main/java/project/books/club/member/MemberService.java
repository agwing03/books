package project.books.club.member;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

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
		if("I".equals(vo.getSaveFlag())) {
			memberMapper.insertMember(vo);
		}else {
			memberMapper.insertMember(vo);
		}
	}
}
