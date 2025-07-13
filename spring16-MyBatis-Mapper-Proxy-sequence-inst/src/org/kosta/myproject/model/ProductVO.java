package org.kosta.myproject.model;

public class ProductVO {
	private long productNo;
	private String name;
	private String maker;
	private long price;
	public ProductVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductVO(long productNo, String name, String maker, long price) {
		super();
		this.productNo = productNo;
		this.name = name;
		this.maker = maker;
		this.price = price;
	}
	public long getProductNo() {
		return productNo;
	}
	public void setProductNo(long productNo) {
		this.productNo = productNo;
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
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "ProductVO [productNo=" + productNo + ", name=" + name + ", maker=" + maker + ", price=" + price + "]";
	}
	
}
