package org.kosa.myproject;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kosa.myproject.mapper.ProductMapper;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Spring20JoyThymeleafServiceJunitInstApplicationTests {
	
	private final  ProductMapper productMapper;
	
	public Spring20JoyThymeleafServiceJunitInstApplicationTests(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}

	@Test
	void contextLoads() {
		Assertions.assertNotNull(productMapper);
	}
}
