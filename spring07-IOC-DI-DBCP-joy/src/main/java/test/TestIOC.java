package test;

import java.sql.SQLException;

import org.kosta.myproject.config.AppConfig;
import org.kosta.myproject.model.MemberService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestIOC {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=
				new AnnotationConfigApplicationContext(AppConfig.class);
		//System.out.println(ctx.getBean("dataSource"));
		//MemberDAO dao=(MemberDAO)ctx.getBean("memberDAOImpl");
		MemberService service=(MemberService)ctx.getBean("memberServiceImpl");
		try {
			//System.out.println(dao.findMemberById("java"));
			System.out.println(service.findMemberById("java"));
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		ctx.close();
	}
}





