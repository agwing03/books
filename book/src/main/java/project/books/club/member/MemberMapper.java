package project.books.club.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.books.sys.util.CamelMap;


@Mapper
public interface MemberMapper {
	
    /**
	 * 맴버 목록 조회
	 * @param clubNo
	 * @return list, integer
	 * @throw Exception
     */
	int selectMemberListCnt(MemberVO vo);
	List<CamelMap> selectMemberList(MemberVO vo);
}
