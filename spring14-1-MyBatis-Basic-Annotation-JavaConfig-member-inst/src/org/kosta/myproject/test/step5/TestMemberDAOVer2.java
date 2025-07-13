package org.kosta.myproject.test.step5;

import java.util.List;

import org.kosta.myproject.config.AppConfig;
import org.kosta.myproject.model.MemberDAO;
import org.kosta.myproject.model.MemberVO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMemberDAOVer2 {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
		MemberDAO dao=(MemberDAO)ctx.getBean("memberDAOImplVer2");
		List<MemberVO> list=dao.findMemberList();
		for(MemberVO vo:list)
			System.out.println(vo);
		System.out.println("----------------------");
		System.out.println(dao.getTotalMemberCount());
		ctx.close();
	}
}
