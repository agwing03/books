package project.books.club.main;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.books.club.cmmn.SrchVO;

@Service
@RequiredArgsConstructor
public class MainService {
	
	private final MainMapper mainMapper;
	
	/**
	 * 검색
	 * @param SrchVO
	 * @return list
	 * @throw Exception
	 */
	public SrchVO selectCmmnSearch(SrchVO vo) throws Exception{
		vo.setDataList(mainMapper.selectCmmnSearch(vo));
		vo.setTotCnt(vo.getDataList().size());
		return vo;
	}
	
	/**
	 * 알림
	 * @param SrchVO
	 * @return list
	 * @throw Exception
	 */
	public SrchVO selectCmmnAlarm(SrchVO vo) throws Exception{
		vo.setDataList(mainMapper.selectCmmnAlarm(vo));
		vo.setTotCnt(vo.getDataList().size());
		return vo;
	}
	
	
}
