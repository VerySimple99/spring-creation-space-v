package org.kosta.myproject.model;

import java.util.List;

public interface AccountDAO {

	int getTotalAccountCount();

	AccountVO findAccountByNo(long no);

	List<AccountVO> findAccountListOrderByNoDesc();

	int register(AccountVO accountVO);

	List<AccountVO> findAccountListGreaterThanMoneyOrderByBalanceDesc(long money);

	List<AccountVO> findAccountListLessThanMoneyOrderByBalanceDesc(long money);
}