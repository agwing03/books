package com.tccins.haein.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderVO{
    private String orderNo;      // 내용
    private String prductNm;       // 제품명
    private String price;       // 가격
    private String userNm;       // 주문자명
    private String phone;       // 핸드폰
    private String tel;       // 전화번호
    private String email;       // 이메일
    private String address;       // 주소
    private String orderDate;       // 주문일자
}
