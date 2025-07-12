package org.kosta.myproject.model;

import org.springframework.stereotype.Repository;

@Repository
public class ProductDAOImplVer2 implements ProductDAO {

	@Override
	public String findProductById(String id) {		
		return id+"아이디 상품정보 "+getClass().getName();
	}
}
