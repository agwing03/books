package com.tccins.template.payment;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tccins.template.payment.mapper.PaymentMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
	private final PaymentMapper paymentMapper;
	 
    /**
     * 결제정보 상세 조회
     * @param orderNo
     * @return 전송결과
     */
    public PaymentVO payInfoDetail(PaymentVO paymentVO) {
    	//결제정보 상세조회
    	paymentVO = paymentMapper.selectPayInfoDetail(paymentVO);
        return paymentVO;
    }
    
    /**
     * 결제정보 목록 조회
     * @param orderNo
     * @return 전송결과
     */
    public List<PaymentVO> selectPayInfoList(PaymentVO paymentVO) {
    	//결제정보 상세조회
    	List<PaymentVO> paymentList = paymentMapper.selectPayInfoList(paymentVO);
        return paymentList;
    }

}
