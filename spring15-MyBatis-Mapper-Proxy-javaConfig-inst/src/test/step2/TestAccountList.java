package test.step2;

import java.util.List;

import org.kosta.myproject.config.AppConfig;
import org.kosta.myproject.model.AccountMapper;
import org.kosta.myproject.model.AccountVO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestAccountList {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
		System.out.println(ctx.getBean("accountMapper"));
		AccountMapper am=(AccountMapper)ctx.getBean("accountMapper");
		List<AccountVO> list=am.findAccountListOrderByNoDesc();
		for(AccountVO vo:list)
			System.out.println(vo);
		ctx.close();
	}
}
