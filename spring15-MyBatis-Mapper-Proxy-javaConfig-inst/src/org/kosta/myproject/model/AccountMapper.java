package org.kosta.myproject.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper // MyBatis Mapper Proxy ( DAOImpl ) 를 위한 어노테이션 , 현 인터페이스의 구현 클래스(Proxy)를 자동 생성
public interface AccountMapper {
	AccountVO findAccountByNo(long accountNo);

	List<AccountVO> findAccountListOrderByNoDesc();	
}
