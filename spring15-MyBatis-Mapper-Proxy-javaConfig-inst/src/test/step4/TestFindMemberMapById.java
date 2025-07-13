package test.step4;

import java.util.Map;

import org.kosta.myproject.config.AppConfig;
import org.kosta.myproject.model.MemberMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestFindMemberMapById {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
		System.out.println(ctx.getBean("memberMapper"));
		MemberMapper mapper=(MemberMapper)ctx.getBean("memberMapper");
		Map<String,String> map=mapper.findMemberMapById("java");
		System.out.println(map);
		ctx.close();
	}
}











