package project.books.club.meeting;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.books.club.club.ClubVO;
import project.books.club.cmmn.SrchVO;
import project.books.sys.util.CamelMap;

@RestController
@RequiredArgsConstructor
public class MeetingController {
	
	private final MeetingService meetingService;
	 
	/**
	 * 모임 목록 조회
	 * @param SrchVO
	 * @return ResponseEntity
	 */
	@RequestMapping("/selectMeetingList.do")
	public ResponseEntity<List<CamelMap>> selectMeetingList(
			@RequestBody SrchVO vo
		) throws Exception {
		vo = meetingService.selectMeetingList(vo);
		return ResponseEntity.ok(vo.getDataList());
	}
	
	/**
	 * 모임 상세 조회
	 * @param MeetingVO
	 * @return ResponseEntity
	 */
	@RequestMapping("/selectMeetingDtl.do")
	public ResponseEntity<?> selectMeetingDtl(
			@RequestBody MeetingVO vo
		) throws Exception {
		vo = meetingService.selectMeetingDtl(vo);
		return ResponseEntity.ok(vo);
	}
	
	/**
	 * 모임 정보 조회(변경 팝업)
	 * @param MeetingVO
	 * @return ResponseEntity
	 */
	@RequestMapping("/selectMeetingInfo.do")
	public ResponseEntity<?> selectMeetingInfo(
			@RequestBody MeetingVO vo
		) throws Exception {
		vo = meetingService.selectMeetingInfo(vo);
		return ResponseEntity.ok(vo);
	}
	
	/**
	 * 모임 등록
	 * @param MeetingVO
	 * @return ResponseEntity
	 */
	@RequestMapping("/insertMeeting.do")
	public ResponseEntity<?> insertMeeting(
			@RequestBody MeetingVO vo
		) throws Exception {
		vo = meetingService.insertMeeting(vo);
		return new ResponseEntity<>(vo.getMsg(), HttpStatus.OK);
	}

	/**
	 * 모임 수정
	 * @param MeetingVO
	 * @return ResponseEntity
	 */
	@RequestMapping("/updateMeeting.do")
	public ResponseEntity<?> updateMeeting(
			@RequestBody MeetingVO vo
		) throws Exception {
		vo = meetingService.updateMeeting(vo);
		return new ResponseEntity<>(vo.getMsg(), HttpStatus.OK);
	}

	/**
	 * 모임 삭제
	 * @param MeetingVO
	 * @return ResponseEntity
	 */
	@RequestMapping("/deleteMeeting.do")
	public ResponseEntity<?> deleteMeeting(
			@RequestBody MeetingVO vo
		) throws Exception {
		vo = meetingService.deleteMeeting(vo);
		return new ResponseEntity<>(vo.getMsg(), HttpStatus.OK);
	}
	
	
	
	/**
	 * 모임후기 등록
	 * @param MeetingVO
	 * @return ResponseEntity
	 */
	@RequestMapping("/insertMeetingReview.do")
	public ResponseEntity<?> insertMeetingReview(
			@RequestBody MeetingVO vo
		) throws Exception {
		vo = meetingService.insertMeetingReview(vo);
		return new ResponseEntity<>(vo.getMsg(), HttpStatus.OK);
	}

	/**
	 * 모임후기 수정
	 * @param MeetingVO
	 * @return ResponseEntity
	 */
	@RequestMapping("/updateMeeting.do")
	public ResponseEntity<?> updateMeetingReview(
			@RequestBody MeetingVO vo
		) throws Exception {
		vo = meetingService.updateMeetingReview(vo);
		return new ResponseEntity<>(vo.getMsg(), HttpStatus.OK);
	}
}
 
