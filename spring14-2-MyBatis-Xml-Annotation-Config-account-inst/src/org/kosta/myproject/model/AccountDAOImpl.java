package org.kosta.myproject.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class AccountDAOImpl implements AccountDAO {
	// MyBatis 의 핵심 객체 SqlSession 을 효과적으로 사용하도록 지원 , AOP 기반 트랜잭션 지원(이후 공부예정) => SqlSessionTemplate
	private SqlSessionTemplate sqlSessionTemplate;
	@Autowired
	public AccountDAOImpl(SqlSessionTemplate sqlSessionTemplate) {
		super();
		this.sqlSessionTemplate = sqlSessionTemplate;
	}	
	@Override
	public int getTotalAccountCount() {
		return sqlSessionTemplate.selectOne("account.getTotalAccountCount");
	}
	@Override
	public AccountVO findAccountByNo(long no) {		
		return sqlSessionTemplate.selectOne("account.findAccountByNo", no);
	}
	@Override
	public List<AccountVO> findAccountListOrderByNoDesc() {		
		return sqlSessionTemplate.selectList("account.findAccountListOrderByNoDesc");
	}
	@Override
	public int register(AccountVO accountVO) {		
		return sqlSessionTemplate.insert("account.register",accountVO);
	}
	@Override
	public List<AccountVO> findAccountListGreaterThanMoneyOrderByBalanceDesc(long money) {		
		return sqlSessionTemplate.selectList("account.findAccountListGreaterThanMoneyOrderByBalanceDesc",money);
	}
	@Override
	public List<AccountVO> findAccountListLessThanMoneyOrderByBalanceDesc(long money) {		
		return sqlSessionTemplate.selectList("account.findAccountListLessThanMoneyOrderByBalanceDesc",money);
	}
}















