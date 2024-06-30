package project.books.club.main;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.books.club.cmmn.SrchVO;

@Service
@RequiredArgsConstructor
public class MainService {
	
	private final MainMapper mainMapper;
	
	/**
	 * 화면 프로필
	 * @param SrchVO
	 * @return CamelMap
	 * @throw Exception
	 */
	public SrchVO selectCmmnMemberProfile(SrchVO vo) throws Exception{
		vo.setDataMap(mainMapper.selectCmmnMemberProfile(vo));
		return vo;
	}
	
	/**
	 * 검색
	 * @param SrchVO
	 * @return List
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
	 * @return List
	 * @throw Exception
	 */
	public SrchVO selectCmmnAlarm(SrchVO vo) throws Exception{
		vo.setDataList(mainMapper.selectCmmnAlarm(vo));
		vo.setTotCnt(vo.getDataList().size());
		return vo;
	}
	
	
}
