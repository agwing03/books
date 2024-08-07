package com.tccins.template.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tccins.template.common.CamelMap;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EmailController{
	
	private final EmailSend emailSend;
	
	@RequestMapping("/test/test.do")
	public void test(Model model) throws Exception{
		
		CamelMap data = new CamelMap();
		data.put("ordrdate", "2022년 09월 20일 13시 51분");
		data.put("ordrIdxx", "TEST1234567890");
		data.put("ordrArr", "서울특별시 영등포구");
		data.put("goodName", "운동화");
		data.put("goodMny", "391000");
		data.put("buyrName", "홍길동");
		data.put("buyrTel1", "02-0000-0000");
		data.put("buyrTel2", "010-0000-0000");
		data.put("buyrMail", "jjusuk@tccins.co.kr");
		model.addAttribute("data", data);
		
		//model.addAttribute("data",data);
		HashMap<String , String> values = new HashMap<>();
		values.put("ordrdate",data.get("ordrdate").toString());
		values.put("ordrArr",data.get("ordrArr").toString());
		values.put("ordrIdxx",data.get("ordrIdxx").toString());
		values.put("goodName",data.get("goodName").toString());
		values.put("goodMny", data.get("goodMny").toString());
		values.put("buyrName",data.get("buyrName").toString());
		values.put("buyrTel1",data.get("buyrTel1").toString());
		values.put("buyrTel2",data.get("buyrTel2").toString());
		values.put("buyrMail",data.get("buyrMail").toString());
		
		//email.sendEmailMessage(data.get("buyrMail").toString(), values);
		emailSend.send("결제 바랍니다.",data.get("buyrMail").toString() , "/email/emails.html", values);
	}
	
	@RequestMapping("/test/testView.do")
	public String testView(@ModelAttribute("param") Map<String,Object> param,Model model){
		model.addAttribute("result",param);
		return "email/order";
	}
	
	@RequestMapping("/test/testRedirect.do")
	public String testRedirect(@RequestParam Map<String,Object> comandMap,RedirectAttributes rttr) throws Exception{
		// URL 숨기기 
		rttr.addFlashAttribute("param", comandMap);
		return "redirect:/test/testView.do";
	}
	

}
