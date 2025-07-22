package org.kosta.myproject.model.vo;

import java.io.Serializable;

// 세션에 저장되는 인증정보 객체 클래스이므로 객체 직렬화 처리한다 
public class MemberVO implements Serializable{
	private static final long serialVersionUID = 4319098642053468565L;
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
		//System.out.println("getName()");
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		//System.out.println("getAddress()");
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	// thymeleaf test를 위해 메서드 정의 
	public boolean isNextPageGroup() {
		//System.out.println("isNextPageGroup()");
		return true;
	}
	// thymeleaf test를 위해 메서드 정의 
	public String register() {
		//System.out.println("register");
		return "등록완료";
	}
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", name=" + name + ", address=" + address + "]";
	}
	
}



