package org.kosa.myproject.test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kosa.myproject.domain.Product;
import org.kosa.myproject.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductUnitTest {
//	private Logger logger=LoggerFactory.getLogger(getClass());
	private final  ProductMapper productMapper;
   
	@Autowired
	public ProductUnitTest(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}
	@Test
	void contextLoads() {
		Assertions.assertNotNull(productMapper);// null 이 아니면 그린 라이트 
	}
	@Test
	public void getTotalProductCount() {
		int totalCount=productMapper.getTotalProductCount();
		Assertions.assertNotEquals(0,totalCount);// 0 이 기대값 , totalCount 가 실제값 => 같지 않으면 통과 
	}
	
	@Test
	public void selectAllProducts() {
		List<Product> list=productMapper.selectAllProducts();
		Assertions.assertEquals(6, list.size());
	}
	@Test
	public void register() {
		
		
	}
	@Test
	public void selectProductById() {
		Long id=9L;
		//id=1L;
		Product product=productMapper.selectProductById(id);
		Assertions.assertNotNull(product);
	}
	// 상품 등록 및 삭제 테스트를 해볼 수 있음 
}
