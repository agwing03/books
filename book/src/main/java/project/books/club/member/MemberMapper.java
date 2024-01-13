package project.books.club.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.books.sys.cmmn.SrchVO;
import project.books.sys.util.CamelMap;


@Mapper
public interface MemberMapper {
	
    /**
	 * 맴버 목록 조회
	 * @param clubNo
	 * @return list, integer
	 * @throw Exception
     */
	int selectMemberListCnt(SrchVO vo);
	List<CamelMap> selectMemberList(SrchVO vo);
	
    /**
	 * 맴버 저장
	 * @param model
	 * @throw Exception
     */
	void insertMember(MemberVO vo);
	
	/**
	 * 맴버 수정
	 * @param model
	 * @throw Exception
     */
	void updateMember(MemberVO vo);
	
	/**
	 * 맴버 목록 조회
	 * @param SrchVO
	 * @return List<CamelMap>
	 * @throw Exception
     */
	List<CamelMap> selectMemberSrch(SrchVO vo);
}
