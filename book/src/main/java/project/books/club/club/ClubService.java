package project.books.club.club;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.books.club.cmmn.MsgCodes;
import project.books.club.cmmn.SrchVO;

@Service
@RequiredArgsConstructor
public class ClubService {
	//private static final Logger log = (Logger) LoggerFactory.getLogger(MeetingService.class);
	
	private final ClubMapper clubMapper;
	
	/**	
	 * 클럽 목록
	 * @param SrchVO
	 * @return SrchVO
	 * @throw Exception
	 */
	public SrchVO selectClubList(SrchVO vo) throws Exception{
		vo.setDataList(clubMapper.selectClubList(vo));
		vo.setTotCnt(vo.getDataList().size());
		return vo;
	}
	
	/**
	 * 클럽 상세
	 * @param SrchVO
	 * @return SrchVO
	 * @throw Exception
	 */
	public SrchVO selectClubDtl(SrchVO vo) throws Exception{
		vo.setDataMap(clubMapper.selectClubDtl(vo));
		return vo;
	}
	
	/**
	 * 클럽 등록
	 * @param ClubVO
	 * @return ClubVO
	 * @throw Exception
	 */
	public ClubVO insertClub(ClubVO vo) throws Exception{
		//처리 건수
		vo.setProcCnt(clubMapper.insertClub(vo));
		//메세지
		if(vo.getProcCnt() > 0) {
			vo.setMsg(MsgCodes.CLUB_INSERT);
		} else {
			vo.setMsg(MsgCodes.SYSTEM_PROCESS_FAILED);
		}
		return vo;
	}
	
	/**
	 * 클럽 수정
	 * @param ClubVO
	 * @return ClubVO
	 * @throw Exception
	 */
	public ClubVO updateClub(ClubVO vo) throws Exception{
		//수행 건수
		vo.setProcCnt(clubMapper.updateClub(vo));
		//메세지
		if(vo.getProcCnt() > 0) {
			vo.setMsg(MsgCodes.CLUB_UPDATE);
		} else {
			vo.setMsg(MsgCodes.SYSTEM_PROCESS_FAILED);
		}
		return vo;
	}
	
	/**
	 * 클럽 삭제
	 * @param ClubVO
	 * @return ClubVO
	 * @throw Exception
	 */
	public ClubVO deleteClub(ClubVO vo) throws Exception{
		//수행 건수
		vo.setProcCnt(clubMapper.deleteClub(vo));
		if(vo.getProcCnt() > 0) {
			vo.setMsg(MsgCodes.CLUB_DELETE);
		} else {
			vo.setMsg(MsgCodes.SYSTEM_PROCESS_FAILED);
		}
		return vo;
	}
	
	/**
	 * 클럽 탈퇴
	 * @param ClubVO
	 * @return ClubVO
	 * @throw Exception
	 */
	public ClubVO updateClubOut(ClubVO vo) throws Exception{
		//탈퇴 처리	
		vo.setProcCnt(clubMapper.updateClubOut(vo));
		if(vo.getProcCnt() > 0) {
			//맴버 이력 LOG 
			vo.setMsg(MsgCodes.CLUB_OUT_UPDATE);
		} else {
			vo.setMsg(MsgCodes.SYSTEM_PROCESS_FAILED);
		}
		return vo;
	}
}

