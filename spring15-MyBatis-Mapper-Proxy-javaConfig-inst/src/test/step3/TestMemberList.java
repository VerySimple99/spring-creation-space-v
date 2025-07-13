package test.step3;

import java.util.List;

import org.kosta.myproject.config.AppConfig;
import org.kosta.myproject.model.MemberMapper;
import org.kosta.myproject.model.MemberVO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMemberList {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
		System.out.println(ctx.getBean("memberMapper"));
		MemberMapper mapper=(MemberMapper)ctx.getBean("memberMapper");
		List<MemberVO> list=mapper.findAllMemberList();
		for(MemberVO vo:list)
			System.out.println(vo);
		ctx.close();
	}
}











