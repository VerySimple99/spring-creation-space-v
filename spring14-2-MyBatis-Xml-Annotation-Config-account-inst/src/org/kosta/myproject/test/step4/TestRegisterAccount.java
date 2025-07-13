package org.kosta.myproject.test.step4;

import java.util.List;

import org.kosta.myproject.model.AccountDAO;
import org.kosta.myproject.model.AccountVO;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestRegisterAccount {
	public static void main(String[] args) {
		// spring xml 설정을 로딩해 Spring Container 를 생성 
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("spring-mybatis-config.xml");
		AccountDAO dao=(AccountDAO)ctx.getBean("accountDAOImpl");
		//신규 계좌 등록 : DAOImpl , mapper xml 을 구현해본다 
		int result=dao.register(new AccountVO("아이유",700));
		System.out.println("계좌 등록완료:"+result);
	    List<AccountVO> list=dao.findAccountListOrderByNoDesc();
	    for(AccountVO vo:list)
	    	System.out.println(vo);
		ctx.close();
	}
}











