package project.books.club.member;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;
import lombok.RequiredArgsConstructor;
import project.books.club.cmmn.MsgCodes;
import project.books.club.cmmn.SrchVO;

@Service
@RequiredArgsConstructor
public class MemberService {
	private static final Logger log = (Logger) LoggerFactory.getLogger(MemberService.class);
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
	 * 맴버 등록
	 * @param MemberVO
	 * @return MemberVO
	 * @throw Exception
	 */
	public MemberVO insertMember(MemberVO vo) throws Exception{
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
