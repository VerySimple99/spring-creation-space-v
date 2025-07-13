package org.kosta.myproject.test.step5;

import java.util.List;

import org.kosta.myproject.model.AccountDAO;
import org.kosta.myproject.model.AccountVO;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestFindAccountListGreaterThanMoneyOrderByBalanceDesc {
	public static void main(String[] args) {
		// spring xml 설정을 로딩해 Spring Container 를 생성 
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("spring-mybatis-config.xml");
		AccountDAO dao=(AccountDAO)ctx.getBean("accountDAOImpl");
		long money=250;
		//지정한 money 250 보다 큰 잔액을 가진 계좌 리스트를 조회한다 ( 잔액 내림차순으로 정렬 )
		// sql -> AccountMapper.xml -> DAOImpl -> Test
	    List<AccountVO> list=dao.findAccountListGreaterThanMoneyOrderByBalanceDesc(money);
	    for(AccountVO vo:list)
	    	System.out.println(vo);
		ctx.close();
	}
}











