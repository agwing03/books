package project.books.club.club;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClubService {
	
	private final ClubMapper clubMapper;
	
	/**
	 * 모임 목록 조회
	 * @param clubNo
	 * @return list
	 * @throw Exception
	 */
	public ClubVO selectMeetingList(ClubVO vo) throws Exception{
		int cnt = clubMapper.selectMeetingListCnt(vo);
		if(cnt > 0) {
			vo.setDataList(clubMapper.selectMeetingList(vo));
		}
		return vo;
	}
}
