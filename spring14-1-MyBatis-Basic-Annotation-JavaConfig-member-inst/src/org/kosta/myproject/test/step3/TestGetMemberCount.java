package org.kosta.myproject.test.step3;

import org.kosta.myproject.config.AppConfig;
import org.kosta.myproject.model.MemberDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestGetMemberCount {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
		MemberDAO dao=(MemberDAO)ctx.getBean("memberDAOImplVer1");
		System.out.println(dao.getTotalMemberCount());
		ctx.close();
	}
}
