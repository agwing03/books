package project.books.club.main;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainService {
	
	private MainMapper mainMapper;
	
	/**
	 * 부서리스트 조회
	 * @param SearchDto
	 * @return list
	 * @throw Exception
	 */
	public MainVO selectMeetingList(MainVO vo) throws Exception{
		int cnt = mainMapper.selectMeetingListCnt(vo);
		if(cnt > 0) {
			mainMapper.selectMeetingList(vo);
		}
		return vo;
	}
}
