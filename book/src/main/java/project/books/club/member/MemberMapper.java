package project.books.club.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.books.club.cmmn.SrchVO;
import project.books.sys.util.CamelMap;


@Mapper
public interface MemberMapper {
	
    /**
	 * 맴버 목록 조회
	 * @param SrchVO
	 * @return List<CamelMap>
     */
	List<CamelMap> selectMemberList(SrchVO vo);
	
    /**
	 * 맴버 저장
	 * @param MemberVO
	 * @return integer
     */
	int insertMember(MemberVO vo);
	
	/**
	 * 맴버 수정
	 * @param MemberVO
	 * @return integer
     */
	int updateMember(MemberVO vo);
	
    /**
	 * 클럽 맴버 저장
	 * @param MemberVO
	 * @return integer
     */
	int insertClubMember(MemberVO vo);
	
	/**
	 * 맴버 실시간 검색(공통모듈)
	 * @param SrchVO
	 * @return List<CamelMap>
     */
	List<CamelMap> selectMemberRealTimeSrch(SrchVO vo);
}
