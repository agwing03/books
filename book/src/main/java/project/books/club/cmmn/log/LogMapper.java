package project.books.club.cmmn.log;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface LogMapper {
	
    /**
	 * 맴버 조인, 탈퇴 이력
	 * @param LogVO
	 * @throw Exception
     */
	void insertClubMemberHist(LogVO vo);
	
    /**
	 * 시스템 에러 이력
	 * @param LogVO
	 * @throw Exception
     */
	void insertSystemErrorLog(LogVO vo);
	
    /**
	 * 시스템 로그인/아웃 이력
	 * @param LogVO
	 * @throw Exception
     */
	void insertSystemLoginLog(LogVO vo);
	
}