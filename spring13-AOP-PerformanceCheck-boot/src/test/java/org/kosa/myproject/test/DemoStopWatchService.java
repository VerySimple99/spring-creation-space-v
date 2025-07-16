package org.kosa.myproject.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

public class DemoStopWatchService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	public String findAllList() {

		StopWatch watch = new StopWatch();
		watch.start();// 시간 측정 시작

		try {
			Thread.sleep(800);
			System.out.println("리스트 조회 작업...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		watch.stop(); // 측정 종료
		long time = watch.getTotalTimeMillis();
		if(time>=500&&time<1000) {
			logger.warn("메서드 실행 소요시간 {}",time);
		}else if(time>=1000) {
			logger.error("메서드 실행 소요시간 {}",time);
		}
		return "리스트 정보";
	}

}





