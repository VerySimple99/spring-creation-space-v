package org.kosta.myproject.model;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingDemoService {
	private Logger logger=LoggerFactory.getLogger(getClass());
	public void testLog() {
		// 로깅 레벨별로 실행 
		logger.trace("trace test");
		logger.debug("debug test");
		logger.info("info test");
		logger.warn("warn test");
		ArrayList<String> list=new ArrayList<String>();
		try {
		list.get(1);
		}catch(Exception e) {
			logger.error("error test",e);
		}
				
	}
	public void testLog2(String string, String string2) {
		// {} : 변수의 데이터로 출력 		
		logger.info("info log test message {} message2 {}",string,string2);
		//아래는 성능을 위해 피해야 할 로깅 스타일 : String 문자열이 새로 생성되므로 
	//	logger.info("info log test message "+string+" message2 {}"+string2);
	}
}












