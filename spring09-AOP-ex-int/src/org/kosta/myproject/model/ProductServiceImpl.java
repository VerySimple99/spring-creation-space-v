package org.kosta.myproject.model;

import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	@Override
	public void updateProduct() {
		System.out.println(getClass().getName()+" core concern updateProduct");
	}
	@Override
	public void findProductById() {
		System.out.println("**공통관심사항: 특정요구사항을 수행 findProductById**");
		System.out.println(getClass().getName()+" core concern findProductById");
	}
	@Override
	public void findProductByName() {
		System.out.println("**공통관심사항: 특정요구사항을 수행 findProductByName**");
		System.out.println(getClass().getName()+" core concern findProductByName");
	}
	@Override
	public void findProductListByMaker() {
		System.out.println("**공통관심사항: 특정요구사항을 수행 findProductListByMaker**");
		System.out.println(getClass().getName()+" core concern findProductListByMaker");
	}
	// 그외 많은 메서드가 있다고 가정 
}





