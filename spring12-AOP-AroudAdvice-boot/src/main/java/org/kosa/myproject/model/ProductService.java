package org.kosa.myproject.model;

import java.sql.SQLException;

import org.springframework.stereotype.Service;
@Service
public class ProductService {
	private int productCount=5;
	public void register(String productName) {
		System.out.println("register product "+productName);
	}
	public String getMaker() {
		return "오뚜기";
	}	
	public void sellProduct(int count) throws InventoryException {
		if(productCount<count) {//상품재고수량보다 주문수량이 많으면 InventoryException 발생시킨다 
			throw new InventoryException("재고수량이 부족합니다");
		}
		System.out.println("상품 판매.. 판매수량:"+count);
	}
	public void buyProduct(int count) throws SQLException {
		if(count<=0) {
			throw new SQLException("잘못된 구매수량");
		}
		System.out.println("상품 구매.. 구매수량:"+count);
	}
}









