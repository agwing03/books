package com.tccins.template.payment;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tccins.template.common.LayoutModule;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor // final 필드나 @Nonnull이 붙은 필드에 대해 생성자를 주입해줌
public class PaymentController extends LayoutModule{
	/**
	 * XML전송 화면
	 * @param req
	 * @param commandMap
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/payment/payXmlSendView.do")
	public String payXmlSendView(HttpServletRequest req, Model model) throws Exception {
		return "payment/payXmlSendView";
	}
	
	/**
	 * JSON전송화면
	 * @param req
	 * @param commandMap
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/payment/payJsonSendView.do")
	public String payJsonSendView(HttpServletRequest req
			,Model model) throws Exception {
		return "payment/payJsonSendView";
	}
	
	
}