package org.kosta.myproject.test;

class Demo {
	private final String ID;
	// final field 는 생성자를 통해 할당이 가능 
	public Demo(String id) {
		ID = id;
	}
	// setter 를 이용해 final 에 정보를 할당 x
	/*
	 * public void setID(String id) { this.ID=id; }
	 */
	public String getId() {
		return ID;
	}
}

public class TestFinal {

}
