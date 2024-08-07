package com.tccins.template.payment;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tccins.template.common.inf.JsonSendInf;
import com.tccins.template.common.inf.RootElement;
import com.tccins.template.common.inf.RootElement.Node1;
import com.tccins.template.common.inf.RootElement.Node1.Node2;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PaymentApiController {
	private final Gson gson;
	
	private final PaymentService paymentService;
	
	private final JsonSendInf jsonSendInf;
	
	/**
	 * XML 파싱 후 전송
	 * @param req
	 * @param commandMap
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @GetMapping(value="/payment/payXmlSend.do")
	public RootElement payXmlSend(/* @RequestBody String orderNo */) {
    	
    	RootElement rootElement = new RootElement();
    	
    	//1번째 노드값 (2뎁스)
    	Node1 node1 = new Node1();
    	node1.setResultCode("200");
    	node1.setResultMsg("성공");

    	PaymentVO paymentVO = new PaymentVO();
    	paymentVO.setOrderNo("10000");
    	paymentVO = paymentService.payInfoDetail(paymentVO);
    	node1.setItem(paymentVO);
    	
    	
    	Node2 node2 = new Node2();
    	node2.setCode("SUCCES");
    	List<PaymentVO> testList =  paymentService.selectPayInfoList(paymentVO);
    	node2.setItems(testList);

    	node1.setNode2(node2);
    	rootElement.setNode1(node1);
    	

    	//예시로 payment 테스트 리스트 불러옴
    	return rootElement;
    }
    
	
    
    
	
	/**
	 * JSON 전송
	 * @param req
	 * @param commandMap
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @PostMapping(value="/payment/payJsonSend.do")
    public void payJsonSend(@RequestBody PaymentVO paymentVO) {
    	String urlStr = "http://localhost:8090/payment/jsonReciveTest";
    	try {
    		//주문내역 1건
    		paymentVO = paymentService.payInfoDetail(paymentVO);
    		String json = gson.toJson(paymentVO);
			jsonSendInf.jsonHttpRequest(urlStr, "POST", json);
			
			//주문내역 리스트
			urlStr = "http://localhost:8090/payment/jsonReciveTestList";
			List<PaymentVO> paymentList =  paymentService.selectPayInfoList(paymentVO);
    		String json2 = gson.toJson(paymentList);
			jsonSendInf.jsonHttpRequest(urlStr, "POST", json2);
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	/**
	 * JSON 수신 테스트 (1건)
	 * @param req
	 * @param commandMap
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @PostMapping(value = "/payment/jsonReciveTest")
    public String jsonReciveTest(@RequestBody HashMap<String, Object> map) {
	    System.out.println("수신받은 JSON 데이터 = " + map);
	    return "redirect:/main.do";
    }
    
	/**
	 * JSON 수신 테스트 (목록)
	 * @param req
	 * @param commandMap
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @PostMapping(value = "/payment/jsonReciveTestList")
    public String jsonReciveTestList(@RequestBody List<HashMap<String, Object>> list) {
	    System.out.println("수신받은 JSON 데이터 = " + list);
	    return "redirect:/main.do";
    }
}
