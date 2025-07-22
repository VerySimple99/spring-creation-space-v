package org.kosta.myproject.model.vo;

import java.io.Serializable;

/*
 *  인증 정보로 사용되는 객체 : WAS ( Web Container ) 가 reload 시에 객체 직렬화하여 세션 정보를 유지할 수 있도록 
 *  							   java.io.Serializable 인터페이스를 구현한다 
 */
public class MemberVO implements Serializable{
	private static final long serialVersionUID = -4547755095127981561L;
	private String id;
	private String password;
	private String name;
	private String address;
	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberVO(String id, String password, String name, String address) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", name=" + name + ", address=" + address + "]";
	}
	
}
