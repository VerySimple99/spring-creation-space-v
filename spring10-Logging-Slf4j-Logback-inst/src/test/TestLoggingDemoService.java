package test;

import org.kosta.myproject.model.LoggingDemoService;

public class TestLoggingDemoService {
	public static void main(String[] args) {
		LoggingDemoService service=new LoggingDemoService();
		service.testLog();
		service.testLog2("월드컵","4강");
	}
}
