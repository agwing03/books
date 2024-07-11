package project.books.club.club;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ClubMapper {
	
	/**
	 * 클럽 목록
	 * @param SrchVO
	 * @return CamelMap
	 * @throw Exception
	 */
	CamelMap selectClubList(SrchVO vo);
	
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
	int deleteClub(SrchVO vo);
	
	/**
	 * 클럽 탈퇴
	 * @param SrchVO
	 * @return Integer
	 * @throw Exception
	 */
	int updateClubOut(SrchVO vo);
	
}
