package org.kosta.myproject.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // business layer 비즈니스 계층에서 사용하는 컴포넌트 계열 어노테이션 
public class ProductServiceImpl implements ProductService {
	private final ProductDAO productDAO;// 항상 추상 인터페이스 타입으로 관리해야 한다 
	@Autowired // DI 
	public ProductServiceImpl(ProductDAO productDAO) {
		super();
		this.productDAO = productDAO;
	}
	@Override
	public String findProductById(String id) {
		return productDAO.findProductById(id);
	}
}







