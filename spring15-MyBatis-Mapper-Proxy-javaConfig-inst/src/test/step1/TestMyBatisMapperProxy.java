package test.step1;

import org.kosta.myproject.config.AppConfig;
import org.kosta.myproject.model.AccountMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMyBatisMapperProxy {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
		System.out.println(ctx.getBean("accountMapper"));
		AccountMapper am=(AccountMapper)ctx.getBean("accountMapper");
		System.out.println(am.findAccountByNo(1));
		ctx.close();
	}
}
