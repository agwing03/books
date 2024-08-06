package project.books.club.club;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.books.club.cmmn.SrchVO;
import project.books.sys.util.CamelMap;

@RestController
@RequiredArgsConstructor
public class ClubController {
	
	private final ClubService clubService;
	
	/**
	 * 클럽 목록
	 * @param SrchVO
	 * @return ResponseEntity
	 */
	@RequestMapping("/selectClubList.do")
	public ResponseEntity<List<CamelMap>> selectClubList(
			@RequestBody SrchVO vo
		) throws Exception {
		vo = clubService.selectClubList(vo);
		return ResponseEntity.ok(vo.getDataList());
	}

	/**
	 * 클럽 상세
	 * @param SrchVO
	 * @return ResponseEntity
	 */
	@RequestMapping("/selectClubDtl.do")
	public ResponseEntity<CamelMap> selectClubDtl(
			@RequestBody SrchVO vo
		) throws Exception {
		vo = clubService.selectClubDtl(vo);
		return ResponseEntity.ok(vo.getDataMap());
	}
	
	/**
	 * 클럽 등록
	 * @param ClubVO
	 * @return ResponseEntity
	 */
	@RequestMapping("/insertClub.do")
	public ResponseEntity<?> insertClub(
			@RequestBody ClubVO vo
		) throws Exception {
		vo = clubService.insertClub(vo);
		return new ResponseEntity<>(vo.getMsg(), HttpStatus.OK);
	}
	
	/**
	 * 클럽 수정
	 * @param ClubVO
	 * @return ResponseEntity
	 */
	@RequestMapping("/updateClub.do")
	public ResponseEntity<?> updateClub(
			@RequestBody ClubVO vo
		) throws Exception {
		vo = clubService.updateClub(vo);
		return new ResponseEntity<>(vo.getMsg(), HttpStatus.OK);
	}
	
	/**
	 * 클럽 삭제
	 * @param ClubVO
	 * @return ResponseEntity
	 */
	@RequestMapping("/deleteClub.do")
	public ResponseEntity<?> deleteClub(
			@RequestBody ClubVO vo
		) throws Exception {
		vo = clubService.deleteClub(vo);
		return new ResponseEntity<>(vo.getMsg(), HttpStatus.OK);
	}
	
	/**
	 * 클럽 탈퇴
	 * @param SrchVO
	 * @return ResponseEntity
	 */
	@RequestMapping("/updateClubOut.do")
	public ResponseEntity<?> updateClubOut(
			@RequestBody ClubVO vo
		) throws Exception {
		vo = clubService.updateClubOut(vo);
		return new ResponseEntity<>(vo.getMsg(), HttpStatus.OK);
	}
}
 
