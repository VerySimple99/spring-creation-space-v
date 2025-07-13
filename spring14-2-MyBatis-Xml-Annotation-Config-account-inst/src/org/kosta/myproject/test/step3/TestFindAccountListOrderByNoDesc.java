package org.kosta.myproject.test.step3;

import java.util.List;

import org.kosta.myproject.model.AccountDAO;
import org.kosta.myproject.model.AccountVO;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestFindAccountListOrderByNoDesc {
	public static void main(String[] args) {
		// spring xml 설정을 로딩해 Spring Container 를 생성 
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("spring-mybatis-config.xml");
		AccountDAO dao=(AccountDAO)ctx.getBean("accountDAOImpl");
	    List<AccountVO> list=dao.findAccountListOrderByNoDesc();
	    for(AccountVO vo:list)
	    	System.out.println(vo);
		ctx.close();
	}
}











