package org.kosta.myproject.model;

//@Repository  : Persistence Layer 영속성 계층에 적용하는 Component 어노테이션 
//@Repository 
public class ProductDAOImpl implements ProductDAO {
	@Override
	public String findProductById(String id) {
		return id+"아이디 상품정보 "+getClass().getName();
	}
}
