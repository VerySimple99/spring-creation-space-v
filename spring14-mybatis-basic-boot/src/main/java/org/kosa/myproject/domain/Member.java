package org.kosa.myproject.domain;
/*
 controller: org.kosa.myproject.controller

service: org.kosa.myproject.service
// 데이터 구조를 넘어, 해당 비즈니스 영역의 핵심 로직과 규칙이 담긴 객체
domain: org.kosa.myproject.domain

repository: org.kosa.myproject.repository
 */
public class Member {
	private String id;
	private String password;
	private String name;
	private String address;
//	public Member() {
//		super();
//		// TODO Auto-generated constructor stub
//	}	
	public Member(String id, String password, String name, String address) {
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
		return "Member [id=" + id + ", password=" + password + ", name=" + name + ", address=" + address + "]";
	}
}
