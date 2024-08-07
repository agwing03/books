package com.tccins.template.board;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tccins.template.common.LayoutModule;
import com.tccins.template.common.dto.MessageDto;
import com.tccins.template.common.dto.SearchDto;
import com.tccins.template.config.CustomUserDetails;
import com.tccins.template.paging.PagingResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController extends LayoutModule{
	  private final BoardService boardService;

	    // 게시글 작성 페이지
	    @GetMapping("/board/write.do")
	    public String openPostWrite(@RequestParam(value = "id", required = false) final Long id
	    							,Model model
	    							,@AuthenticationPrincipal CustomUserDetails userInfo) {
	    		
	    	System.out.println(userInfo.getUsername());
	    	System.out.println(userInfo.getAuthorities());
	    	System.out.println(userInfo.getDeptCd());
	    	
	        if (id != null) {
	            BoardVO board = boardService.findBoardById(id);
	            model.addAttribute("board", board);
	        }
	    	
	        return "board/write";
	    }
	
	    // 신규 게시글 생성
	    @PostMapping("/board/save.do")
	    public String saveBoard(final BoardVO params, Model model) {
	    	boardService.saveBoard(params);
	    	MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/board/list.do", RequestMethod.GET, null);
	        return showMessageAndRedirect(message, model);
	    }
	
	    // 게시글 리스트 페이지
	    @GetMapping("/board/list.do")
	    public String openBoardList(@ModelAttribute("params") final SearchDto params,Model model) {
	    	PagingResponse<BoardVO> response = boardService.findAllBoard(params);
	        model.addAttribute("response", response);
	        return "board/list";
	    }
	    
	    // 게시글 상세 페이지
	    @GetMapping("/board/view.do")
	    public String openPostView(@RequestParam final Long id, Model model) {
	    	BoardVO board = boardService.findBoardById(id);
	        model.addAttribute("board", board);
	        return "board/view";
	    }
	    
	    // 기존 게시글 수정
	    @PostMapping("/board/update.do")
	    public String updatePost(final BoardVO params, Model model) {
	    	boardService.updateBoard(params);

	    	MessageDto message = new MessageDto("게시글 수정이 완료되었습니다.", "/board/list.do", RequestMethod.GET, null);
	    	return showMessageAndRedirect(message, model);
	    }
	    
	    // 게시글 삭제
	    @PostMapping("/board/delete.do")
	    public String deleteBoard(@RequestParam final Long id, Model model) {
	    	boardService.deleteBoard(id);

	    	MessageDto message = new MessageDto("게시글 삭제가 완료되었습니다.", "/board/list.do", RequestMethod.GET, null);
	    	return showMessageAndRedirect(message, model);
	    }
	    
}
