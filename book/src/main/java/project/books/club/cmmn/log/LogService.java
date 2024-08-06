package project.books.club.cmmn.log;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogService {
	
	private final LogMapper logMapper;
	
	/**
	 * 맴버 조인, 탈퇴 이력
	 * @param clubNo : 클럽번호
	 * @param memberNo : 맴버번호
	 * @param targetGbn : 대상구분(SYSTEM, CLUB)
	 * @param joinGbn : 처리구분(IN, OUT, RESIGN)
	 * @param connUser : 접속유져
	 * @throw Exception
	 */
	public void memberHistLog(
			int memberNo, 
			String targetGbn, 
			String joinGbn, 
			int connUser,
			int clubNo
		) throws Exception{
		
		//데이터셋
		LogVO vo = new LogVO();
		vo.setMemberNo(memberNo);
		vo.setTargetGbn(targetGbn);
		vo.setJoinGbn(joinGbn);
		vo.setConnUser(connUser);
		if(clubNo > 0 && targetGbn.equals("CLUB")) {
			vo.setClubNo(clubNo);
		}
		
		//로그 저장
		logMapper.insertClubMemberHist(vo);
	}
	
	/**
	 * 시스템 에러 이력
	 * @param methodNm : 메소드명
	 * @param paramData : 파라미터
	 * @param errMsg : 에러메세지
	 * @param connUser : 접속유져
	 * @throw Exception
	 */
	public void systemErrorLog(
			String methodNm, 
			String paramData,
			String errMsg,
			int connUser
		) throws Exception {
		
		//데이터셋
		LogVO vo = new LogVO();
		vo.setMethodNm(methodNm);
		vo.setParamData(paramData);
		vo.setErrMsg(errMsg);
		vo.setConnUser(connUser);
		
		//로그 저장
		logMapper.insertSystemErrorLog(vo);
	}
	
	/**
	 * 시스템 로그인/아웃 이력
	 * @param loginGbn : 로그인 성곧/실패 구분(IN, OUT)
	 * @param loginIp : 접속IP
	 * @param rtnMsg : 반환메세지
	 * @param failCnt : 실패카운트
	 * @param connUser : 접속유져
	 * @throw Exception
	 */
	public void systemLoginLog(
			String loginGbn, 
			String loginIp,
			String rtnMsg,
			int failCnt,
			int connUser
		) throws Exception {
		
		//데이터셋
		LogVO vo = new LogVO();
		vo.setLoginGbn(loginGbn);
		vo.setLoginIp(loginIp);
		vo.setRtnMsg(rtnMsg);
		vo.setFailCnt(failCnt);
		vo.setConnUser(connUser);
		
		//로그 저장
		logMapper.insertSystemLoginLog(vo);
	}
}
