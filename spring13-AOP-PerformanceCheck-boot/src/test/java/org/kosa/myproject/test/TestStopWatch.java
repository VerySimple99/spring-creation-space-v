package org.kosa.myproject.test;

public class TestStopWatch {
	/*
	 *  메서드 소요 시간을 구하는 spring 의 StopWatch 를 테스트하는 예제 
	 */
	public static void main(String[] args) {
		DemoStopWatchService service=new DemoStopWatchService();
		System.out.println(service.findAllList());
	}
}
  