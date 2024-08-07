package com.tccins;

import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.tccins.template.common.CommonService;

// 파일 첨부 위하여 MultipartAutoConfig 추가
@SpringBootApplication(exclude={MultipartAutoConfiguration.class})
@EnableTransactionManagement // @transactional 어노테이션을 사용하기 위함
public class TccTemplateApplication{

	@Resource(name = "commonService")
	private CommonService commonService;
	
	public static void main(String[] args) {
		SpringApplication.run(TccTemplateApplication.class, args);
		
		Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            	//10초마다 주문정보 테이블 조회
            	
            	//메일,sms전송여부 N인데이터들 
            	
            	//메일전송
            	
            	//sms전송
            	
            	//전송여부 업데이트
            	
				/* System.out.println("Running: " + new java.util.Date()); */
            }
        }, 0, 10000);
		
	}

}
