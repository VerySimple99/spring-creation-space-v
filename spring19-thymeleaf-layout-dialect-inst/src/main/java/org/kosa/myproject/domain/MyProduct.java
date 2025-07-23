package org.kosa.myproject.domain;
// thymeleaf test class : step07에서 사용 
public class MyProduct {
	private int productId;
	private String name;
	private String maker;
	private int price;
	private boolean gift;
	public MyProduct() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MyProduct(int productId, String name, String maker, int price, boolean gift) {
		super();
		this.productId = productId;
		this.name = name;
		this.maker = maker;
		this.price = price;
		this.gift = gift;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isGift() {
		return gift;
	}
	public void setGift(boolean gift) {
		this.gift = gift;
	}
	@Override
	public String toString() {
		return "ProductVO [productId=" + productId + ", name=" + name + ", maker=" + maker + ", price=" + price
				+ ", gift=" + gift + "]";
	}
	
}
