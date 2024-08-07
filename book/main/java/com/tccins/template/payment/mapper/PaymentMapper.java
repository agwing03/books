package com.tccins.template.payment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tccins.template.payment.PaymentVO;

@Mapper
public interface PaymentMapper {
    /**
     *  결제정보 상세 조회
     * @param orderNo - PK
     * @return 결제정보 상세정보
     */
	PaymentVO selectPayInfoDetail(PaymentVO paymentVO);
	

    /**
     *  결제정보 목록 조회
     * @param orderNo - PK
     * @return 결제정보 상세정보
     */
	List<PaymentVO> selectPayInfoList(PaymentVO paymentVO);
	
	
}
