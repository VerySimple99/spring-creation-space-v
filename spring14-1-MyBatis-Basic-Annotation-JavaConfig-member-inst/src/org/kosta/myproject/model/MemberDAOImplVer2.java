package org.kosta.myproject.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository // 영속성 계층 persistence layer 에 적용하는 컴포넌트 애너테이션
public class MemberDAOImplVer2 implements MemberDAO {
	private Logger log=LoggerFactory.getLogger(getClass());
	// SqlSession : Sql 을 처리하는 MyBatis 의 핵심 객체 
	// SqlSessionFactory : SqlSession 을 생성하는 공장 역할 
	//private SqlSessionFactory factory;
	//더 생산성 있는 개발을 위해 SqlSessionTemplate 을 version 2 에서 이용해 본다 
	private SqlSessionTemplate template;
	@Autowired // 일치하는 타입에 해당하는 bean을 DI
	public MemberDAOImplVer2(SqlSessionTemplate template) {
		super();
		this.template = template;
		log.debug(template.toString());
	}
	@Override
	public int getTotalMemberCount() {					
		return template.selectOne("member.getTotalMemberCount");
	}
	@Override
	public List<MemberVO> findMemberList(){		
		return template.selectList("member.findMemberList");
	}
	@Override
	public int register(MemberVO memberVO) {		
		return template.insert("member.register", memberVO);
	}
	@Override
	public int deleteMember(String id) {		
		return template.delete("member.deleteMember",id);
	}
}













