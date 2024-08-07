package project.books.club.member;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.books.club.club.ClubVO;
import project.books.club.cmmn.MsgCodes;
import project.books.club.cmmn.SrchVO;
import project.books.club.meeting.MeetingVO;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberMapper memberMapper;
	
	/**
	 * 맴버 목록 조회
	 * @param SrchVO
	 * @return SrchVO
	 * @throw Exception
	 */
	public SrchVO selectMemberList(SrchVO vo) throws Exception{
		vo.setDataList(memberMapper.selectMemberList(vo));
		vo.setTotCnt(vo.getDataList().size());
		return vo;
	}
	
	/**
	 * 맴버 상세 조회
	 * @param SrchVO
	 * @return SrchVO
	 * @throw Exception
	 */
	public SrchVO selectMemberDtl(SrchVO vo) throws Exception{
		vo.setDataMap(memberMapper.selectMemberDtl(vo));
		return vo;
	}
	
	/**
	 * 맴버 저장
	 * @param MemberVO
	 * @return MemberVO
	 * @throw Exception
	 */
	public MemberVO insertMember(MemberVO vo) throws Exception{
		int setCnt = 0;
		if(vo.getSaveFlag().equals("I")) {
			//member 등록
			setCnt += memberMapper.insertMember(vo);
			
			//club_member 등록
			setCnt += memberMapper.insertClubMember(vo);
		}else {
			setCnt += memberMapper.updateMember(vo);
		}
		vo.setProcCnt(setCnt);
		return vo;
	}
	
	/**
	 * 맴버 등록
	 * @param MemberVO
	 * @return MemberVO
	 * @throw Exception
	 */
	public MemberVO insertMember(ClubVO vo) throws Exception{
		int mmICnt = 0, cmICnt = 0;
		//처리 건수
		vo.setProcCnt(memberMapper.insertMember(vo));
		
		//member 등록
		mmICnt += memberMapper.insertMember(vo);
		
		//club_member 등록
		cmICnt += memberMapper.insertClubMember(vo);
		
		log.debug("##### 맴버 등록 insertMember / 시스템맴버등록:"+mmICnt+"건 / 클럽맴버등록:"+cmICnt+"건 #####");
		
		//메세지
		if(vo.getProcCnt() > 0) {
			vo.setMsg(MsgCodes.MEMBER_INSERT);
		} else {
			vo.setMsg(MsgCodes.SYSTEM_PROCESS_FAILED);
		}
		return vo;
	}
	
	/**
	 * 맴버 실시간 검색(공통모듈)
	 * @param SrchVO
	 * @return SrchVO
	 * @throw Exception
	 */
	public SrchVO selectMemberRealTimeSrch(SrchVO vo) throws Exception{
		vo.setDataList(memberMapper.selectMemberRealTimeSrch(vo));
		vo.setTotCnt(vo.getDataList().size());
		return vo;
	}
}
