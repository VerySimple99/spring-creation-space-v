package org.kosta.myproject.test.step2;

import org.kosta.myproject.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestSqlSessionFactory {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
		System.out.println(ctx.getBean("memberDAOImplVer1"));		
		ctx.close();
	}
}
