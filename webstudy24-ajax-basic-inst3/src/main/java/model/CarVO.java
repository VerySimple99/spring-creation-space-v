package model;

public class CarVO {
	private String model;
	private String maker;
	private int price;
	public CarVO() {
		super();
	}
	public CarVO(String model, String maker, int price) {
		super();
		this.model = model;
		this.maker = maker;
		this.price = price;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
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
		return "CarVO [model=" + model + ", maker=" + maker + ", price=" + price + "]";
	}
	
}
