package org.kosta.myproject.test.step2;

import org.kosta.myproject.model.AccountDAO;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestFindAccountByNo {
	public static void main(String[] args) {
		// spring xml 설정을 로딩해 Spring Container 를 생성 
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("spring-mybatis-config.xml");
		AccountDAO dao=(AccountDAO)ctx.getBean("accountDAOImpl");
		System.out.println(dao.findAccountByNo(1));
		ctx.close();
	}
}
