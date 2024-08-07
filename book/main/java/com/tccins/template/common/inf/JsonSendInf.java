package com.tccins.template.common.inf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JsonSendInf {
	
	/**
	 * JSON HTTP URL 전송
	 * @param req
	 * @param commandMap
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public void jsonHttpRequest(String urlStr, String method, String jsonObj) throws IOException{
		//URL 생성
		URL url = new URL(urlStr);
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		
		http.setRequestMethod(method);
		http.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

        log.trace("가장 디테일한 로그");
        log.warn("경고");
        log.info("정보성 로그");
        log.debug("디버깅용 로그");
        log.error("에러","에러");
        
		//POST 데이터를 스트림으로  처리후 서버전송
		http.setDoOutput(true);
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(http.getOutputStream()));
		printWriter.write(jsonObj);
		printWriter.flush();
		
		//응답결과
		BufferedReader bufferedReader = null;
		int status = http.getResponseCode();
		//응답성공일 경우
		if(status == HttpURLConnection.HTTP_OK) {
			bufferedReader = new BufferedReader(new InputStreamReader(http.getInputStream()));
		}else {
		//응답실패일 경우
			bufferedReader = new BufferedReader(new InputStreamReader(http.getErrorStream()));
		}
		String line;
		StringBuffer response = new StringBuffer();
		
		//응답 값이 null일때까지
		while( (line = bufferedReader.readLine()) != null) {
			response.append(line);
		}
		bufferedReader.close();
	}
	
}
