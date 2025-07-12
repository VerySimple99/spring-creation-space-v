package test;

import org.kosta.myproject.config.AppConfig;
import org.kosta.myproject.model.WorkerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestIOC {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
		/*
		Tool tool1=(Tool) ctx.getBean("hammer");
		tool1.work();
		Tool tool2=(Tool) ctx.getBean("spade");
		tool2.work();		
		Tool tool3=(Tool) ctx.getBean("tool");
		tool3.work();
		*/
		WorkerService service=(WorkerService) ctx.getBean("workerServiceImpl");
		service.service();
		ctx.close();
	}
}








