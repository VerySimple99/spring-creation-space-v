package test.step5;

import java.util.HashMap;
import java.util.Map;

import org.kosta.myproject.config.AppConfig;
import org.kosta.myproject.model.MemberMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestRegisterMemberParamMap {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
		System.out.println(ctx.getBean("memberMapper"));
		MemberMapper mapper=(MemberMapper)ctx.getBean("memberMapper");
		String id="seoul";
		Map<String,String> map=mapper.findMemberMapById(id);
		if(map==null) {
			// VO 대신 테스트 차원에서 Map 을 만들어 회원가입을 해본다 
			HashMap<String,String> paramMap=new HashMap<>();
			paramMap.put("ID", id);
			paramMap.put("PASS", "a");
			paramMap.put("NAME", "조규성");
			paramMap.put("ADDR", "전주");
			int result=mapper.register(paramMap);
			System.out.println("회원가입완료 "+result);
		}else {
			System.out.println("id에 해당하는 회원이 존재함:"+map);
		}
		ctx.close();
	}
}











