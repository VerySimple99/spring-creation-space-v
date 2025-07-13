package org.kosta.myproject.test.step7;

import java.util.List;

import org.kosta.myproject.config.AppConfig;
import org.kosta.myproject.model.MemberDAO;
import org.kosta.myproject.model.MemberVO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestDeleteMember {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
		MemberDAO dao=(MemberDAO)ctx.getBean("memberDAOImplVer2");
		String id="mybatis";
		int result=dao.deleteMember(id);
		System.out.println("삭제 완료, 삭제 row 수"+result);
		System.out.println("*****삭제 후 리스트*****");
		List<MemberVO> list=dao.findMemberList();
		for(MemberVO vo:list)
			System.out.println(vo);		
		ctx.close();
	}
}
