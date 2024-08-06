package project.books.club.club;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.books.club.cmmn.SrchVO;
import project.books.sys.util.CamelMap;


@Mapper
public interface ClubMapper {
	
	/**
	 * 클럽 목록
	 * @param SrchVO
	 * @return CamelMap
	 * @throw Exception
	 */
	List<CamelMap> selectClubList(SrchVO vo);
	
	/**
	 * 클럽 상세
	 * @param SrchVO
	 * @return CamelMap
	 * @throw Exception
	 */
	CamelMap selectClubDtl(SrchVO vo);
	
	/**
	 * 클럽 등록
	 * @param ClubVO
	 * @return Integer
	 * @throw Exception
	 */
	int insertClub(ClubVO vo);
	
	/**
	 * 클럽 수정
	 * @param ClubVO
	 * @return Integer
	 * @throw Exception
	 */
	int updateClub(ClubVO vo);
	
	/**
	 * 클럽 삭제
	 * @param ClubVO
	 * @return Integer
	 * @throw Exception
	 */
	int deleteClub(ClubVO vo);
	
	/**
	 * 클럽 탈퇴
	 * @param ClubVO
	 * @return Integer
	 * @throw Exception
	 */
	int updateClubOut(ClubVO vo);
	
}
