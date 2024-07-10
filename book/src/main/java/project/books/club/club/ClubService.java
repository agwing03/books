package project.books.club.club;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClubService {
	
	private final ClubMapper clubMapper;
	
	/**
	 * 클럽 목록
	 * @param SrchVO
	 * @return SrchVO
	 * @throw Exception
	 */
	public SrchVO selectClubList(SrchVO vo) throws Exception{
		vo.setDataList(clubMapper.selectClubList(vo));
		return vo;
	}
	
	/**
	 * 클럽 상세
	 * @param SrchVO
	 * @return SrchVO
	 * @throw Exception
	 */
	public SrchVO selectClubDtl(SrchVO vo) throws Exception{
		vo.setDataMap(clubMapper.selectClubDtl(vo));
		return vo;
	}
	
	/**
	 * 클럽 등록
	 * @param ClubVO
	 * @return SrchVO
	 * @throw Exception
	 */
	public ClubVO insertClub(ClubVO vo) throws Exception{
		vo.setProcCnt(clubMapper.insertClub(vo));
		if(vo.getProcCnt() > 0) {
			vo.setMsg("완료");
		} else {
			vo.setMsg("미처리");
		}
		return vo;
	}
	
	/**
	 * 클럽 수정
	 * @param ClubVO
	 * @return SrchVO
	 * @throw Exception
	 */
	public ClubVO updateClub(ClubVO vo) throws Exception{
		vo.setProcCnt(clubMapper.updateClub(vo));
		if(vo.getProcCnt() > 0) {
			vo.setMsg("완료");
		} else {
			vo.setMsg("미처리");
		}
		return vo;
	}
	
	/**
	 * 클럽 삭제
	 * @param ClubVO
	 * @return SrchVO
	 * @throw Exception
	 */
	public SrchVO deleteClub(SrchVO vo) throws Exception{
		vo.setProcCnt(clubMapper.deleteClub(vo));
		if(vo.getProcCnt() > 0) {
			vo.setMsg("완료");
		} else {
			vo.setMsg("미처리");
		}
		return vo;
	}
}

