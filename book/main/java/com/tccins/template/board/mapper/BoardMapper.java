package com.tccins.template.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tccins.template.board.BoardVO;
import com.tccins.template.common.dto.SearchDto;

@Mapper
public interface BoardMapper {
    /**
     * 게시글 저장
     * @param params - 게시글 정보
     */
    void save(BoardVO params);

    /**
     * 게시글 상세정보 조회
     * @param id - PK
     * @return 게시글 상세정보
     */
    BoardVO findById(Long id);
    
    /**
     * 게시글 수정
     * @param params - 게시글 정보
     */
    void update(BoardVO params);

    /**
     * 게시글 삭제
     * @param id - PK
     */
    void deleteById(Long id);

    /**
     * 게시글 리스트 조회
     * @return 게시글 리스트
     */
    List<BoardVO> findAll(SearchDto params);

    /**
     * 게시글 수 카운팅
     * @return 게시글 수
     */
    int count(SearchDto params);
}
