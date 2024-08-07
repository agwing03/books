package project.books.club.member;

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
public class MemberController {
	
	private final MemberService memberService;
	 
	/**
	 * 맴버 목록 조회
	 * @param SrchVO
	 * @return ResponseEntity
	 */
	public ResponseEntity<List<CamelMap>> selectMemberList(
			@RequestBody SrchVO vo
		) throws Exception {
		vo = memberService.selectMemberList(vo);
		return ResponseEntity.ok(vo.getDataList());
	}
	
	/**
	 * 맴버 저장
	 * @param MeetingVO
	 * @return ResponseEntity
	 */
	@RequestMapping("/insertMember.do")
	public ResponseEntity<?> insertMember(
			@RequestBody MemberVO vo
		) throws Exception {
		vo = memberService.insertMember(vo);
		return new ResponseEntity<>(vo.getMsg(), HttpStatus.OK);
	}
	
	/**
	 * 맴버 실시간 검색(공통모듈)
	 * @param SrchVO
	 * @return ResponseEntity
	 */
	@RequestMapping("/selectMemberRealTimeSrch.do")
	public ResponseEntity<List<CamelMap>> selectMemberRealTimeSrch(
			@RequestBody SrchVO vo
		) throws Exception {
		vo = memberService.selectMemberRealTimeSrch(vo);
		return ResponseEntity.ok(vo.getDataList());
	}
}
 