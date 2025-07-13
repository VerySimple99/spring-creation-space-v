package org.kosta.myproject.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository // 영속성 계층 persistence layer 에 적용하는 컴포넌트 애너테이션
// Ver2 가 만들어 졌으므로 사용하지 않는다 
public class MemberDAOImplVer1 implements MemberDAO {
	private Logger log=LoggerFactory.getLogger(getClass());
	// SqlSession : Sql 을 처리하는 MyBatis 의 핵심 객체 
	// SqlSessionFactory : SqlSession 을 생성하는 공장 역할 
	private SqlSessionFactory factory;
	@Autowired // 일치하는 타입에 해당하는 bean을 DI
	public MemberDAOImplVer1(SqlSessionFactory factory) {
		super();
		this.factory = factory;
		log.debug(factory.toString());
	}
	@Override
	public int getTotalMemberCount() {
		SqlSession session=null;
		int count=0;
		try {
			session=factory.openSession();
			count=session.selectOne("member.getTotalMemberCount");
		}finally {
			session.close();
		}
		return count;
	}
	@Override
	public List<MemberVO> findMemberList(){
		SqlSession session=null;
		List<MemberVO> list=null;
		try {
			session=factory.openSession();
			list=session.selectList("member.findMemberList");
		}finally {
			session.close();
		}
		return list;
	}
	@Override
	public int register(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return 0;
	}
}













