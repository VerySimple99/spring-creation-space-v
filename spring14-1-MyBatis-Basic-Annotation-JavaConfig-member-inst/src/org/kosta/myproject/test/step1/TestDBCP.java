package org.kosta.myproject.test.step1;

import org.kosta.myproject.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestDBCP {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
		System.out.println(ctx.getBean("dataSource"));
		System.out.println(ctx.getBean("dataSource"));
		System.out.println(ctx.getBean("dataSource"));//모두 동일한 주소값, IOC Container 는 bean 을 singleton 으로 생성 관리함 
		ctx.close();
	}
}
