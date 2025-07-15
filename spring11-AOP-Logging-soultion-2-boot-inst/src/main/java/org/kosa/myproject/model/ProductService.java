package org.kosa.myproject.model;

public interface ProductService {
	public void deleteProduct(String id);
	public void findProductById(String id);
	public void findProductListByMaker(String maker);
	public void findProductListByPriceAndMaker(int price,String maker);	
}
