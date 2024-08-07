package com.tccins.haein.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tccins.haein.order.OrderVO;
import com.tccins.template.common.dto.SearchDto;


@Mapper
public interface OrderMapper {

    /**
     * 주문목록 조회
     * @return 게시글 리스트
     */
    List<OrderVO> selectOrderList(SearchDto params);

    /**
     * 주문목록 목록수 조회
     * @return 게시글 리스트
     */
    int selectOrderListCount(SearchDto params);
}
