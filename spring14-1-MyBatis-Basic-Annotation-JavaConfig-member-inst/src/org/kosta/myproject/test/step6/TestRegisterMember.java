package org.kosta.myproject.test.step6;

import java.util.List;

import org.kosta.myproject.config.AppConfig;
import org.kosta.myproject.model.MemberDAO;
import org.kosta.myproject.model.MemberVO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestRegisterMember {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
		MemberDAO dao=(MemberDAO)ctx.getBean("memberDAOImplVer2");
		// MyBatis 전달 파라미터는 주로 String or 자바기본형 or VO or Map 으로 전달 
		// 회원 가입이므로 MemberVO 로 전달하는 것이 적합 
		MemberVO memberVO=new MemberVO("mybatis","a","이강인","파리");
		int result=dao.register(memberVO);
		System.out.println("등록 완료 insert :"+result);
		System.out.println("*****등록 후 리스트*****");
		List<MemberVO> list=dao.findMemberList();
		for(MemberVO vo:list)
			System.out.println(vo);		
		ctx.close();
	}
}
