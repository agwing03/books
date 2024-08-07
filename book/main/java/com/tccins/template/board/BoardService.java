package com.tccins.template.board;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.tccins.template.board.mapper.BoardMapper;
import com.tccins.template.common.dto.SearchDto;
import com.tccins.template.paging.Pagination;
import com.tccins.template.paging.PagingResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	 private final BoardMapper boardMapper;

	  /**
	     * 게시글 저장
	     * @param params - 게시글 정보
	     * @return Generated PK
	     */
	    @Transactional
	    public Long saveBoard(final BoardVO params) {
	    	boardMapper.save(params);
	        return params.getId();
	    }

	    /**
	     * 게시글 상세정보 조회
	     * @param id - PK
	     * @return 게시글 상세정보
	     */
	    public BoardVO findBoardById(final Long id) {
	        return boardMapper.findById(id);
	    }

	    /**
	     * 게시글 수정
	     * @param params - 게시글 정보
	     * @return PK
	     */
	    @Transactional
	    public Long updateBoard(final BoardVO params) {
	    	boardMapper.update(params);
	        return params.getId();
	    }

	    /**
	     * 게시글 삭제
	     * @param id - PK
	     * @return PK
	     */
	    public Long deleteBoard(final Long id) {
	    	boardMapper.deleteById(id);
	        return id;
	    }

	    /**
	     * 게시글 리스트 조회
	     * @return 게시글 리스트
	     */
	    public PagingResponse<BoardVO> findAllBoard(final SearchDto params) {
	        int count = boardMapper.count(params);
	        Pagination pagination = new Pagination(count, params);
	        params.setPagination(pagination);

	        List<BoardVO> list = boardMapper.findAll(params);
	        return new PagingResponse<>(list, pagination);
	    }

}
