package org.kosta.myproject.model;

public class AccountVO {
	private long accountNo;// 컬럼명 : account_no  인스턴스 변수명과 컬럼명이 같지 않음
	private String name;
	private long balance;
	public AccountVO() {
		super();
	}
	
	public AccountVO(String name, long balance) {
		super();
		this.name = name;
		this.balance = balance;
	}

	public AccountVO(long accountNo, String name, long balance) {
		super();
		this.accountNo = accountNo;
		this.name = name;
		this.balance = balance;
	}
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "AccountVO [accountNo=" + accountNo + ", name=" + name + ", balance=" + balance + "]";
	}
	
}
