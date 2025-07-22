package org.kosta.myproject.model.vo;

public class UserVO {
	private String id;
	private String name;
	private CarVO carVO; // has a relationship 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CarVO getCarVO() {
		return carVO;
	}
	public void setCarVO(CarVO carVO) {
		this.carVO = carVO;
	}
	@Override
	public String toString() {
		return "UserVO [id=" + id + ", name=" + name + ", carVO=" + carVO + "]";
	}
	
}
