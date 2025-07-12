package org.kosa.myproject.model;

import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	@Override
	public void updateProduct() {
		System.out.println(getClass().getName()+" core concern updateProduct");
	}
	@Override
	public void findProductById() {		
		System.out.println(getClass().getName()+" core concern findProductById");
	}
	@Override
	public void findProductByName() {		
		System.out.println(getClass().getName()+" core concern findProductByName");
	}
	@Override
	public void findProductListByMaker() {		
		System.out.println(getClass().getName()+" core concern findProductListByMaker");
	}
	// 그외 많은 메서드가 있다고 가정 
}





