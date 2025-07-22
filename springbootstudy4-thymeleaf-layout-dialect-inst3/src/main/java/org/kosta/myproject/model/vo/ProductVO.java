package org.kosta.myproject.model.vo;

public class ProductVO {
	private int productNo;
	private String name;
	private String maker;
	private int price;
	
	public ProductVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductVO(int productNo, String name, String maker, int price) {
		super();
		this.productNo = productNo;
		this.name = name;
		this.maker = maker;
		this.price = price;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductVO [productNo=" + productNo + ", name=" + name + ", maker=" + maker + ", price=" + price + "]";
	}
	
}
