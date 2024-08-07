package com.tccins.haein.order;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tccins.haein.order.mapper.OrderMapper;
import com.tccins.template.common.dto.SearchDto;
import com.tccins.template.paging.Pagination;
import com.tccins.template.paging.PagingResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	 private final OrderMapper orderMapper;
	
    /**
     * 주문 목록 조회
     * @return 주문목록 리스트
     */
    public PagingResponse<OrderVO> selectOrderList(final SearchDto params) {
        int count = orderMapper.selectOrderListCount(params);
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        List<OrderVO> list = orderMapper.selectOrderList(params);
        return new PagingResponse<>(list, pagination);
    }

}
