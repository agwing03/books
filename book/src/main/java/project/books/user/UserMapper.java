package project.books.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.books.club.member.MemberVO;
import project.books.sys.cmmn.SrchVO;
import project.books.sys.util.CamelMap;


@Mapper
public interface UserMapper {
	
    /**
	 * 시스템 사용자 조회
	 * @param clubNo
	 * @return list, integer
	 * @throw Exception
     */
	int selectUserListCnt(UserVO vo);
	List<CamelMap> selectUserList(UserVO vo);
	
    /**
	 * 시스템 사용자 저장
	 * @param model
	 * @throw Exception
     */
	int insertUser(MemberVO vo);
}
