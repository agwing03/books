package project.books.club.main;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MainMapper {
	
    /**
     * 입력 ID로 사용자PK 조회
     * @param id - PK
     * @return 게시글 상세정보
     */
	int selectMeetingListCnt(MainVO vo);
	MainVO selectMeetingList(MainVO vo);
	
}
