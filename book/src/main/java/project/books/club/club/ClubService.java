package project.books.club.club;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClubService {
	private static final Logger log = (Logger) LoggerFactory.getLogger(MeetingService.class);
	
	private final ClubMapper clubMapper;
	private final LogService sysLog;
	
	/**
	 * 클럽 목록
	 * @param SrchVO
	 * @return SrchVO
	 * @throw Exception
	 */
	public SrchVO selectClubList(SrchVO vo) throws Exception{
		vo.setDataList(clubMapper.selectClubList(vo));
		vo.setTotCnt(vo.getDataList().size())
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
		try {
			//처리 건수
			vo.setProcCnt(clubMapper.insertClub(vo));
			//메세지
			if(vo.getProcCnt() > 0) {
				vo.setMsg(MsgCodes.CLUB_INSERT_MSG);
			} else {
				vo.setMsg(MsgCodes.SYSTEM_PROCESS_FAILED);
			}
		} catch (Exception e) {
			log.info("::: ClubService > updateClub > Exception : {}", e);
			vo.setMsg(MsgCodes.SYSTEM_ERROR_MSG + e.toString());
			//시스템 로그 등록
			sysLog.systemErrorLog
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
		try {
			//수행 건수
			vo.setProcCnt(clubMapper.updateClub(vo));
			//메세지
			if(vo.getProcCnt() > 0) {
				vo.setMsg(MsgCodes.CLUB_UPDATE_MSG);
			} else {
				vo.setMsg(MsgCodes.SYSTEM_PROCESS_FAILED);
			}
		} catch (Exception e) {
			log.info("::: ClubService > updateClub > Exception : {}", e);
			vo.setMsg(MsgCodes.SYSTEM_ERROR_MSG + e.toString());
		}
		return vo;
	}
	
	/**
	 * 클럽 삭제
	 * @param ClubVO
	 * @return SrchVO
	 * @throw Exception
	 */
	public SrchVO deleteClub(SrchVO vo) throws Exception{
		try {
			//수행 건수
			vo.setProcCnt(clubMapper.deleteClub(vo));
			if(vo.getProcCnt() > 0) {
				vo.setMsg(MsgCodes.CLUB_DELETE_MSG);
			} else {
				vo.setMsg(MsgCodes.SYSTEM_PROCESS_FAILED);
			}
		} catch (Exception e) {
			log.info("::: ClubService > deleteClub > Exception : {}", e);
			vo.setMsg(MsgCodes.SYSTEM_ERROR_MSG + e.toString());
		}
		return vo;
	}
	
	/**
	 * 클럽 탈퇴
	 * @param SrchVO
	 * @return SrchVO
	 * @throw Exception
	 */
	public SrchVO updateClubOut(SrchVO vo) throws Exception{
		try {
			//탈퇴 처리
			vo.setProcCnt(clubMapper.updateClubOut(vo));
			if(vo.getProcCnt() > 0) {
				//맴버 이력 LOG 
				
				vo.setMsg(MsgCodes.CLUB_DELETE_MSG);
			} else {
				vo.setMsg(MsgCodes.SYSTEM_PROCESS_FAILED);
			}
		} catch (Exception e) {
			log.info("::: ClubService > deleteClub > Exception : {}", e);
			vo.setMsg(MsgCodes.SYSTEM_ERROR_MSG + e.toString());
		}
		return vo;
	}
}

